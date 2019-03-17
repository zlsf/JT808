
package model;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import message.PlatformAckMessage;
import message.PlatformGeneralRsp;
import message.PlatformReqMessage;
import model.codec.JT808LargePacket;
import model.codec.JT808Packet;
import model.codec.JT808PacketCodec;
import model.codec.MessageId;
import model.codec.OutboundMessage;
import model.codec.TerminalId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Session {

	private static final Logger log = LoggerFactory.getLogger(Session.class);

	private final String id;
	private final Channel channel;
	private TerminalId terminalId;
	private boolean authenticated;
	private int msgNo = 0;

	private MessageProcessService messageProcessService;

	private Map<Integer, JT808Packet> alreadySentPacketMap = new ConcurrentHashMap<>(); // 发送未确认消息
	private Map<Integer, JT808LargePacket> alreadySentLargePacketMap = new ConcurrentHashMap<>(); // 发送未确认的长消息
	private Map<MessageId, JT808LargePacket> receivedLargePacketMap = new ConcurrentHashMap<>(); // 接收的长消息

	public Session(String id, Channel channel) {
		this.id = id;
		this.channel = channel;
	}

	public String getId() {
		return id;
	}

	public TerminalId getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(TerminalId terminalId) {
		this.terminalId = terminalId;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getRemoteAddress() {
		return channel.remoteAddress().toString();
	}

	public void close() {
		log.info("close channel: {}", id);
		if (channel.isOpen()) {
			channel.close();
		}
	}

	public void setMessageProcessService(MessageProcessService messageProcessService) {
		this.messageProcessService = messageProcessService;
	}

	public int getMsgNo() {
		return msgNo;
	}

	private synchronized int getMsgNoAndIncrement() {
		if (msgNo >= 0xffff)
			msgNo = 0;
		return msgNo++;
	}

	/**
	 * 发送平台请求命令
	 * 
	 * @param reqMessage
	 */
	public void sendReqMessage(PlatformReqMessage reqMessage) {
		sendMessage(reqMessage, true);
	}

	/**
	 * 发送平台确认消息
	 * 
	 * @param ackMessage
	 */
	public void sendAckMessage(PlatformAckMessage ackMessage) {
		sendMessage(ackMessage, false);
	}

	/**
	 * 发送平台通用应答
	 * 
	 * @param terminalId
	 * @param ackMsgNo
	 * @param ackMsgId
	 * @param result
	 */
	public void sendPlatformGeneralRsp(TerminalId terminalId, int ackMsgNo, int ackMsgId, int result) {
		PlatformGeneralRsp rsp = new PlatformGeneralRsp(terminalId);
		rsp.setAckMsgNo(ackMsgNo);
		rsp.setAckMsgId(ackMsgId);
		rsp.setResult(result);
		sendAckMessage(rsp);
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @param ack
	 *            是否需要确认应答(平台主动下发的，均要求确认应答)
	 */
	public void sendMessage(OutboundMessage message, boolean ack) {
		List<JT808Packet> packets = JT808PacketCodec.encodeMessage(message);
		if (packets.size() == 1) {
			packets.get(0).setMsgNo(getMsgNoAndIncrement());
			sendPacket(packets.get(0), ack);
		} else {
			for (JT808Packet packet : packets) {
				packet.setMsgNo(getMsgNoAndIncrement());
				sendPacket(packet, ack);
			}
			if (ack) {
				JT808LargePacket lpacket = new JT808LargePacket(packets.get(0));
				for (JT808Packet packet : packets) {
					lpacket.add(packet);
				}
				alreadySentLargePacketMap.put(lpacket.getMsgNo(), lpacket);
			}
		}
	}

	/**
	 * 下发数据包
	 * 
	 * @param packet
	 * @param ack
	 *            是否需要确认应答(平台主动下发的，均要求确认应答)
	 */
	public void sendPacket(JT808Packet packet, boolean ack) {
		byte[] bytes= packet.toByteArray();
		ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer(new byte[] { 0x7e }, bytes,
				new byte[] { 0x7e }));
		if (!future.isSuccess()) {
			log.error("发送数据出错:{}", future.cause());
		} else {
			if (ack) {
				alreadySentPacketMap.put(packet.getMsgNo(), packet);
			}
		}
	}

	/**
	 * 确认消息
	 * 
	 * @param message
	 */
	public void ackMessage(Integer ackMsgNo) {
		alreadySentPacketMap.remove(ackMsgNo);
		JT808LargePacket lpacket = alreadySentLargePacketMap.remove(ackMsgNo);
		if (lpacket != null) {
			for (int i = 1; i < lpacket.getSubpacketCount(); i++) {
				alreadySentPacketMap.remove(lpacket.getMsgNo() + i);
			}
		}
	}

	public void processPacket(JT808Packet packetData) {
		log.info("process packet data");
		if (packetData.isSubpacket()) {
			if (receivedLargePacketMap.containsKey(packetData.getMessageId())) {
				JT808LargePacket lmsg = receivedLargePacketMap.get(packetData.getMessageId());
				lmsg.add(packetData);
				if (lmsg.isFulfill()) {
					messageProcessService.processPacketData(this, lmsg.toJT808Packet());
					receivedLargePacketMap.remove(packetData.getMessageId());
				}
			} else {
				JT808LargePacket lmsg = new JT808LargePacket(packetData);
				receivedLargePacketMap.put(packetData.getMessageId(), lmsg);
			}
		} else {
			messageProcessService.processPacketData(this, packetData);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", terminalId=" + terminalId + ", authenticated=" + authenticated + ", msgNo="
				+ msgNo + ", remoteAddress=" + getRemoteAddress() + "]";
	}

	public static String buildId(Channel channel) {
		return channel.toString();
	}

	public static Session buildSession(Channel channel) {
		Session session = new Session(buildId(channel), channel);
		return session;
	}

	
	public Channel getChannel() {
		return channel;
	}
	
}

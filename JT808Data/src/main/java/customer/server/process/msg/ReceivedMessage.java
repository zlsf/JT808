package customer.server.process.msg;

import customer.server.model.DeviceId;
import io.netty.channel.Channel;

/**
 * 锟斤拷锟斤拷息
 *
 * @author
 * 
 *         <p>
 * 	Modification History:
 *         </p>
 *         <p>
 * 	Date Author Description
 *         </p>
 *         <p>
 * 	------------------------------------------------------------------
 *         </p>
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 */
public interface ReceivedMessage {

    /**
     * Gets the msg id.
     *
     * @return the msg id
     */
    int getMsgId();

    /**
     * Gets the stop id.
     *
     * @return the stop id
     */
    DeviceId getDeviceId();

    /**
     * Gets the msg no.
     *
     * @return the msg no
     */
    int getMsgNo();

    /**
     * Sets the channel.
     *
     * @param channel
     *            the new channel
     */
    void setChannel(Channel channel);

    /**
     * Gets the channel.
     *
     * @return the channel
     */
    Channel getChannel();

    /**
     * 锟斤拷锟斤拷锟斤拷息
     */
    void dealMessage();
}

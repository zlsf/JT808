import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.DataServer;
import Utils.Constant;
import model.Task;

public class MainStart {

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(MainStart.class);

	public static void main(String[] args) {
		log.info("程序启动......");
		DataServer server = DataServer.getInstance();
		server.setPort(8001); // 可以重新设置端口号，不设置默认8899
		DataServer.setModel(Constant.Service_Model_D2C); // 设备对中心

//		log.info("定时轮训启动......");
//		Task task = new Task();
//		task.doTask();
		server.run();
		log.debug("程序 退出......");
	}

}

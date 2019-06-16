import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.DataServer808;
import Utils.Constant;

/**
 * 程序入口文件
 * @author Administrator
 *
 */
public class MainStart {

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(MainStart.class);

	public static void main(String[] args) {
		log.info("程序启动......");
		DataServer808 server = DataServer808.getInstance();
		server.setPort(8003); // 可以重新设置端口号，不设置默认8899
		DataServer808.setModel(Constant.Service_Model_D2C); // 设备对中心

		// 某些定时轮训的口--废弃不用，因为位置信息定时上报

		// log.info("定时轮训启动......");
		// Task task = new Task();
		// task.doTask();
		server.run();
		log.debug("程序 退出......");
	}

}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.DataServer;
import Utils.Constant;
import model.Task;

public class MainStart {

	/**
	 * ��־
	 */
	private static Logger log = LoggerFactory.getLogger(MainStart.class);

	public static void main(String[] args) {
		log.info("��������......");
		DataServer server = DataServer.getInstance();
		server.setPort(8001); // �����������ö˿ںţ�������Ĭ��8899
		DataServer.setModel(Constant.Service_Model_D2C); // �豸������

//		log.info("��ʱ��ѵ����......");
//		Task task = new Task();
//		task.doTask();
		server.run();
		log.debug("���� �˳�......");
	}

}

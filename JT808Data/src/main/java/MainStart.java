import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.DataServer808;
import Utils.Constant;

/**
 * ��������ļ�
 * @author Administrator
 *
 */
public class MainStart {

	/**
	 * ��־
	 */
	private static Logger log = LoggerFactory.getLogger(MainStart.class);

	public static void main(String[] args) {
		log.info("��������......");
		DataServer808 server = DataServer808.getInstance();
		server.setPort(8003); // �����������ö˿ںţ�������Ĭ��8899
		DataServer808.setModel(Constant.Service_Model_D2C); // �豸������

		// ĳЩ��ʱ��ѵ�Ŀ�--�������ã���Ϊλ����Ϣ��ʱ�ϱ�

		// log.info("��ʱ��ѵ����......");
		// Task task = new Task();
		// task.doTask();
		server.run();
		log.debug("���� �˳�......");
	}

}

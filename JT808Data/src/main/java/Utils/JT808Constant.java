
package Utils;

public class JT808Constant {
	/**
	 * ����Frame��Ϣ����Ϣ����󳤶�(1023) 2^10 - 1
	 */
	public static final int MAX_MSG_BODY_LENGTH = 0b1111111111;

	/**
	 * ͨ��Ӧ��״̬
	 */
	public static final int GENERAL_RSP_RESULT_SUCCESS = 0x00; // �ɹ�/ȷ��
	public static final int GENERAL_RSP_RESULT_FAILURE = 0x01; // ʧ��
	public static final int GENERAL_RSP_RESULT_MSG_ERROR = 0x02; // ��Ϣ����
	public static final int GENERAL_RSP_RESULT_NOT_SUPPORT = 0x03; // ��֧��
	public static final int GENERAL_RSP_RESULT_ALARM_ACK = 0x04; // ��������ȷ��

	/**
	 * ��Ϣ���ձ�
	 */
	public static final int MSG_ID_TERMINAL_GENERAL_RSP = 0x0001; // �ն�ͨ��Ӧ��
	public static final int MSG_ID_PLATFORM_GENERAL_RSP = 0x8001; // ƽ̨ͨ��Ӧ��
	public static final int MSG_ID_TERMINAL_HEARTBEAT_REQ = 0x0002; // �ն�����
	public static final int MSG_ID_PLATFORM_RETRANSMISSION_REQ = 0x8003; // ƽ̨���󲹴��ְ�
	public static final int MSG_ID_TERMINAL_REGISTER_REQ = 0x0100; // �ն�ע��
	public static final int MSG_ID_TERMINAL_REGISTER_RSP = 0x8100; // �ն�ע��Ӧ��
	public static final int MSG_ID_TERMINAL_UNREGISTER_REQ = 0x0003; // �ն�ע��
	public static final int MSG_ID_TERMINAL_AUTH_REQ = 0x0102; // �ն˼�Ȩ
	public static final int MSG_ID_TERMINAL_PARAM_SETTING_REQ = 0x8103; // ƽ̨���������ն˲���
	public static final int MSG_ID_TERMINAL_PARAM_QUERY_REQ = 0x8104; // ƽ̨�����ѯ�ն˲���
	public static final int MSG_ID_TERMINAL_PARAM_PARTIAL_QUERY_REQ = 0x8106; // ƽ̨�����ѯָ���ն˲���
	public static final int MSG_ID_TERMINAL_PARAM_QUERY_RSP = 0x0104; // �ն˲���Ӧ��
	public static final int MSG_ID_TERMINAL_COMMAND_REQ = 0x8105; // ƽ̨�����ն˿���
	public static final int MSG_ID_TERMINAL_PROPS_QUERY_REQ = 0x8107; // ƽ̨�����ѯ�ն�����
	public static final int MSG_ID_TERMINAL_PROPS_QUERY_RSP = 0x0107; // ��ѯ�ն�����Ӧ��
	public static final int MSG_ID_TERMINAL_SYSTERM_UPDATE_REQ = 0x8108; // ƽ̨�·�������
	public static final int MSG_ID_TERMINAL_SYSTERM_UPDATE_RSP = 0x0108; // �������֪ͨ

	public static final int MSG_ID_TERMINAL_LOCATION_REPORT = 0x0200; // λ����Ϣ�㱨
	public static final int MSG_ID_TERMINAL_LOCATION_QUERY_REQ = 0x8201; // λ����Ϣ��ѯ
	public static final int MSG_ID_TERMINAL_LOCATION_QUERY_RSP = 0x0201; // λ����Ϣ��ѯӦ��
	public static final int MSG_ID_TERMINAL_LOCATION_TRACKING_REQ = 0x8202; // ��ʱλ����Ϣ���ٿ���
	public static final int MSG_ID_PLATFORM_MANUAL_CONFIRMED_ALARM_REQ = 0x8203; // �˹�ȷ�ϱ�����Ϣ

	public static final int MSG_ID_PLATFORM_TEXT_MESSAGE_REQ = 0x8300; // ��Ϣ�ı��·�
	public static final int MSG_ID_PLATFORM_SELECTABLE_EVENT_MAINT_REQ = 0x8301; // �¼�����
	public static final int MSG_ID_TERMINAL_SELECTABLE_EVENT_REPORT_REQ = 0x0301; // �¼�����
	public static final int MSG_ID_PLATFORM_QUESTION_MESSAGE_REQ = 0x8302; // �����·�
	public static final int MSG_ID_TERMINAL_QUESTION_ANSWER_RSP = 0x0302; // ����Ӧ��
	public static final int MSG_ID_PLATFORM_INFORMATION_MENU_MAINT_REQ = 0x8303; // ��Ϣ�㲥�˵�����
	public static final int MSG_ID_TERMINAL_INFORMATION_MENU_MAINT_RSP = 0x0303; // ��Ϣ�㲥/ȡ��
	public static final int MSG_ID_PLATFORM_INFORMATION_REQ = 0x8304; // ��Ϣ����

	public static final int MSG_ID_PLATFORM_TELPHONE_CALLBACK_REQ = 0x8400; // �绰�ذ�
	public static final int MSG_ID_PLATFORM_TELPHONE_BOOK_REQ = 0x8401; // �绰��

	public static final int MSG_ID_PLATFORM_VEHICLE_CONTROL_REQ = 0x8500; // ��������
	public static final int MSG_ID_TERMINAL_VEHICLE_CONTROL_RSP = 0x0500; // ��������Ӧ��

	public static final int MSG_ID_PLATFORM_CIRCLE_AREA_UPDATE_REQ = 0x8600; // ����Բ������
	public static final int MSG_ID_PLATFORM_CIRCLE_AREA_REMOVE_REQ = 0x8601; // ɾ��Բ������
	public static final int MSG_ID_PLATFORM_RECTANGLE_AREA_UPDATE_REQ = 0x8602; // ���þ�������
	public static final int MSG_ID_PLATFORM_RECTANGLE_AREA_REMOVE_REQ = 0x8603; // ɾ����������
	public static final int MSG_ID_PLATFORM_POLYGON_AREA_UPDATE_REQ = 0x8604; // ���ö��������
	public static final int MSG_ID_PLATFORM_POLYGON_AREA_REMOVE_REQ = 0x8605; // ɾ�����������
	public static final int MSG_ID_PLATFORM_ROAD_LINE_UPDATE_REQ = 0x8606; // ������·
	public static final int MSG_ID_PLATFORM_ROAD_LINE_REMOVE_REQ = 0x8607; // ɾ����·

	public static final int MSG_ID_PLATFORM_DRIVE_RECORD_COLLECT_REQ = 0x8700; // ��ʻ��¼���ݲɼ�����
	public static final int MSG_ID_TERMINAL_DRIVE_RECORD_UPLOAD_RSP = 0x0700; // ��ʻ��¼���ݲɼ�����
	public static final int MSG_ID_PLATFORM_DRIVE_RECORD_PARAM_REQ = 0x8701; // ��ʻ��¼�����´�����
	public static final int MSG_ID_TERMINAL_EWAYBILL_REPORT_REQ = 0x0701; // �����˵��ϱ�
	public static final int MSG_ID_PLATFORM_DRIVER_REPORT_REQ = 0x8702; // �ϴ���ʻԱ��Ϣ
	public static final int MSG_ID_TERMINAL_DRIVER_REPORT_REQ = 0x0702; // ��ʻԱ��Ϣ

	public static final int MSG_ID_TERMINAL_LOCATION_BATCH_REPORT = 0x0704; // ������Ϣ��Ϣ
	public static final int MSG_ID_TERMINAL_CANBUS_DATA_REPORT = 0x0705; // CAN��Ϣ��Ϣ

	public static final int MSG_ID_TERMINAL_MULTIMEDIA_EVENT_REPORT = 0x0800; // ��ý����Ϣ
	public static final int MSG_ID_TERMINAL_MULTIMEDIA_DATA_REPORT = 0x0801; // ��ý������
	public static final int MSG_ID_PLATFORM_MULTIMEDIA_DATA_REPORT_RSP = 0x8800; // ��ý�������ϴ�Ӧ��
	public static final int MSG_ID_PLATFORM_CAMERA_TAKE_PHOTO_REQ = 0x8801; // ��������
	public static final int MSG_ID_TERMINAL_CAMERA_TAKE_PHOTO_RSP = 0x0805; // ��������Ӧ��
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_QUERY_REQ = 0x8802; // �洢��ý���ѯ
	public static final int MSG_ID_TERMINAL_STORED_MULTIMEDIA_QUERY_RSP = 0x0802; // ��ѯӦ��
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_UPLOAD_REQ = 0x8803; // �洢��ý���ϴ�
	public static final int MSG_ID_PLATFORM_RECORDER_RECORD_REQ = 0x8804; // ¼����ʼ
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_UPLOAD_EXACT_REQ = 0x8805; // �����洢��ý���ϴ�

	public static final int MSG_ID_PLATFORM_DATA_SENDDOWN_REQ = 0x8900; // ����͸��
	public static final int MSG_ID_TERMINAL_DATA_UPLOAD_REQ = 0x0900; // ����͸��
	public static final int MSG_ID_TERMINAL_DATA_COMPRESS_UPLOAD = 0x0901; // ѹ���ϱ�
	public static final int MSG_ID_PLATFORM_RSA_PUBLIC_KEY_REQ = 0x8A00; // ƽ̨RSA��Կ
	public static final int MSG_ID_TERMINAL_RSA_PUBLIC_KEY_REQ = 0x0A00; // �ն�RSA��Կ

}

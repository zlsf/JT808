
package Utils;

public class JT808Constant {
	/**
	 * 单个Frame消息的消息体最大长度(1023) 2^10 - 1
	 */
	public static final int MAX_MSG_BODY_LENGTH = 0b1111111111;

	/**
	 * 通用应答状态
	 */
	public static final int GENERAL_RSP_RESULT_SUCCESS = 0x00; // 成功/确认
	public static final int GENERAL_RSP_RESULT_FAILURE = 0x01; // 失败
	public static final int GENERAL_RSP_RESULT_MSG_ERROR = 0x02; // 消息有误
	public static final int GENERAL_RSP_RESULT_NOT_SUPPORT = 0x03; // 不支持
	public static final int GENERAL_RSP_RESULT_ALARM_ACK = 0x04; // 报警处理确认

	/**
	 * 消息对照表
	 */
	public static final int MSG_ID_TERMINAL_GENERAL_RSP = 0x0001; // 终端通用应答
	public static final int MSG_ID_PLATFORM_GENERAL_RSP = 0x8001; // 平台通用应答
	public static final int MSG_ID_TERMINAL_HEARTBEAT_REQ = 0x0002; // 终端心跳
	public static final int MSG_ID_PLATFORM_RETRANSMISSION_REQ = 0x8003; // 平台请求补传分包
	public static final int MSG_ID_TERMINAL_REGISTER_REQ = 0x0100; // 终端注册
	public static final int MSG_ID_TERMINAL_REGISTER_RSP = 0x8100; // 终端注册应答
	public static final int MSG_ID_TERMINAL_UNREGISTER_REQ = 0x0003; // 终端注销
	public static final int MSG_ID_TERMINAL_AUTH_REQ = 0x0102; // 终端鉴权
	public static final int MSG_ID_TERMINAL_PARAM_SETTING_REQ = 0x8103; // 平台请求设置终端参数
	public static final int MSG_ID_TERMINAL_PARAM_QUERY_REQ = 0x8104; // 平台请求查询终端参数
	public static final int MSG_ID_TERMINAL_PARAM_PARTIAL_QUERY_REQ = 0x8106; // 平台请求查询指定终端参数
	public static final int MSG_ID_TERMINAL_PARAM_QUERY_RSP = 0x0104; // 终端参数应答
	public static final int MSG_ID_TERMINAL_COMMAND_REQ = 0x8105; // 平台请求终端控制
	public static final int MSG_ID_TERMINAL_PROPS_QUERY_REQ = 0x8107; // 平台请求查询终端属性
	public static final int MSG_ID_TERMINAL_PROPS_QUERY_RSP = 0x0107; // 查询终端属性应答
	public static final int MSG_ID_TERMINAL_SYSTERM_UPDATE_REQ = 0x8108; // 平台下发升级包
	public static final int MSG_ID_TERMINAL_SYSTERM_UPDATE_RSP = 0x0108; // 升级结果通知

	public static final int MSG_ID_TERMINAL_LOCATION_REPORT = 0x0200; // 位置信息汇报
	public static final int MSG_ID_TERMINAL_LOCATION_QUERY_REQ = 0x8201; // 位置信息查询
	public static final int MSG_ID_TERMINAL_LOCATION_QUERY_RSP = 0x0201; // 位置信息查询应答
	public static final int MSG_ID_TERMINAL_LOCATION_TRACKING_REQ = 0x8202; // 临时位置信息跟踪控制
	public static final int MSG_ID_PLATFORM_MANUAL_CONFIRMED_ALARM_REQ = 0x8203; // 人工确认报警信息

	public static final int MSG_ID_PLATFORM_TEXT_MESSAGE_REQ = 0x8300; // 信息文本下发
	public static final int MSG_ID_PLATFORM_SELECTABLE_EVENT_MAINT_REQ = 0x8301; // 事件设置
	public static final int MSG_ID_TERMINAL_SELECTABLE_EVENT_REPORT_REQ = 0x0301; // 事件报告
	public static final int MSG_ID_PLATFORM_QUESTION_MESSAGE_REQ = 0x8302; // 提问下发
	public static final int MSG_ID_TERMINAL_QUESTION_ANSWER_RSP = 0x0302; // 提问应答
	public static final int MSG_ID_PLATFORM_INFORMATION_MENU_MAINT_REQ = 0x8303; // 信息点播菜单设置
	public static final int MSG_ID_TERMINAL_INFORMATION_MENU_MAINT_RSP = 0x0303; // 信息点播/取消
	public static final int MSG_ID_PLATFORM_INFORMATION_REQ = 0x8304; // 信息服务

	public static final int MSG_ID_PLATFORM_TELPHONE_CALLBACK_REQ = 0x8400; // 电话回拔
	public static final int MSG_ID_PLATFORM_TELPHONE_BOOK_REQ = 0x8401; // 电话本

	public static final int MSG_ID_PLATFORM_VEHICLE_CONTROL_REQ = 0x8500; // 车辆控制
	public static final int MSG_ID_TERMINAL_VEHICLE_CONTROL_RSP = 0x0500; // 车辆控制应答

	public static final int MSG_ID_PLATFORM_CIRCLE_AREA_UPDATE_REQ = 0x8600; // 设置圆形区域
	public static final int MSG_ID_PLATFORM_CIRCLE_AREA_REMOVE_REQ = 0x8601; // 删除圆形区域
	public static final int MSG_ID_PLATFORM_RECTANGLE_AREA_UPDATE_REQ = 0x8602; // 设置矩形区域
	public static final int MSG_ID_PLATFORM_RECTANGLE_AREA_REMOVE_REQ = 0x8603; // 删除矩形区域
	public static final int MSG_ID_PLATFORM_POLYGON_AREA_UPDATE_REQ = 0x8604; // 设置多边形区域
	public static final int MSG_ID_PLATFORM_POLYGON_AREA_REMOVE_REQ = 0x8605; // 删除多边形区域
	public static final int MSG_ID_PLATFORM_ROAD_LINE_UPDATE_REQ = 0x8606; // 设置线路
	public static final int MSG_ID_PLATFORM_ROAD_LINE_REMOVE_REQ = 0x8607; // 删除线路

	public static final int MSG_ID_PLATFORM_DRIVE_RECORD_COLLECT_REQ = 0x8700; // 行驶记录数据采集命令
	public static final int MSG_ID_TERMINAL_DRIVE_RECORD_UPLOAD_RSP = 0x0700; // 行驶记录数据采集命令
	public static final int MSG_ID_PLATFORM_DRIVE_RECORD_PARAM_REQ = 0x8701; // 行驶记录数据下传命令
	public static final int MSG_ID_TERMINAL_EWAYBILL_REPORT_REQ = 0x0701; // 电子运单上报
	public static final int MSG_ID_PLATFORM_DRIVER_REPORT_REQ = 0x8702; // 上传驾驶员信息
	public static final int MSG_ID_TERMINAL_DRIVER_REPORT_REQ = 0x0702; // 驾驶员信息

	public static final int MSG_ID_TERMINAL_LOCATION_BATCH_REPORT = 0x0704; // 批量信息信息
	public static final int MSG_ID_TERMINAL_CANBUS_DATA_REPORT = 0x0705; // CAN信息信息

	public static final int MSG_ID_TERMINAL_MULTIMEDIA_EVENT_REPORT = 0x0800; // 多媒体信息
	public static final int MSG_ID_TERMINAL_MULTIMEDIA_DATA_REPORT = 0x0801; // 多媒体数据
	public static final int MSG_ID_PLATFORM_MULTIMEDIA_DATA_REPORT_RSP = 0x8800; // 多媒体数据上传应答
	public static final int MSG_ID_PLATFORM_CAMERA_TAKE_PHOTO_REQ = 0x8801; // 立即拍摄
	public static final int MSG_ID_TERMINAL_CAMERA_TAKE_PHOTO_RSP = 0x0805; // 立即拍摄应答
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_QUERY_REQ = 0x8802; // 存储多媒体查询
	public static final int MSG_ID_TERMINAL_STORED_MULTIMEDIA_QUERY_RSP = 0x0802; // 查询应答
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_UPLOAD_REQ = 0x8803; // 存储多媒体上传
	public static final int MSG_ID_PLATFORM_RECORDER_RECORD_REQ = 0x8804; // 录音开始
	public static final int MSG_ID_PLATFORM_STORED_MULTIMEDIA_UPLOAD_EXACT_REQ = 0x8805; // 单条存储多媒体上传

	public static final int MSG_ID_PLATFORM_DATA_SENDDOWN_REQ = 0x8900; // 下行透传
	public static final int MSG_ID_TERMINAL_DATA_UPLOAD_REQ = 0x0900; // 上行透传
	public static final int MSG_ID_TERMINAL_DATA_COMPRESS_UPLOAD = 0x0901; // 压缩上报
	public static final int MSG_ID_PLATFORM_RSA_PUBLIC_KEY_REQ = 0x8A00; // 平台RSA公钥
	public static final int MSG_ID_TERMINAL_RSA_PUBLIC_KEY_REQ = 0x0A00; // 终端RSA公钥

}

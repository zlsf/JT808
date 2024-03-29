
package JT809Data.model;

public class JT809Constant {

	public static final int FRAME_LENGTH_CONSTANT = 26;

	public static final int UP_CONNECT_REQ = 0x1001;

	public static final int UP_CONNECT_RSP = 0x1002;

	public static final int UP_DISCONNECT_REQ = 0x1003;

	public static final int UP_DISCONNECT_RSP = 0x1004;

	public static final int UP_LINKTEST_REQ = 0x1005;

	public static final int UP_LINKTEST_RSP = 0x1006;

	public static final int UP_DISCONNECT_INFORM = 0x1007;

	public static final int UP_CLOSELINK_INFORM = 0x1008;

	public static final int DOWN_CONNECT_REQ = 0x9001;

	public static final int DOWN_CONNECT_RSP = 0x9002;

	public static final int DOWN_DISCONNECT_REQ = 0x9003;

	public static final int DOWN_DISCONNECT_RSP = 0x9004;

	public static final int DOWN_LINKTEST_REQ = 0x9005;

	public static final int DOWN_LINKTEST_RSP = 0x9006;

	public static final int DOWN_DISCONNECT_INFORM = 0x9007;

	public static final int DOWN_CLOSELINK_INFORM = 0x9008;

	public static final int DOWN_TOTAL_RECV_BACK_MSG = 0x9101;

	public static final int UP_EXG_MSG = 0x1200;

	public static final int DOWN_EXG_MSG = 0x9200;

	public static final int UP_PLATFORM_MSG = 0x1300;

	public static final int DOWN_PLATFORM_MSG = 0x9300;

	public static final int UP_WARN_MSG = 0x1400;

	public static final int DOWN_WARN_MSG = 0x9400;

	public static final int UP_CTRL_MSG = 0x1500;

	public static final int DOWN_CTRL_MSG = 0x9500;

	public static final int UP_BASE_MSG = 0x1600;

	public static final int DOWN_BASE_MSG = 0x9600;

	// 子业务常量
	public static final int UP_EXG_MSG_REGISTER = 0x1201;
	public static final int UP_EXG_MSG_REAL_LOCATION = 0x1202;
	public static final int UP_EXG_MSG_HISTORY_LOCATION = 0x1203;
	// 0x1204--
	public static final int UP_EXG_MSG_RETURN_STARTUP_ACK = 0x1205;
	public static final int UP_EXG_MSG_RETURN_END_ACK = 0x1206;
	public static final int UP_EXG_MSG_APPLY_FOR_MONITOR_STARTUP = 0x1207;
	public static final int UP_EXG_MSG_APPLY_FOR_MONITOR_END = 0x1208;
	public static final int UP_EXG_MSG_APPLY_HISGNSSDATA_REQ = 0x1209;
	public static final int UP_EXG_MSG_REPORT_DRIVER_INFO_ACK = 0x120A;
	public static final int UP_EXG_MSG_TAKE_EWAYBILL_ACK = 0x120B;
	public static final int UP_EXG_MSG_REPORT_DRIVER_INFO = 0x120C;
	public static final int UP_EXG_MSG_TAKE_EWAYBILL_INFO = 0x120D;

	public static final int DOWN_EXG_MSG_CAR_LOCATION = 0x9202;
	public static final int DOWN_EXG_MSG_HISTORY_ARCOSSAREA = 0x9203;
	public static final int DOWN_EXG_MSG_CAR_INFO = 0x9204;
	public static final int DOWN_EXG_MSG_RETURN_STARTUP = 0x9205;
	public static final int DOWN_EXG_MSG_RETURN_END = 0x9206;
	public static final int DOWN_EXG_MSG_APPLY_FOR_MONITOR_STARTUP_ACK = 0x9207;
	public static final int DOWN_EXG_MSG_APPLY_FOR_MONITOR_END_ACK = 0x9208;
	public static final int DOWN_EXG_MSG_APPLY_HISGNSSDATA_ACK = 0x9209;
	public static final int DOWN_EXG_MSG_REPORT_DRIVER_INFO = 0x920A;
	public static final int DOWN_EXG_MSG_TAKE_EWAYBILL_REQ = 0x920B;

	public static final int UP_PLATFORM_MSG_POST_QUERY_ACK = 0x1301;
	public static final int UP_PLATFORM_MSG_INFO_ACK = 0x1302;
	public static final int DOWN_PLATFORM_MSG_POST_QUERY_REQ = 0x9301;
	public static final int DOWN_PLATFORM_MSG_INFO_REQ = 0x9302;

	public static final int UP_WARN_MSG_URGE_TODO_ACK = 0x1401;
	public static final int UP_WARN_MSG_ADPT_INFO = 0x1402;
	public static final int UP_WARN_MSG_ADPT_TODO_INFO = 0x1403;
	public static final int DOWN_WARN_MSG_URGE_TODO_REQ = 0x9401;
	public static final int DOWN_WARN_MSG_INFO_TIPS = 0x9402;
	public static final int DOWN_WARN_MSG_EXG_INFORM = 0x9403;

	public static final int UP_CTRL_MSG_MONITOR_VEHICLE_ACK = 0x1501;
	public static final int UP_CTRL_MSG_TAKE_PHOTO_ACK = 0x1502;
	public static final int UP_CTRL_MSG_TEXT_INFO_ACK = 0x1503;
	public static final int UP_CTRL_MSG_TAKE_TRAVEL_ACK = 0x1504;
	public static final int UP_CTRL_MSG_EMERGENCY_MONITORING_ACK = 0x1505;
	public static final int DOWN_CTRL_MSG_MONITOR_VEHICLE_REQ = 0x9501;
	public static final int DOWN_CTRL_MSG_TAKE_PHOTO_REQ = 0x9502;
	public static final int DOWN_CTRL_MSG_TEXT_INFO = 0x9503;
	public static final int DOWN_CTRL_MSG_TAKE_TRAVEL_REQ = 0x9504;
	public static final int DOWN_CTRL_MSG_EMERGENCY_MONITORING_REQ = 0x9505;

	public static final int UP_BASE_MSG_VEHICLE_ADDED_ACK = 0x1601;
	public static final int DOWN_BASE_MSG_VEHICLE_ADDED = 0x9601;
}

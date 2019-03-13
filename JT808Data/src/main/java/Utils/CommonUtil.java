
package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static Date strToTimeSpan(String dataStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YYMMDDhhmmss");
			Date ts = sdf.parse(dataStr);
			return ts;
		} catch (Exception ex) {
			return null;
		}
	}
}

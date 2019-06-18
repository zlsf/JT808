import JT809govData.model.GnssPlatformIdGov809;
import JT809govData.model.Session809Gov;
import JT809govData.netty.DataServerGov809;

public class MainStart809Gov {

	public static void main(String[] args) {
		DataServerGov809 server = new DataServerGov809();
		server.setPort(10001);
		server.start();
	}


}

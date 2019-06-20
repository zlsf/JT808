import customer.server.service.DataServer;

public class MyServiceStarter {

    public static void main(String[] args) {
	DataServer service = new DataServer();
	service.setPort(8899);
	service.start();
    }

}

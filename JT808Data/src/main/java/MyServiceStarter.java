import customer.server.service.DataServer;

public class MyServiceStarter {

    public static void main(String[] args) {
	DataServer service = new DataServer();
	service.setPort(8899);
	service.start();

	try {
	    while (true) {
		System.out.println(service.getRunning());
		System.out.println(service.getCount());
		Thread.sleep(2000);
	    }

	} catch (Exception ex) {

	}
    }

}

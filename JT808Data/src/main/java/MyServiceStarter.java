import customer.server.service.DataServer;

public class MyServiceStarter {

    public static void main(String[] args) {
	MyServiceStarter starter=new MyServiceStarter();
    }

    static {
	DataServer service = new DataServer();
	service.setPort(8888);
	service.start();
    }

}

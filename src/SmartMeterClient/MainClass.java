package SmartMeterClient;

public class MainClass {

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		
		controller.startUp();
		
		Runtime.getRuntime().addShutdownHook(new Thread()
	    {
	      public void run()
	      {
	        System.out.println("Shutdown Hook - Save Data Here");
	      }
	    });
	}
}
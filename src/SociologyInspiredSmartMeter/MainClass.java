package SociologyInspiredSmartMeter;

public class MainClass {

    public static void main(String[] args) {

        Controller controller = new Controller();
		
		controller.startUp();
		
		Runtime.getRuntime().addShutdownHook(new Thread()
	    {
	      public void run()
	      {
	        controller.saveSettings();
			controller.saveFeedback();
	      }
	    });
    }
}
package SmartMeterClient;

public class Controller {

	public Controller() {

		// Load Config

	}

	public void startUp() {

		Config config;
		config = new Config();

		HomePage homePage;

		homePage = new HomePage(this, config);

	}

}

package SmartMeterClient;

public class Controller {

	Config config = new Config();

	Settings settings = new Settings();

	HomePage homePage;

	StatisticsPage statisticsPage;

	SettingsPage settingsPage;

	public Controller() {

		

	}

	public void startUp() {
		
		displayHomePage();

	}

	public void displayHomePage()
	{
		homePage = new HomePage(this, config, settings);
	}

	public void displayStatisticsPage()
	{
		statisticsPage = new StatisticsPage(this, config, settings);
	}

	public void displayCalendarPage()
	{
		//calendarPage = new CalendarPage(this, config, settings);
	}

	public void displaySettingsPage()
	{
	 	settingsPage = new SettingsPage(this, config, settings);
	}

}

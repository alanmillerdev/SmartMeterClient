package SmartMeterClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

	/*
	 * Config and Settings are used to store the configuration and settings of the application.
	 */
	Config config = new Config();
	Settings settings = new Settings();

	/*
	 * The home page is the first page that is displayed when the application is started.
	 * It shows some basic information about the household usage as well as basic social capital statistics.
	 */
	HomePage homePage;

	/*
	 * The statistics page shows more detailed information about the household usage, as well as the social capital statistics.
	 */
	StatisticsPage statisticsPage;

	/*
	 *  The settings page allows the user to change some settings of their agent and the application.
	 */
	SettingsPage settingsPage;

	/*
	 * Status is used to determine what the announcement should be
	 */
	String status;


	public Controller() {

		//Sets the status to "Select" by default
		status = "Select";

	}

	/*
	 * startUp() is called when the application is started.
	 */
	public void startUp() {
		
		displayHomePage();

	}

	/*
	 * displayHomePage() is called when the user clicks the home button.
	 */

	public void displayHomePage()
	{
		homePage = new HomePage(this, config, settings);
	}

	/*
	 * displayStatisticsPage() is called when the user clicks the statistics button.
	 */
	public void displayStatisticsPage()
	{
		statisticsPage = new StatisticsPage(this, config, settings);
	}

	/*
	 * displayCalendarPage() is called when the user clicks the calendar button.
	 */
	public void displayCalendarPage()
	{
		//calendarPage = new CalendarPage(this, config, settings);
	}

	/*
	 * displaySettingsPage() is called when the user clicks the settings button.
	 */
	public void displaySettingsPage()
	{
	 	settingsPage = new SettingsPage(this, config, settings);
	}

	/*
	 * announcementHandler() is called to update the announcement at the top of the display.
	 * It returns a string that is used to display the announcement.
	 */
	public String announcementHandler()
	{

		String announcement = null;

		System.out.println(status);

		switch(status)
		{
			case "Select":
				
				announcement = "Please select your timeslots in the calendar!";
				break;

			case "Selected":

				announcement = "Timeslots will be assigned shortly!";
				break;

			case "Assigned":

				announcement = "Timeslots have been assigned, check your calendar!";
				break;

			default:

				LocalDateTime now = LocalDateTime.now();

				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");

				String time = timeFormatter.format(now);

				int hour = Integer.parseInt(time);

					if(hour >= 0 && hour < 12)
					{
						announcement = "Good morning";
					}
					else if(hour >= 12 && hour < 17)
					{
						announcement = "Good afternoon";
					}
					else if(hour >= 17 && hour <= 23)
					{
						announcement = "Good evening";
					}
				break;
			}

		return announcement;
	}
}

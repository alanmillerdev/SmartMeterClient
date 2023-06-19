package SociologyInspiredSmartMeter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import SociologyInspiredSmartMeter.SmartMeterBackend.SmartMeterBackend;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPagePreferenceSelection;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPageTimeslotTimeline;
import SociologyInspiredSmartMeter.SmartMeterClient.Config;
import SociologyInspiredSmartMeter.SmartMeterClient.HomePage;
import SociologyInspiredSmartMeter.SmartMeterClient.Settings;
import SociologyInspiredSmartMeter.SmartMeterClient.SettingsPage;
import SociologyInspiredSmartMeter.SmartMeterClient.StatisticsPage;

public class Controller {

	//TODO: Write a method that is executed at run time that will read in the settings from the settings file
	//Store those settings in the settings object.

	//TODO: Write a method that is executed at run time that will attempt to read in an existing agent population
	//If the agent population does not exist, create a new one and store it in the controller object.
	
	//TODO: Write a method to create a new agent population including the agent that will be used by the user.
	//Store the agent population in the controller object.

	//TODO: Write a method to save the agent population to a file that can be executed.
	//This method should be executed when the application is closed.

	/*
	 * Config and Settings are used to store the configuration and settings of the application.
	 */
	Config config = new Config();
	Settings settings = new Settings();
	SmartMeterBackend backend = new SmartMeterBackend();

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
	 * The calendar preference selection page allows the user to select their timeslots for the day.
	 */
	CalendarPagePreferenceSelection calendarPagePreferenceSelection;

	/*
	 * The preference timeline page shows the user the timeslots that they have selected.
	 */
	CalendarPageTimeslotTimeline preferenceTimelinePage;

	/*
	 * The assigned timeline page shows the user the timeslots that have been assigned to them.
	 */
	CalendarPageTimeslotTimeline displayAssignedTimelinePage;

	/*
	 * Status is used to determine what the announcement should be
	 */
	public String status;


	public HashMap<Integer, String> timeslotPreferences = new HashMap<Integer, String>();

	public Controller() {

		//Sets the status to "Select" by default
		status = "Select";

		//Sets the agent mode to "Social" and the application mode to "Appliance" by default
		settings.setAgentMode("Social");
		settings.setApplicationMode("Appliance");

	}

	/*
	 * startUp() is called when the application is started.
	 */
	public void startUp() {

		displayHomePage();
		
		backend.run();

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

		calendarPagePreferenceSelection = new CalendarPagePreferenceSelection(this, config, settings);

	}

	public void displayPreferenceTimelinePage()
	{

		preferenceTimelinePage = new CalendarPageTimeslotTimeline(this, config, settings);

	}

	public void displayAssignedTimelinePage()
	{
		displayAssignedTimelinePage = new CalendarPageTimeslotTimeline(this, config, settings);
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
				
				announcement = "Please Select your Timeslots for Today!";
				break;

			case "Selecting4":
				
				announcement = "Please Select 4 more timeslots!";
				break;
			
			case "Selecting3":
				
				announcement = "Please Select 3 more timeslots!";
				break;

			case "Selecting2":
				
				announcement = "Please Select 2 more timeslots!";
				break;

			case "Selecting1":
				
				announcement = "Please Select 1 more timeslot!";
				break;

			case "selecting0":
				
				announcement = "Please Submit your Timeslots!";
				break;

			case "Selected":

				announcement = "Timeslots will be assigned shortly!";
				break;

			case "Assigned":

				announcement = "Timeslots have been assigned!";
				break;

			case "Feedback":
				announcement = "Please provide feedback on your assigned timeslots!";
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

	public void PreferenceSubmissionHandler(HashMap<Integer, String> timeslotPreferences)
	{
		this.timeslotPreferences = timeslotPreferences;
		displayPreferenceTimelinePage();
		status = "Selected";
	}

	public void saveAgentPopulation()
	{

		

	}

}
package SociologyInspiredSmartMeter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SociologyInspiredSmartMeter.SmartMeterBackend.SmartMeterBackend;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPageAssignedTimeslots;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPageExchangeInformation;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPageFeedback;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPagePreferenceSelection;
import SociologyInspiredSmartMeter.SmartMeterClient.CalendarPageTimeslotTimeline;
import SociologyInspiredSmartMeter.SmartMeterClient.Config;
import SociologyInspiredSmartMeter.SmartMeterClient.ExchangeMessage;
import SociologyInspiredSmartMeter.SmartMeterClient.HomePage;
import SociologyInspiredSmartMeter.SmartMeterClient.Settings;
import SociologyInspiredSmartMeter.SmartMeterClient.SettingsPage;
import SociologyInspiredSmartMeter.SmartMeterClient.StatisticsPage;

public class Controller {

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
	 * The exchange information page shows the user how their timeslots have been obtained.
	 */
	CalendarPageExchangeInformation displayExchangeInformationPage;

	/*
	 * The feedback page allows the user to provide feedback on their experience.
	 */
	CalendarPageFeedback feedbackPage;

	/*
	 * The assigned timeline page shows the user the timeslots that have been assigned to them.
	 */
	CalendarPageAssignedTimeslots assignedTimelinePage;

	/*
	 * Status is used to determine what the announcement should be
	 */
	public String status;

	public HashMap<Integer, String> timeslotPreferences = new HashMap<Integer, String>();

	public HashMap<Integer, String> timeslotAssignments = new HashMap<Integer, String>();

	public ArrayList<Integer> randomAllocation = new ArrayList<Integer>();

	public ArrayList<String> providedFeedback = new ArrayList<String>();

	public ArrayList<ExchangeMessage> exchangeMessages = new ArrayList<ExchangeMessage>();

	public int trackedAgentID = 1;

	public Controller() {

		//Sets the status to "Select" by default
		status = "Select";

		loadSettings();
		
	}

	/*
	 * startUp() is called when the application is started.
	 */
	public void startUp() {

		displayHomePage();
	
	}

	Thread algorithmThread = new Thread(() -> {
    SmartMeterBackend smartMeterBackend = new SmartMeterBackend();
    smartMeterBackend.smartMeterSimulationRun(this, config, settings);
	});

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
		if(timeslotPreferences.isEmpty())
		{
			calendarPagePreferenceSelection = new CalendarPagePreferenceSelection(this, config, settings);
		} else if (!timeslotPreferences.isEmpty() && timeslotAssignments.isEmpty())
		{
			displayPreferenceTimelinePage();
		} else if (!timeslotAssignments.isEmpty() && (status.equals("Update") || status.equals("Assigned")))
		{
			status = "Update";
			displayPreferenceTimelinePage();
		} else if (status.equals("Exchange"))
		{
			displayExchangeInformationPage();
		} else if (status.equals("Feedback"))
		{
			displayFeedbackPage();
		} else if (status.equals("Submitted"))
		{
			displayAssignedTimelinePage();
		}
	}

	public void displayPreferenceTimelinePage()
	{

		preferenceTimelinePage = new CalendarPageTimeslotTimeline(this, config, settings);

	}

	public void displayExchangeInformationPage()
	{

		displayExchangeInformationPage = new CalendarPageExchangeInformation(this, config, settings);

	}

	/*
	 * displaySettingsPage() is called when the user clicks the settings button.
	 */
	public void displaySettingsPage()
	{
	 	settingsPage = new SettingsPage(this, config, settings);
	}

	/*
	 * displayFeedbackPage() is called when the user clicks the feedback button.
	 */
	public void displayFeedbackPage()
	{
		feedbackPage = new CalendarPageFeedback(this, config, settings);
	}

	public void displayAssignedTimelinePage()
	{
		assignedTimelinePage = new CalendarPageAssignedTimeslots(this, config, settings);
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

			case "Selected":

				announcement = "Timeslots will be assigned shortly!";
				break;

			case "Assigned":

				announcement = "Timeslots have been assigned!";
				break;
	
			//Duplicate Message to allow for the "Update" status to be used to update the user interface.
			case "Update":
		
				announcement = "Timeslots have been assigned!";
				break;

			case "Exchange":
				announcement = "View your timeslot exchange information!";
				break;
				
			case "Feedback":
				announcement = "Please provide feedback on your assigned timeslots!";
				break;

			case "Submitted":

				announcement = "Thank you for your feedback! See you tomorrow!";
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
		status = "Selected";
		displayPreferenceTimelinePage();
		algorithmThread.start();
	}

	//getter and setter for the timeslot preferences
	public HashMap<Integer, String> getTimeslotPreferences()
	{
		return timeslotPreferences;
	}

	public void setTimeslotPreferences(HashMap<Integer, String> timeslotPreferences)
	{
		this.timeslotPreferences = timeslotPreferences;
	}

	//getter and setter for the tracked agent ID
	public int getTrackedAgentID()
	{
		return trackedAgentID;
	}

	public void setTrackedAgentID(int trackedAgentID)
	{
		this.trackedAgentID = trackedAgentID;
	}

	//getter and setter for the timeslot assignments
	public HashMap<Integer, String> getTimeslotAssignments()
	{
		return timeslotAssignments;
	}

	public void setTimeslotAssignments(ArrayList<Integer> assignedTimeslots)
	{

		String assignment = "";
		int timeslot = 0;
		int i = 0;

		for (Map.Entry<Integer, String> entry : timeslotPreferences.entrySet()) {
			assignment = entry.getValue();
			timeslot = assignedTimeslots.get(i);
			timeslotAssignments.put(timeslot, assignment);
			i++;
		}
		exchangeMessageBuilder();
		status = "Update";
	}

	//getter and setter for the agent mode
	public String getAgentMode()
	{
		return settings.getAgentMode();
	}

	public void setAgentMode(String agentMode)
	{
		settings.setAgentMode(agentMode);
	}

	public void saveSettings()
	{
		try {
        FileOutputStream fileOut = new FileOutputStream("settings.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(settings);
        out.close();
        fileOut.close();
        System.out.println("Serialized data is saved in settings.ser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSettings()
	{
		try {
        FileInputStream fileIn = new FileInputStream("settings.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        settings = (Settings) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("Deserialized data is loaded from settings.ser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFeedback()
	{
		try {
			FileWriter writer = new FileWriter(SmartMeterBackend.dataOutputFolder + "/feedback.csv");
			writer.append("Metric,Feedback\n");
			int i = 0;
			for (String feedback: providedFeedback)
			{
				writer.append(config.getFeedbackMetrics().get(i) + "," + feedback + "\n");
				i++;
			}
			writer.close();
		}catch
			(FileNotFoundException ex) {
			System.out.println("Process incomplete. Skipping file saving.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exchangeMessageBuilder()
	{
		for (Map.Entry<Integer, String> entry : timeslotAssignments.entrySet()) {
			{

				ExchangeMessage message = new ExchangeMessage();

				for(Integer rTimeslot : randomAllocation)
				{
					if(rTimeslot == entry.getKey())
					{
						message.setTimeslot(entry.getKey());
						message.setMessage("You were randomly allocated your " + entry.getKey() + ":00 - " + (entry.getKey() + 1) + ":00 " + "timeslot.");
						message.setConnotation("Neutral");
	
					} else {
						message.setTimeslot(entry.getKey());
						message.setMessage("Your agent made an exchange for your " + entry.getKey() + ":00 - " + (entry.getKey() + 1) + ":00 " + "timeslot.");
						message.setConnotation("Positive");
					}

				}

				exchangeMessages.add(message);

			}
		}
	}

	public ArrayList<ExchangeMessage> getExchangeMessages()
	{
		return exchangeMessages;
	}

	public void startNewDay()
	{
		status = "Select";
		timeslotPreferences.clear();
		timeslotAssignments.clear();
		exchangeMessages.clear();
		providedFeedback.clear();
		algorithmThread = new Thread(() -> {
    		SmartMeterBackend smartMeterBackend = new SmartMeterBackend();
    		smartMeterBackend.smartMeterSimulationRun(this, config, settings);
		});
		displayHomePage();
	}
}
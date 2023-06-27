package SociologyInspiredSmartMeter.SmartMeterClient;

import java.util.ArrayList;

/*
 * Config class to store the preferences that are set by those running the experiment.
 * Responsible for setting the application mode, and the metrics to be used in the feedback page.
 * 
 */
public class Config {
	
	/*
	 * applicationMode is a String value that determines if the user is selecting appliances and timeslots or just timeslots.
	 * "Appliance" allows the user to select appliances and timeslots.
	 * "Generic" allows the user to only select timeslots.
	 */
	String applicationMode;

	/*
	 * feedbackMetrics is a String ArrayList that determines that holds the metrics to be used in the feedback page.
	 * Metrics are as follows:
	 * 
	 */
	ArrayList<String> feedbackMetrics = new ArrayList<String>();

	/*
	 * Constructor for the config class.
	 * @param applicationMode is a String value that determines if the user is selecting appliances and timeslots or just timeslots.
	 */
	public Config() {

		applicationMode = "Appliance";

		feedbackMetrics.add("Config Metric");
		feedbackMetrics.add("Config Metric");
		feedbackMetrics.add("Config Metric");
		
	}

	public String getApplicationMode() {
		return applicationMode;
	}

	public void setApplicationMode(String applicationMode) {
		this.applicationMode = applicationMode;
	}

	public ArrayList<String> getFeedbackMetrics() {
		return feedbackMetrics;
	}

	public void setFeedbackMetrics(ArrayList<String> feedbackMetrics) {
		this.feedbackMetrics = feedbackMetrics;
	}

}
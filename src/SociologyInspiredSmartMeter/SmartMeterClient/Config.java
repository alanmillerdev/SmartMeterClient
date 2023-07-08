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
	 * Fairness
	 * Trust
	 * Satisfaction
	 * Control
	 * Engagement
	 * Motivation
	 * Enjoyment
	 * Understanding
	 */
	ArrayList<String> feedbackMetrics = new ArrayList<String>();

	ArrayList<String> feedbackMetricsDescriptions = new ArrayList<String>();

	/*
	 * Constructor for the config class.
	 * @param applicationMode is a String value that determines if the user is selecting appliances and timeslots or just timeslots.
	 * @param feedbackMetrics is a String ArrayList that determines that holds the metrics to be used in the feedback page.
	 * Each metric that is to be used in the feedback page is added to the ArrayList.
	 * Each metric used should also have a description added to the feedbackMetricsDescriptions ArrayList.
	 */
	public Config() {

		applicationMode = "Appliance";

		feedbackMetrics.add("Fairness");
		feedbackMetricsDescriptions.add("Fairness: How fair was the timeslot allocation?");
		feedbackMetrics.add("Satisfaction");
		feedbackMetricsDescriptions.add("Satisfaction: How satisfied were you with the outcome of the timeslot allocation?");
		feedbackMetrics.add("Understanding");
		feedbackMetricsDescriptions.add("Understanding: How well did you understand the timeslot allocation process?");
		
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
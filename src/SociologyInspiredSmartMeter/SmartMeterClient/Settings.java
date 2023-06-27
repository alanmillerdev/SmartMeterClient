package SociologyInspiredSmartMeter.SmartMeterClient;

import java.io.Serializable;

/*
 * Settings class is used to store the settings of the agent client.
 * The settings are stored in a settings object and passed to the agent client.
 * The settings object is  passed to the agent client to determine if the user is selecting appliances and timeslots or just timeslots.
 * The settings object is also passed to the agent client to determine if the agent is taking the social approach or the selfish approach.
 */
public class Settings implements Serializable{
	
    /*
     * agentMode is a String value that determines which approach the agent is taking.
     * "Social" is the social approach.
     * "Selfish" is the selfish approach.
     */
	String agentMode;

    /*
     * applicationMode is a String value that determines if the user is selecting appliances and timeslots or just timeslots.
     * "Appliance" allows the user to select appliances and timeslots.
     * "Generic" allows the user to only select timeslots.
     */
    String applicationMode;

    /*
     * Default constructor for the settings class.
     */
    public Settings() {
    }

    /*
     * Constructor for the settings class.
     * @param agentMode is a String value that determines which approach the agent is taking.
     * @param applianceSelection is a Boolean value that determines if the user is selecting appliances and timeslots or just timeslots.
     */
    public Settings(String agentMode, String applicationMode) {
        this.agentMode = agentMode;
        this.applicationMode = applicationMode;
    }

    public String getAgentMode() {
        return agentMode;
    }

    public void setAgentMode(String agentMode) {
        this.agentMode = agentMode;
    }

    public String getApplicationMode() {
        return applicationMode;
    }

    public void setApplicationMode(String applicationMode) {
        this.applicationMode = applicationMode;
    }

}
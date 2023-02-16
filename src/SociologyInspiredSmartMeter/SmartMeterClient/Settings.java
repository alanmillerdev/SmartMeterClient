package SociologyInspiredSmartMeter.SmartMeterClient;

/*
 * Settings class is used to store the settings of the agent client.
 * The settings are stored in a settings object and passed to the agent client.
 * The settings object is  passed to the agent client to determine if the user is selecting appliances and timeslots or just timeslots.
 * The settings object is also passed to the agent client to determine if the agent is taking the social approach or the selfish approach.
 */
public class Settings {
	
    /*
     * agentMode is a String value that determines which approach the agent is taking.
     * "Social" is the social approach.
     * "Selfish" is the selfish approach.
     */
	String agentMode;

    /*
     * applianceSelection is a Boolean value that determines if the user is selecting appliances and timeslots or just timeslots.
     * True (A Mode) allows the user to select appliances and timeslots.
     * False (B Mode) allows the user to only select timeslots.
     */
    Boolean applianceSelection;

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
    public Settings(String agentMode, Boolean applianceSelection) {
        this.agentMode = agentMode;
        this.applianceSelection = applianceSelection;
    }

    public String getAgentMode() {
        return agentMode;
    }

    public void setAgentMode(String agentMode) {
        this.agentMode = agentMode;
    }

    public Boolean getApplianceSelection() {
        return applianceSelection;
    }

    public void setApplianceSelection(Boolean applianceSelection) {
        this.applianceSelection = applianceSelection;
    }

}

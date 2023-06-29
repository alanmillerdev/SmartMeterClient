package SociologyInspiredSmartMeter.SmartMeterClient;

public class ExchangeMessage {

    public Integer timeslot;
    public String message;
    public String connotation; // positive, neutral, negative

    public ExchangeMessage() {
    }

    //Create a constructor for the class
    public ExchangeMessage(Integer timeslot, String message, String connotation) {
        this.timeslot = timeslot;
        this.message = message;
        this.connotation = connotation;
    }

    //Create getters and setters for all variables
    public Integer getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Integer timeslot) {
        this.timeslot = timeslot;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConnotation() {
        return connotation;
    }

    public void setConnotation(String connotation) {
        this.connotation = connotation;
    }
    
}



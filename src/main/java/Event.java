public class Event extends Task {

    private String eventTiming;

    public Event(String description, String eventTiming){
        super(description);
        this.eventTiming = eventTiming;
    }

    public String getEventTiming(){
        return this.eventTiming;
    }

    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.getEventTiming() + ")";
    }

}

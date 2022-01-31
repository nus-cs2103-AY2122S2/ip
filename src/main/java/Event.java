/**
 * Represents an Event object
 */
public class Event extends Task {
    private String metaInfo;

    /**
     * Default constructor
     * @param description description of the task
     * @param metaInfo duration information of the task
     */
    public Event(String description, String metaInfo) {
        super(description);
        this.metaInfo = metaInfo;
    }

    
    /** 
     * @return String "[E]" task icon
     */
    @Override
    public String getTaskIcon() {
        return "[E]";
    }

    
    /** 
     * @return String default string representation of an event
     */
    @Override
    public String toString() {
        return super.toString() + "(at:" + this.metaInfo + ")";
    }
}
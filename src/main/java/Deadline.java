/**
 * Represents a Deadline object
 */
class Deadline extends Task {
    private String metaInfo;

    /**
     * Default constructor
     * @param description description of the task
     * @param metaInfo deadline information of the task
     */
    public Deadline(String description, String metaInfo) {
        super(description);
        this.metaInfo = metaInfo;
    }

    
    /** 
     * @return String "[D]" task icon
     */
    @Override
    public String getTaskIcon() {
        return "[D]";
    }

    
    /** 
     * @return String default string representation of a deadline
     */
    @Override
    public String toString() {
        return super.toString() + "(by:" + this.metaInfo + ")";
    }
}
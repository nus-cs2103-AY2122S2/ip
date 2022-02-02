/**
 * Represents a Deadline object
 */
class Deadline extends Task {
    private String metaInfo;

    /**
     * Default constructor
     * 
     * @param description description of the task
     * @param metaInfo    deadline information of the task
     */
    public Deadline(String description, String metaInfo) {
        super(description, "D");
        this.metaInfo = metaInfo;
    }

    /**
     * This constructor initializes deadline objects with isDone specified
     * @param isDone whether this task is done or not
     * @param description description of the task
     * @param metaInfo deadline information of the task
     */
    public Deadline(boolean isDone, String description, String metaInfo) {
        super(description, "D");
        if (isDone) {
            setDone();
        } else {
            setUndone();
        }
        this.metaInfo = metaInfo;
    }

    /**
     * @return String default string representation of a deadline
     */
    @Override
    public String toString() {
        return super.toString() + "(by:" + this.metaInfo + ")";
    }

    
    /** 
     * @return String string representation of this task to be saved to file
     */
    @Override
    public String getPrintString() {
        return super.getPrintString() + "|" + this.metaInfo;
    }
}
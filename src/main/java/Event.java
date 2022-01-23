public class Event extends Task {
    /**
     * The time of this event
     */
    private String eventTime;

    /**
     * Constructor for Event objects
     *
     * @param eventName  the event name
     * @param eventTime  time of the event
     */
    public Event(String eventName, String eventTime) {
        super(eventName);
        this.eventTime = eventTime;
    }

    /**
     * Constructor for Event objects
     *
     * @param eventName the event name
     * @param isDone    if the event is done
     * @param eventTime the time of the event
     */
    public Event(String eventName, boolean isDone, String eventTime) {
        super(eventName, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Returns a String representation of the Event
     *
     * @return  Event in String
     */
    @Override
    public String toString() {
        String box1 = "[E]";
        String doneness;
        if (super.isDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String eventName = super.toString();
        String time = " (at: " + eventTime + ")";
        return box1 + doneness + eventName + time;
    }

    /**
     * Converts the strings in mem to an Event
     *
     * @param taskStr   the string in mem
     */
    public static Event memToTask(String taskStr) {
        // Example str = E|0|return book|June 6th
        String[] args = taskStr.split("|");
        boolean isDone = args[1].equals('1');
        return new Event(args[2], isDone, args[3]);
        /**
         try {

         } catch (IndexOutOfBoundsException e) {
         throw new MemoryCorruptedException();
         }
         */
    }

    /**
     * Converts Event into a string to be stored in mem
     *
     * @return string   string of event to be stored in mem
     */
    @Override
    public String taskToMemStr() {
        // Example str = E|0|return book|June 6th
        StringBuilder sb = new StringBuilder();
        sb.append("E|");
        if (this.isDone()) {
            sb.append("1|");
        } else {
            sb.append("0|");
        }
        sb.append(super.toString());
        sb.append("|");
        sb.append(this.eventTime);
        return sb.toString();
    }
}

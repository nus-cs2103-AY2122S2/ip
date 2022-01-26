package duke.task;

public class Event extends Task {

    private String eventTiming;

    public Event(String description, String eventTiming){
        super(description, "E");
        this.eventTiming = eventTiming;
    }

    public String getEventTiming(){
        return this.eventTiming;
    }

    /**
     * returns deadline in save data format (format to be saved in data.txt file for task storage)
     *
     * @return
     */
    @Override
    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription(), this.getEventTiming());
    }

    @Override
    public String toString() {
        return super.toString() + " (At: " + this.getEventTiming() + ")";
    }

}

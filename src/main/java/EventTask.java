public class EventTask extends Task{
    protected String eventTime;

    public EventTask(String title, String eventTime){
        super(title);
        this.type = TaskType.EVENT;
        this.eventTime = eventTime;
    }
    public EventTask(String title, Boolean isDone, String eventTime){
        super(title, isDone);
        this.type = TaskType.EVENT;
        this.eventTime = eventTime;
    }

    public String getEventTime(){
        return "(by:" + eventTime + ")";
    }

    @Override
    public String toString(){
        return this.title + " " + getEventTime();
    }
}

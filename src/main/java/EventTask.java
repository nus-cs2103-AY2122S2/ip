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
        return "(at:" + eventTime + ")";
    }

    public String toOutputLine(){
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title + "|" + eventTime;
    }

    @Override
    public String toString(){
        return this.title + " " + getEventTime();
    }
}

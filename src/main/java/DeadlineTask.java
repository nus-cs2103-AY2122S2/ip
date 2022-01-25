public class DeadlineTask extends Task{
    protected String byTime;

    public DeadlineTask(String title, String byTime){
        super(title);
        this.type = TaskType.DEADLINE;
        this.byTime = byTime;
    }
    public DeadlineTask(String title, Boolean isDone, String byTime){
        super(title, isDone);
        this.type = TaskType.DEADLINE;
        this.byTime = byTime;
    }

    public String getByTime(){
        return "(by:" + byTime + ")";
    }

    public String toOutputLine(){
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title + "|" + byTime;
    }

    @Override
    public String toString(){
        return this.title + " " + getByTime();
    }
}

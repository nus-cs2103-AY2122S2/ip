public class DeadlineTask extends Task{
    protected String byTime;

    public DeadlineTask(String title, String byTime){
        super(title);
        this.type = "D";
        this.byTime = byTime;
    }
    public DeadlineTask(String title, Boolean isDone, String byTime){
        super(title, isDone);
        this.type = "D";
        this.byTime = byTime;
    }

    public String getByTime(){
        return "(by:" + byTime + ")";
    }
    @Override
    public String toString(){
        return this.title + " " + getByTime();
    }
}

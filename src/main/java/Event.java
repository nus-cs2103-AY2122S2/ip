public class Event extends Task{
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    public Event(String description, String time, boolean isDone) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        return "E" + " | " + status + " | " + this.description + " | " + this.time + "\n";
    }
}

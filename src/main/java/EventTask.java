public class EventTask extends Task{
    private String location;

    EventTask(String ss, String at) {
        this.taskName = ss;
        this.isDone = false;
        this.location = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at:%s)", this.isDone?"X":" ", this.taskName, this.location);
    }

}

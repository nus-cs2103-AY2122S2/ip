public class EventTask extends Task{

    EventTask(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s", this.isDone?"X":" ", this.taskName);
    }

}

public class DeadlineTask extends Task{
    DeadlineTask(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s", this.isDone?"X":" ", this.taskName);
    }
}

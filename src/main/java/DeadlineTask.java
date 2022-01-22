public class DeadlineTask extends Task{
    private String by;

    DeadlineTask(String ss, String by) {
        this.taskName = ss;
        this.isDone = false;
        this.by = by;
    }

    DeadlineTask(String ss, boolean b, String by) {
        this.taskName = ss;
        this.isDone = b;
        this.by = by;
    }

    public String getDesc() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by:%s)", this.isDone?"X":" ", this.taskName, this.by);
    }
}

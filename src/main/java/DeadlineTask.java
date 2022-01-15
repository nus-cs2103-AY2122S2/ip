public class DeadlineTask extends Task{
    String deadline;

    public DeadlineTask(String taskname, String deadline) {
        super(taskname);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}



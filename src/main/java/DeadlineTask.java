public class DeadlineTask extends Task {
    private String time;

    public DeadlineTask(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(%s)", this.time.replaceFirst(" ", ": "));
    }
}

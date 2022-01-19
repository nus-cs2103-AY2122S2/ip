public class Deadline extends Task {
    private String deadline;

    public Deadline() {
        super();

        this.deadline = "";
    }

    public Deadline(String taskDescription, String eventTime) {
        super(taskDescription);

        this.deadline = eventTime;
    }

    @Override
    public String toString() {
        sb.setLength(0);
        sb.append("[D]").append("[").append(this.isDone ? "X] " : " ] ").append(this.taskDescription);
        sb.append("\n (by: ").append(this.deadline).append(")");

        return sb.toString();
    }
}

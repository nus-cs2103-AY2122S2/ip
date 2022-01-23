public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String time) {
        super(description);
        this.deadline = time;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]")
                .append(super.toString())
                .append(" (by: ")
                .append(this.deadline)
                .append(")");
        return sb.toString();
    }
}

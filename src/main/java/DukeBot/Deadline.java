package DukeBot;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super("D", description);
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        String saveText = String.format("D|%s|%s|%s", complete, this.getDescription(), this.deadline);
        return saveText;
    }

}

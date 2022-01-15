package DukeBot;

public class Deadline extends Task {

    public String deadline;

    public Deadline(String description, String deadline) {
        super("D", description);
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString() + "(by: " + deadline + ")";
    }

}

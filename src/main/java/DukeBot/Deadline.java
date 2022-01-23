package DukeBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super("D", description);
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString() + "(by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("D|%s|%s|%s", complete, this.getDescription(), this.deadline.toString());
    }

}

package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime date;

    public Deadline(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}

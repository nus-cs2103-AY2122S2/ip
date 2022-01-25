package duke.task;

import duke.common.Const;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime time) {
        super(description, Task.Type.DEADLINE);
        this.by = time;
    }

    public String getBy() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(Const.OUT_TIME_FORMATTER) + ")";
    }
}

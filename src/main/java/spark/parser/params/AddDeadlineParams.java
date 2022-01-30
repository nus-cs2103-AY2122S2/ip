package spark.parser.params;

import java.time.LocalDateTime;

public class AddDeadlineParams extends AddTaskParams {
    protected LocalDateTime by;

    public AddDeadlineParams(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }
}

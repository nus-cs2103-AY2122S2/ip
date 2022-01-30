package spark.parser.params;

import java.time.LocalDateTime;

public class AddEventParams extends AddTaskParams {
    protected LocalDateTime at;

    public AddEventParams(String title, LocalDateTime at) {
        super(title);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }
}

package duke.command;

import java.time.LocalDateTime;

/**
 * class for time in a command, especially for event command
 */
public class TimeCommand extends DescriptionCommand {
    public LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

package duke.command;

import java.time.LocalDateTime;

/**
 * class for time in a command, especially for event command
 */
public class TimeCommand extends DescriptionCommand {
    public LocalDateTime time;

    /**
     * Get the local time of a task
     * @return
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Set a local time of a task
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

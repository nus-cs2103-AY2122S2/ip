package duke.command;

import java.time.LocalDateTime;

/**
 * class for time duration in a command, especially for deadline command
 */
public class DurationCommand extends DescriptionCommand {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     *
     * @return date time of a task
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
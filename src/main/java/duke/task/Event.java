package duke.task;

import java.time.LocalDate;

/**
 * Represents a task which is of type event.
 */
public class Event extends Task {

    protected LocalDate at;
    private String type;

    /**
     * Constructor for this class.
     * @param description Task description.
     * @param at Event date.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    /**
     * Task description that is formatted to be written into the file.
     *
     * @return Task description format for file input.
     */
    @Override
    public String taskDescriptionForFile() {
        int i = super.isDone ? 1 : 0;
        return "E , " + i + " , "
                + this.description.trim() + " , " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(super.getOutputDateFormat()) + ")";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setDate(LocalDate localDate) {
        this.at = localDate;
    }
}

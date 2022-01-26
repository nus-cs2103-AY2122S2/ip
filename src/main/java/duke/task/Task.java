package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores the information about the task
 */
public class Task {
    private final String description;
    private boolean isComplete;
    private final String type;
    private LocalDate time;
    private String timeCommand;

    public Task(String description, String type) {
        int slashIndex = parseDescription(description, type);
        this.description = description.substring(0, slashIndex);
        this.isComplete = false;
        this.type = type;
    }

    /**
     * Parses the description into smaller components, such as time and the time command
     * and returns the index at the start of the time command
     *
     * @param description The description of the task
     * @param type The type of the task
     * @return The index at the start of time command
     */
    private int parseDescription(String description, String type) {
        int slashIndex;
        int command;
        if (! type.equals("T")) {
            slashIndex = description.indexOf("/");
            command = description.indexOf(" ", slashIndex);
            String timeString = description.substring(command + 1);
            this.time = LocalDate.parse(timeString,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            this.timeCommand = description.substring(slashIndex + 1, command);
        } else {
            slashIndex = description.length();
            this.timeCommand = "";
        }
        return slashIndex;
    }

    /**
     * Changes the task state to become completed
     */
    public void taskDone() {
        this.isComplete = true;
    }

    /**
     * Changes the task state to become uncompleted
     */
    public void taskUndone() {
        this.isComplete = false;
    }

    /**
     * Returns the string used to append to the database file
     *
     * @return The string with the database file format
     */
    public String toFileText() {
        String done;
        String timeDue;
        if (isComplete) {
            done = "1";
        } else {
            done = "0";
        }

        if (this.timeCommand.equals("")) {
            timeDue = " |";
        } else {
            timeDue = "| " + timeCommand + " " +
                    this.time.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " |";
        }

        return "| " + type + " | " + done + " | " + description + timeDue;
    }

    public LocalDate getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Returns the display string of the task
     *
     * @return The string that describes the task
     */
    @Override
    public String toString() {
        String temp = "[ ]";
        if (isComplete) {
            temp = "[X]";
        }
        String timeCommand = "";
        if (! this.timeCommand.equals("")) {
            timeCommand = "(" + this.timeCommand + ": " +
                    time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[" + type + "]" + temp + " " + description + timeCommand;
    }
}

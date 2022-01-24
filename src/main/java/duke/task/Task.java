package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String description;
    private boolean isComplete;
    private final String type;
    private LocalDate time;
    private final String command;

    public Task(String description, String type) {
        int slashIndex;
        int command;
        if (! type.equals("T")) {
            slashIndex = description.indexOf("/");
            command = description.indexOf(" ", slashIndex);
            String timeString = description.substring(command + 1);
            this.time = LocalDate.parse(timeString,
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            this.command = description.substring(slashIndex + 1, command);
        } else {
            slashIndex = description.length();
            this.command = "";
        }
        this.description = description.substring(0, slashIndex);
        this.isComplete = false;
        this.type = type;
    }

    public void taskDone() {
        this.isComplete = true;
    }

    public void taskUndone() {
        this.isComplete = false;
    }

    public String toFileText() {
        String done;
        String timeDue;
        if (isComplete) {
            done = "1";
        } else {
            done = "0";
        }

        if (this.command.equals("")) {
            timeDue = " |";
        } else {
            timeDue = "| " + command + " " +
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

    @Override
    public String toString() {
        String temp = "[ ]";
        if (isComplete) {
            temp = "[X]";
        }
        String timeCommand = "";
        if (! command.equals("")) {
            timeCommand = "(" + command + ": " +
                    time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[" + type + "]" + temp + " " + description + timeCommand;
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskStorage {
    private String description;
    private boolean complete;
    private String type;
    private LocalDate time;
    private String command;

    public TaskStorage(String description, String type) {
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
        this.complete = false;
        this.type = type;
    }

    public void taskDone() {
        this.complete = true;
    }

    public void taskUndone() {
        this.complete = false;
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
        if (complete) {
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

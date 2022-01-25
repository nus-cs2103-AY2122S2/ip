package duke.io;

public class UserInput {
    private String type = "";
    private String command = "";
    private String description = "";
    private String time = "";
    private boolean isDone;

    public UserInput() {
        this.isDone = false;
    }

    public UserInput(String type, String command, String description, boolean isDone) {
        this.type = type;
        this.command = command;
        this.description = description;
        this.isDone = isDone;
    }

    public UserInput(String type, String command, String description, String time, boolean isDone) {
        this.type = type;
        this.command = command;
        this.description = description;
        this.time = time;
        this.isDone = isDone;
    }

    public String getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return this.time;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
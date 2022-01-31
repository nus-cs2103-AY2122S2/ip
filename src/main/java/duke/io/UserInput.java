package duke.io;

/**
 * This is a UserInput class that store the user's input to Duke.
 * This class helps Duke to make sense of the user's input.
 */
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

    /**
     * A method to get the command that the user is calling.
     *
     * @return String of the command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * A method to get the description of a task.
     *
     * @return String of the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A method to get the time of a task.
     * @return String of the time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * A method to check if a task is done.
     * @return Boolean value of the task being done or undone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Set the user's input command as another.
     * @param command Another command in String
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Set the user's input description as another.
     * @param description Another description in String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the user's input time as another.
     * @param time Another time in String
     */
    public void setTime(String time) {
        this.time = time;
    }
}

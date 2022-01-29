package duke;

/**
 * Represents the ToDos class that inherits from Task class.
 */
public class ToDos extends Task {

    /**
     * Constructor used to instantiate a ToDos class normally.
     *
     * @param description A String representing the description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * An alternative constructor used to instantiate a ToDos class when loading from a pre-existing duke.txt file
     * at the start of a new duke session.
     *
     * @param mark An integer indicating if the task was done or not.
     * @param description A String representing the description of the task.
     */
    public ToDos(int mark, String description) {
        super(description, mark);
    }

    /**
     * Returns the String representation of the ToDos class for user to read.
     *
     * @return A String representing the ToDos class for the user.
     */
    public String getToDo() {
        return "[T]" + this.getTask() + "\n";
    }

    /**
     * Returns the String presentation of the ToDos class for writing into the duke.txt file.
     *
     * @return A String representing the ToDos class for the duke.txt file.
     */
    public String getFormattedText() {
        return "T>" + this.getMark() + ">" + this.getDescription();
    }
}

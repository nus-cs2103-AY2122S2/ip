package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a todo, deadline or event command, adding to the task list.
 */
public class AddCommand extends Command {

    private final String description;
    private final String time;

    /**
     * Constructs an instance of the AddCommand class when a command is given without time.
     *
     * @param command A string containing the word "todo".
     * @param description A string containing the task description.
     */
    public AddCommand(String command, String description) {
        super(command);
        this.description = description;
        this.time = "";
    }

    /**
     * Constructs an instance of the AddCommand class when a command is given with time.
     *
     * @param command A string containing either the word "deadline" or "event".
     * @param description A string containing the task description.
     * @param time A string representing the time, formatted as "yyyy-mm-dd".
     */
    public AddCommand(String command, String description, String time) {
        super(command);
        this.description = description;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, PlaceList places, Ui ui,
                          Storage storageTask, Storage storagePlace) throws IOException{
        try {
            Task task;
            switch (this.getFirstWord()) {
            case "todo":
                task = new Todo(this.description);
                break;
            case "deadline":
                task = new Deadline(this.description, this.time);
                break;
            case "event":
                task = new Event(this.description, this.time);
                break;
            default:
                return "Please try again!";
            }
            String taskString = task.toString();
            tasks.add(task);
            storageTask.update(tasks);
            return getOutput(taskString, tasks.size());
        } catch (DateTimeParseException e) {
            return "Please type a valid date! (Format: YYYY-MM-DD)";
        }
    }

    /**
     * Generates the output based on the task and list size
     *
     * @param task the String representation of the task.
     * @param size an integer representing the size of the task list.
     * @return a string representing the response to the user input after successful addition of the task.
     */
    public String getOutput(String task, int size) {
        return "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                "Now you have " + size + " tasks in the list.";
    }
}

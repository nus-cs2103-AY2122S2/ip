package duke;

import java.io.IOException;
import java.time.LocalDate;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            LocalDate parsedTime;
            String formattedTime;
            switch (this.getFirstWord()) {
            case "todo":
                Task taskTodo = new Todo(this.description);
                tasks.add(taskTodo);
                sb.append("Got it. I've added this task:\n");
                sb.append("  ").append(taskTodo);
                sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            case "deadline":
                Task taskDeadline = new Deadline(this.description, this.time);
                tasks.add(taskDeadline);
                sb.append("Got it. I've added this task:\n");
                sb.append("  ").append(taskDeadline).append("\n");
                sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            case "event":
                Task taskEvent = new Event(this.description, this.time);
                tasks.add(taskEvent);
                sb.append("Got it. I've added this task:\n");
                sb.append("  ").append(taskEvent).append("\n");
                sb.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
                break;
            default:
                // pass through
                break;
            }
            storage.update(tasks);
            return sb.toString();
        } catch (DateTimeParseException e) {
            return "Please type a valid date! (Format: YYYY-MM-DD)";
        }
    }
}

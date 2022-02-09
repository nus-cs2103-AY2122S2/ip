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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            LocalDate parsedTime;
            String formattedTime;
            switch (this.getFirstWord()) {
            case "todo":
                Task taskTodo = new Todo(this.description);
                tasks.add(taskTodo);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskTodo);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                Task taskDeadline = new Deadline(this.description, this.time);
                tasks.add(taskDeadline);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskDeadline);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                Task taskEvent = new Event(this.description, this.time);
                tasks.add(taskEvent);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskEvent);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
        }
    }
}

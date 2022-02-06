package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Deadline;
import mickey.task.Event;
import mickey.task.Task;
import mickey.task.TaskList;
import mickey.task.ToDo;

import java.time.format.DateTimeParseException;

/**
 * Add command to create todos, deadlines and events.
 */
public class AddCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public AddCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Execute command.
     *
     * @param tasks List of tasks.
     * @param ui Ui to print feedback.
     * @param storage Storage to store tasks.
     * @return Response after executing command.
     * @throws MickeyException Exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        Task newTask = null;
        switch (cmd) {
            case "todo":
                newTask = new ToDo(getDescription());
                tasks.add(newTask);
                return ui.showNewTask(tasks.size(), newTask, "todo");
            case "deadline":
                try {
                    newTask = new Deadline(getDescription(), getDate());
                    tasks.add(newTask);
                    return ui.showNewTask(tasks.size(), newTask, "deadline");
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date");
                }
                break;
            case "event":
                try {
                    newTask = new Event(getDescription(), getDate());
                    tasks.add(newTask);
                    return ui.showNewTask(tasks.size(), newTask, "event");
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date");
                }
                break;
            default:
        }
        return "";
    }
}

package kenobi.command;

import kenobi.task.Deadline;
import kenobi.task.Event;
import kenobi.task.Task;
import kenobi.task.TaskException;
import kenobi.task.ToDo;

import java.time.LocalDate;

/**
 * The AddCommand class encapsulates the command to add a Task to a given TaskList.
 */
public class AddCommand extends Command {
    private final Task.Type taskType;
    private final String taskName;
    private final LocalDate taskDate;

    /**
     * Constructs an AddCommand with the given name for the new task.
     *
     * @param name The name of the Task to be added.
     */
    public AddCommand(String name) {
        taskType = Task.Type.TODO;
        taskName = name;
        taskDate = null;
    }

    /**
     * Constructs an AddCommand with the given name, date, and type of task for the new task.
     *
     * @param type The type of Task to be added.
     * @param name The name of Task to be added.
     * @param date The date attached to the Task to be added.
     */
    public AddCommand(Task.Type type, String name, LocalDate date) {
        taskType = type;
        taskName = name;
        taskDate = date;
    }

    /**
     * Executes the addition of a Task into the TaskList.
     *
     * @return a feedback from the execution of the command.
     */
    public String execute() {
        Task toAdd;

        try {
            switch (taskType) {
            case DEADLINE:
                toAdd = new Deadline(taskName, taskDate);
                break;

            case EVENT:
                toAdd = new Event(taskName, taskDate);
                break;

            default:
            case TODO:
                toAdd = new ToDo(taskName);
                break;
            }
        } catch (TaskException e) {
            return e.getMessage();
        }

        tasks.add(toAdd);
        return "I added the following task to the archives:\n" + toAdd;
    }
}

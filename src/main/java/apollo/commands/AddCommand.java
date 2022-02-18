package apollo.commands;

import static apollo.parser.Parser.PATTERN;

import java.time.LocalDateTime;

import apollo.tasks.Deadline;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.Todo;

/**
 * Adds a {@code Task} to the taskList.
 * Extends {@code Command} superclass.
 */
public class AddCommand extends Command {

    public static final String HELP_ADD_TODO = "Add Todo to taskList: todo <DESCRIPTION>";
    public static final String HELP_ADD_DEADLINE = "Add Deadline to taskList: deadline <DESCRIPTION> /by " + PATTERN;
    public static final String HELP_ADD_EVENT = "Add Event to taskList: event <DESCRIPTION> /at " + PATTERN;
    public static final String HELP_ADD_COMMAND = "Add a task using: \n\n"
            + HELP_ADD_TODO + "\n" + HELP_ADD_DEADLINE + "\n" + HELP_ADD_EVENT;
    private final String description;
    private final LocalDateTime dateTime;
    private final Task.Type taskType;

    /**
     * Constructor for {@code AddCommand}.
     *
     * @param description Of task to be added.
     * @param dateTime Due date and time of the task.
     * @param taskType Type of task to be added.
     */
    public AddCommand(String description, LocalDateTime dateTime, Task.Type taskType) {
        this.description = description;
        this.dateTime = dateTime;
        this.taskType = taskType;
    }

    /**
     * Adds new {@code Task} base on {@code taskType}.
     * Tasks are added to the taskList.
     *
     * @return Message for successful execution.
     */
    @Override
    public String execute() {
        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new Todo(description);
            break;
        case DEADLINE:
            newTask = new Deadline(description, dateTime);
            break;
        case EVENT:
            newTask = new Event(description, dateTime);
            break;
        default:
            assert false : "Unknown task type.";
        }

        taskList.addTask(newTask);
        return String.format("I've added this task:\n  %s\n"
                        + "There's a total of %d tasks now. ",
                newTask, taskList.taskCount());
    }

    public static String getHelp() {
        return null;
    }
}

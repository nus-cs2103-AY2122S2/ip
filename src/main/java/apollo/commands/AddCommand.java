package apollo.commands;

import apollo.tasks.Deadline;
import apollo.tasks.Event;
import apollo.tasks.Task;
import apollo.tasks.Todo;

import java.time.LocalDateTime;

/**
 * Adds a {@code Task} to the taskList.
 * Extends {@code Command} superclass.
 */
public class AddCommand extends Command {

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
        }

        taskList.addTask(newTask);
        return String.format("I've added this task:\n  %s\n"
                        + "There's a total of %d tasks now. ",
                newTask, taskList.taskCount());
    }
}

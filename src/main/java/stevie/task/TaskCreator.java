package stevie.task;

import java.util.Date;

import stevie.exception.TaskException;
import stevie.exception.messages.TaskExceptionMessages;
import stevie.task.types.DeadlineTask;
import stevie.task.types.EventTask;
import stevie.task.types.Task;
import stevie.task.types.TaskType;
import stevie.task.types.ToDoTask;

/**
 * Class to handle the creation of tasks. When a new task is added, update or add a new create method in
 * TaskCreator in order to support the construction of the new task.
 */
public class TaskCreator {
    /**
     * Creates a new task based on the type. Tasks can have name and/or a date.
     *
     * @param type     type of the task
     * @param done     true if task is already done, else false
     * @param taskName name of the task
     * @param date     date of the task
     * @return newly constructed task if all arguments supplied are valid
     * @throws TaskException if the type of the task is invalid, or unsupported
     */
    public static Task create(TaskType type, boolean done, String taskName, Date date) throws TaskException {
        Task newTask;
        switch (type) {
        case Todo:
            newTask = new ToDoTask(taskName);
            break;
        case Event:
            newTask = new EventTask(taskName, date);
            break;
        case Deadline:
            newTask = new DeadlineTask(taskName, date);
            break;
        default:
            throw new TaskException(TaskExceptionMessages.InvalidTaskTypeError);
        }
        assert newTask != null;
        if (done) {
            newTask.done();
        }
        return newTask;
    }

    /**
     * /**
     * Creates a new task based on the type. Tasks can only have a name.
     *
     * @param type     type of the task
     * @param done     true if task is already done, else false
     * @param taskName name of the task
     * @return newly constructed task if all arguments supplied are valid
     * @throws TaskException if the type of the task is invalid, or unsupported
     */
    public static Task create(TaskType type, boolean done, String taskName) throws TaskException {
        Task newTask;
        switch (type) {
        case Todo:
            newTask = new ToDoTask(taskName);
            break;
        default:
            throw new TaskException(TaskExceptionMessages.InvalidTaskTypeError);
        }
        assert newTask != null;
        if (done) {
            newTask.done();
        }
        return newTask;
    }
}

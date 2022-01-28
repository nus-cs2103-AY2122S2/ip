package stevie.task;

import stevie.exception.TaskException;

import java.util.Date;

/**
 * Class to handle the creation of tasks. When a new task is added, update or add a new create method in
 * TaskCreator in order to support the construction of the new task.
 */
public class TaskCreator {
    /**
     * Creates a new task based on the type. Tasks can have name and/or a date.
     *
     * @param type type of the task
     * @param done true if task is already done, else false
     * @param taskName name of the task
     * @param date date of the task
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
            throw new TaskException("There is no such task!");
        }
        if (done) {
            newTask.done();
        }
        return newTask;
    }

    /**
     /**
     * Creates a new task based on the type. Tasks can only have a name.
     *
     * @param type type of the task
     * @param done true if task is already done, else false
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
            throw new TaskException("There is no such task!");
        }
        if (done) {
            newTask.done();
        }
        return newTask;
    }

    /**
     * Returns the type of the task, based on a character.
     *
     * @param eventChar character that represents a type of task
     * @return task type with starting character that matches the character
     * @throws TaskException if character supplied does not match any of the task type
     */
    public static TaskType charToType(char eventChar) throws TaskException {
        switch (eventChar) {
        case 'T':
            return TaskType.Todo;
        case 'E':
            return TaskType.Event;
        case 'D':
            return TaskType.Deadline;
        default:
            throw new TaskException("There is no such task!");
        }
    }
}

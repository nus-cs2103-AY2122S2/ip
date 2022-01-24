package stevie.task;

import stevie.exception.TaskException;

import java.util.Date;

public class TaskCreator {
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

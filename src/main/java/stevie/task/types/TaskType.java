package stevie.task.types;

import stevie.exception.TaskException;
import stevie.exception.messages.TaskExceptionMessages;

/**
 * Types of tasks that can be recorded to stevie.task.TaskList.
 */
public enum TaskType {
    Todo, Event, Deadline;

    /**
     * Returns a task type based on the first character of the type. Used for converting
     * saved data to Task object.
     * @param eventChar character that represents a type of task
     * @return task type with starting character that matches the character
     * @throws TaskException if character supplied does not match any of the task type
     */
    public static TaskType charToTaskType(char eventChar) throws TaskException {
        switch (eventChar) {
        case 'T':
            return TaskType.Todo;
        case 'E':
            return TaskType.Event;
        case 'D':
            return TaskType.Deadline;
        default:
            throw new TaskException(TaskExceptionMessages.InvalidTaskTypeError);
        }
    }
}

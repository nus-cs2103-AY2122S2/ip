package duke.task;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Creates @{@link Task} objects from its flattened form.
 * Separated from the <code>Task</code> class to remove cyclic dependencies.
 */
public class TaskFactory {
    /**
     * Creates subtypes of <code>Task</code> depending on the {@link TaskType} supplied.
     * Depends on the {@link Task#readSerializedData(DataInputStream)} method for actual population of object
     * attributes.
     *
     * @param type Type of task to be created.
     * @param dIn Input stream to read attribute data from.
     * @return Task created, or null if an invalid {@link TaskType} is supplied.
     * @throws IOException If an error occurs during any read operation.
     */
    public static Task inflate(TaskType type, DataInputStream dIn) throws IOException {
        Task task;
        switch (type) {
        case TODO:
            task = new Todo();
            break;
        case EVENT:
            task = new Event();
            break;
        case DEADLINE:
            task = new Deadline();
            break;
        default:
            return null;
        }
        return task.readSerializedData(dIn);
    }
}

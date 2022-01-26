package duke.task.serializer;

import duke.exception.DukeIOException;
import duke.task.Task;
import duke.task.TaskType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Serializes and Deserializes Task objects into byte arrays.
 * Depends on the {@link Task#serialize(DataOutputStream)} method for formatting of data for each
 * task.
 */
public class TaskSerializer {
    /**
     * Creates a {@link Task} object from data in the provided byte array.
     * @param data Byte array containing serialized data for a Task object.
     * @return <code>Task</code> object inflated from the byte array.
     * @throws DukeIOException If any format errors are encountered in the provided data.
     */
    static Task inflate(byte[] data) throws DukeIOException {
        ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        DataInputStream dataStream = new DataInputStream(memStream);

        try (dataStream) {
            int typeId = dataStream.readShort();
            TaskType taskType = TaskType.matchType(typeId);
            if (taskType == null) {
                throw new DukeIOException("Encountered unknown format in database");
            }
            return Task.inflate(taskType, dataStream);
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate Task");
        }
    }

    /**
     * Serializes the {@link Task} object into a custom format binary block.
     * @param task <code>Task</code> object to be serialized.
     * @return Byte array containing the deflated data of the supplied <code>Task</code> object.
     * @throws DukeIOException If any write error occurs during serialization.
     */
    static byte[] deflate(Task task) throws DukeIOException {
        ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(memStream);

        try (dataStream) {
            task.serialize(dataStream);
            return memStream.toByteArray();
        } catch (IOException ex) {
            throw new DukeIOException("Failed to serialize Task");
        }
    }
}

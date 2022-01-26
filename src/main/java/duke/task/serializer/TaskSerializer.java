package duke.task.serializer;

import duke.exception.DukeIoException;
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
     * @throws DukeIoException If any format errors are encountered in the provided data.
     */
    static Task inflate(byte[] data) throws DukeIoException {
        final ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        final DataInputStream dataStream = new DataInputStream(memStream);

        try (dataStream) {
            final int typeId = dataStream.readShort();
            TaskType taskType = TaskType.matchType(typeId);
            if (taskType == null) {
                throw new DukeIoException("Encountered unknown format in database");
            }
            return Task.inflate(taskType, dataStream);
        } catch (IOException ex) {
            throw new DukeIoException("Failed to inflate Task");
        }
    }

    /**
     * Serializes the {@link Task} object into a custom format binary block.
     * @param task <code>Task</code> object to be serialized.
     * @return Byte array containing the deflated data of the supplied <code>Task</code> object.
     * @throws DukeIoException If any write error occurs during serialization.
     */
    static byte[] deflate(Task task) throws DukeIoException {
        final ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        final DataOutputStream dataStream = new DataOutputStream(memStream);

        try (dataStream) {
            task.serialize(dataStream);
            dataStream.flush();
            memStream.flush();
            return memStream.toByteArray();
        } catch (IOException ex) {
            throw new DukeIoException("Failed to serialize Task");
        }
    }
}

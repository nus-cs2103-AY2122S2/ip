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
 * Serializes and Deserializes Task objects into Data Streams.
 */

public class TaskSerializer {
    static Task inflate(byte[] data) throws DukeIOException {
        final ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        final DataInputStream dataStream = new DataInputStream(memStream);

        try (dataStream) {
            final int typeId = dataStream.readShort();
            TaskType taskType = TaskType.matchType(typeId);
            if (taskType == null) {
                throw new DukeIOException("Encountered unknown format in database");
            }
            return Task.inflate(taskType, dataStream);
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate Task");
        }
    }

    static byte[] deflate(Task task) throws DukeIOException {
        final ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        final DataOutputStream dataStream = new DataOutputStream(memStream);

        try (dataStream) {
            task.serialize(dataStream);
            dataStream.flush();
            memStream.flush();
            return memStream.toByteArray();
        } catch (IOException ex) {
            throw new DukeIOException("Failed to serialize Task");
        }
    }
}

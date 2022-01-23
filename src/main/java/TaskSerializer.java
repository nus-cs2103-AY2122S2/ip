import java.io.*;
import java.nio.file.Paths;

/**
 * Serializes and Deserializes Task objects into Data Streams.
 */

public class TaskSerializer {
    public static Task inflate(byte[] data) throws DukeIOException {
        ByteArrayInputStream memStream = new ByteArrayInputStream(data);
        DataInputStream dataStream = new DataInputStream(memStream);

        try {
            int typeId = dataStream.readShort();
            TaskType taskType = TaskType.matchType(typeId);
            if (taskType == null) {
                return null;
            }
            return Task.inflate(taskType, dataStream);
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate Task");
        }
    }

    public static byte[] deflate(Task task) throws DukeIOException {
        ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(memStream);

        try {
            task.serialize(dataStream);
        } catch (IOException ex) {
            throw new DukeIOException("Failed to serialize Task");
        }

        return memStream.toByteArray();
    }
}

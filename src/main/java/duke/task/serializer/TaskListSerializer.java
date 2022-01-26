package duke.task.serializer;

import duke.exception.DukeIOException;
import duke.task.TaskList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Serializes and Deserializes Database Files into TaskStore object.
 * Database Files follow a custom binary format.
 * Does not provide File System operations.
 */

public class TaskListSerializer {

    public static TaskList inflate(InputStream dbStream) throws DukeIOException {
        final TaskList taskList = new TaskList();
        if (dbStream == null) {
            return taskList;
        }

        final DataInputStream dbDataStream = new DataInputStream(dbStream);
        try (dbDataStream) {
            final int recordCount = dbDataStream.readInt();

            for (int i = 0; i < recordCount; i++) {
                final int recordLength = dbDataStream.readInt();
                final byte[] recordData = dbStream.readNBytes(recordLength);
                try {
                    taskList.addTask(TaskSerializer.inflate(recordData));
                } catch (DukeIOException ex) {
                    System.out.println("Verbose: Failed to load Task record");
                }
            }
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate database: IO Error");
        }

        return taskList;
    }

    public static void deflate(TaskList taskList, OutputStream dbStream) throws DukeIOException {
        try (final DataOutputStream dbDataStream = new DataOutputStream(dbStream)) {
            dbDataStream.writeInt(taskList.getTaskCount());
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                final byte[] flattenedData = TaskSerializer.deflate(taskList.getTaskByIndex(i));
                dbDataStream.writeInt(flattenedData.length);
                dbDataStream.write(flattenedData);
            }
        } catch (IOException e) {
            throw new DukeIOException("Failed to deflate database: IO Error");
        }
    }
}

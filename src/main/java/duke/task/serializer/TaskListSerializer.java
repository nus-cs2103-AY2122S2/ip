package duke.task.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import duke.exception.DukeIoException;
import duke.task.TaskList;

/**
 * Serializes and Deserializes custom formatted database files into {@link TaskList} objects.
 * Does not provide File System read or write operations.
 * Uses a custom binary format for storage.
 */

public class TaskListSerializer {
    /**
     * Creates a {@link TaskList} object from the data in the supplied input stream.
     * @param dbStream Input stream to read data from for <code>TaskList</code> creation.
     * @return Inflated <code>TaskList</code> object.
     * @throws DukeIoException If any error occurs while reading the input stream.
     */
    public static TaskList inflate(InputStream dbStream) throws DukeIoException {
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
                } catch (DukeIoException ex) {
                    System.out.println("Verbose: Failed to load Task record");
                }
            }
        } catch (IOException ex) {
            throw new DukeIoException("Failed to inflate database: IO Error");
        }

        return taskList;
    }

    /**
     * Flattens and writes the {@link TaskList} object provided to the supplied output stream.
     * @param taskList <code>TaskList</code> object to flatten and write.
     * @param dbStream Output stream to write the data to.
     * @throws DukeIoException If any error occurs while writing to the output stream.
     */
    public static void deflate(TaskList taskList, OutputStream dbStream) throws DukeIoException {
        try (final DataOutputStream dbDataStream = new DataOutputStream(dbStream)) {
            dbDataStream.writeInt(taskList.getTaskCount());
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                final byte[] flattenedData = TaskSerializer.deflate(taskList.getTaskByIndex(i));
                dbDataStream.writeInt(flattenedData.length);
                dbDataStream.write(flattenedData);
            }
        } catch (IOException e) {
            throw new DukeIoException("Failed to deflate database: IO Error");
        }
    }
}

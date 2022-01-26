package duke.task.serializer;

import duke.exception.DukeIOException;
import duke.task.TaskList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
     * @throws DukeIOException If any error occurs while reading the input stream.
     */
    public static TaskList inflate(InputStream dbStream) throws DukeIOException {
        TaskList store = new TaskList();
        if (dbStream == null) {
            return store;
        }

        DataInputStream dbDataStream = new DataInputStream(dbStream);
        try (dbDataStream) {
            int recordCount = dbDataStream.readInt();
            for (int i = 0; i < recordCount; i++) {
                int recordLength = dbDataStream.readInt();
                byte[] recordData = dbStream.readNBytes(recordLength);
                try {
                    store.addTask(TaskSerializer.inflate(recordData));
                } catch (DukeIOException ex) {
                    System.out.println("Verbose: Failed to load Task record");
                }
            }
        } catch (IOException ex) {
            throw new DukeIOException("Failed to inflate database: IO Error");
        }

        return store;
    }

    /**
     * Flattens and writes the {@link TaskList} object provided to the supplied output stream.
     * @param store <code>TaskList</code> object to flatten and write.
     * @param dbStream Output stream to write the data to.
     * @throws DukeIOException If any error occurs while writing to the output stream.
     */
    public static void deflate(TaskList store, OutputStream dbStream) throws DukeIOException {
        try (DataOutputStream dbDataStream = new DataOutputStream(dbStream)) {
            dbDataStream.writeInt(store.getTaskCount());
            for (int i = 0; i < store.getTaskCount(); i++) {
                byte[] flattenedData = TaskSerializer.deflate(store.getTaskByIndex(i));
                dbDataStream.writeInt(flattenedData.length);
                dbDataStream.write(flattenedData);
            }
        } catch (IOException e) {
            throw new DukeIOException("Failed to deflate database: IO Error");
        }
    }
}

package duke.task.serializer;

import duke.task.TaskList;
import duke.exception.DukeIOException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Serializes and Deserializes Database Files into TaskStore object.
 * Database Files follow a custom binary format.
 * Does not provide File System operations.
 */

public class TaskListSerializer {

    public static TaskList inflate(FileInputStream dbStream) throws DukeIOException {
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

    public static void deflate(TaskList store, FileOutputStream dbStream) throws DukeIOException {
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

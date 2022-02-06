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
     *
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
            inflateRecords(dbDataStream, recordCount, taskList);
        } catch (IOException ex) {
            throw new DukeIoException("Failed to inflate database: IO Error");
        }

        return taskList;
    }

    /**
     * Inflates task records from a database {@link DataInputStream} object.
     *
     * @param dbDataStream The data stream to read from.
     * @param recordCount The number of records to read.
     * @param taskList The list that inflated records should be added to.
     * @throws IOException If any error occurs while reading the input stream.
     */
    private static void inflateRecords(DataInputStream dbDataStream, int recordCount, TaskList taskList)
            throws IOException {
        for (int i = 0; i < recordCount; i++) {
            final int recordLength = dbDataStream.readInt();
            final byte[] recordData = dbDataStream.readNBytes(recordLength);

            try {
                taskList.addTask(TaskSerializer.inflate(recordData));
            } catch (DukeIoException ex) {
                System.out.println("Verbose: Failed to load Task record");
            }
        }
    }

    /**
     * Flattens and writes the {@link TaskList} object provided to the supplied output stream.
     *
     * @param taskList <code>TaskList</code> object to flatten and write.
     * @param dbStream Output stream to write the data to.
     * @throws DukeIoException If any error occurs while writing to the output stream.
     */
    public static void deflate(TaskList taskList, OutputStream dbStream) throws DukeIoException {
        try (final DataOutputStream dbDataStream = new DataOutputStream(dbStream)) {
            dbDataStream.writeInt(taskList.getTaskCount());
            deflateRecords(dbDataStream, taskList);
        } catch (IOException e) {
            throw new DukeIoException("Failed to deflate database: IO Error");
        }
    }

    /**
     * Deflates tasks from a task list into a {@link DataOutputStream} object.
     *
     * @param dbDataStream The data stream to write to.
     * @param taskList The task list containing the tasks to be deflated.
     * @throws DukeIoException If an error occurs during task deflation.
     * @throws IOException If an error occurs while writing to the output stream.
     */
    private static void deflateRecords(DataOutputStream dbDataStream, TaskList taskList)
            throws DukeIoException, IOException {
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            final byte[] flattenedData = TaskSerializer.deflate(taskList.getTaskByIndex(i));
            dbDataStream.writeInt(flattenedData.length);
            dbDataStream.write(flattenedData);
        }
    }
}

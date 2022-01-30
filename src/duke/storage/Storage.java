package storage;

import task.Task;
import task.TaskNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Encapsulates the operations related to hard-disk storage.
 */
public class Storage {

    private String filePath;
    private File taskFile;

    /**
     * Constructs a storage object using a file path.
     * If the file with the specified path does not exist, creates a new empty file on that location.
     *
     * @param filePath The file path to be read from.
     */
    public Storage(String filePath) {

        this.taskFile = new File(filePath);

        if (!taskFile.exists()) {
            try {
                taskFile = createTaskFile(taskFile);
            } catch (IOException e) {
                System.err.println("Oops, I am not able to create the file to store your task.");
            }
        }
    }

    /**
     * Initializes an empty file with metadata.
     *
     * @param taskFile The file to be initialized.
     */
    private File createTaskFile(File taskFile) throws IOException {
        // Create the directory, if not exists yet.
        if (!taskFile.exists()) {
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        }
        FileOutputStream writeData = new FileOutputStream(taskFile);
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeInt(0);
        writeStream.writeChar('#');
        writeStream.flush();
        writeStream.close();
        return taskFile;
    }

    /**
     * Reads the tasks from the given file path into an <code>ArrayList</code>.
     *
     * @return An <code>ArrayList</code> that stores the tasks.
     * @throws FileNotFoundException If the file path is invalid.
     */
    public ArrayList<Task> readTasks()
            throws FileNotFoundException {

        ArrayList<Task> result = new ArrayList<>();

        try {
            FileInputStream readData = new FileInputStream(this.taskFile);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            // Retrieve metadata.
            int size = readStream.readInt();
            readStream.readChar();

            for (int i = 0; i < size; i++) {

                Task currentTask = Task.retrieveTask((HashMap<String, Object>) readStream.readObject());
                result.add(currentTask);
            }
            readStream.close();
        } catch (IOException | ClassNotFoundException | TaskNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Writes an array of tasks into the targeted file location.
     *
     * @param tasks The tasks to be written.
     */
    public void writeTasks(ArrayList<Task> tasks) {

        try {
            FileOutputStream writeData = new FileOutputStream(this.taskFile);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            // Metadata: stores the number of tasks in the list.
            writeStream.writeInt(tasks.size());
            writeStream.writeChar('#');

            for (Task t : tasks) {
                // Write the info table as its serialization.
                writeStream.writeObject(t.getInfoTable());
            }
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

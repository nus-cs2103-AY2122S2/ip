package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Encapsulates the methods to store and update tasks to local memory.
 */
public class Storage {
    /**
     * Constant file path to file containing stored data.
     */
    private static final String FILEPATH = "../data/tasks.txt";

    /**
     * Loads saved tasks from local file to program
     *
     * @return the task list of saved tasks
     * @throws IOException when file content is invalid
     */
    public TaskList<Task> loadSavedTasks() throws IOException {
        TaskList<Task> tasks = new TaskList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(FILEPATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read objects into tasks list
            loadTask(tasks, objectInputStream);

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating tasks.txt now...");
            new File(FILEPATH).createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred reading tasks.txt. Try again later.");
        }

        return tasks;
    }

    /**
     * Loads a task from local disk to the program task list.
     *
     * @param tasks             list of tasks to add the task
     * @param objectInputStream the stream of objects read from local file
     */
    public void loadTask(TaskList<Task> tasks, ObjectInputStream objectInputStream) {
        boolean nextTask = true;

        while (nextTask) {
            Task t = null;
            try {
                t = (Task) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                nextTask = false;
            }
            if (t != null) {
                tasks.add(t);
            }
        }
    }

    /**
     * Updates tasks from program to local disk
     *
     * @param tasks list of tasks to be saved to local disk
     */
    public void updateStorage(TaskList<Task> tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILEPATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write updated tasks to file
            for (int i = 1; i <= tasks.size(); i++) {
                objectOutputStream.writeObject(tasks.get(i));
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("UPDATE TASKS: File not found");
        } catch (IOException e) {
            System.out.println("UPDATE TASKS: Error initializing stream");
        }
    }
}

package duke.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskCreator;
/**
 * The Storage class loads data from previous sessions
 * of duke stores data from current session.
 *
 * @author  Melvin Chan Zijun
 */
public class Storage {
    /**
     * File path for the directory where the data will
     * be stored.
     */
    private final String filePath;

    /**
     * Sole constructor.
     *
     * @param filePath the file path of the directory where
     *                 the data of duke will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the directory which the data from
     * the current session of duke will be stored in.
     * If directory already exists, user will be notified.
     * The text file tasks.txt will be created in the directory.
     *
     * @return ArrayList tasks loaded from data
     * @throws DukeException if unsuccessful creation of directory
     *                       or file
     */
    public ArrayList<Task> load() throws DukeException {
        File dukeFile = new File(this.filePath);

        if (!Files.exists(Path.of(this.filePath))) {
            if (dukeFile.mkdir()) {
                System.out.println("File \"data\" has been created.");
            } else {
                System.out.println("Failed to create file \"data\".");
            }
        }

        File tasksPath = new File(this.filePath + "/tasks.txt");

        try {
            if (tasksPath.createNewFile()) {
                System.out.println("Duke set up successfully");
            } else {
                return read(tasksPath);
            }
        } catch (IOException e) {
            throw new DukeException("Failed to create tasks file!");
        }
        return new ArrayList<>();
    }

    /**
     * This method reads the tasks.txt and adds the exisiting tasks
     * on the file into a arraylist and returns it.
     *
     * @param filePath path of the directory where
     *                 the data of duke will be stored
     * @return ArrayList tasks loaded from data
     * @throws DukeException if FileNotFoundException or
     *                       IOException is caught
     */
    public ArrayList<Task> read(File filePath) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int numOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task> tasks = new ArrayList<>();

            while (numOfTasks != 0) {
                numOfTasks--;
                String taskAsText = br.readLine();
                Task currentTask = create(taskAsText);
                tasks.add(currentTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (IOException e) {
            throw new DukeException("Error with tasks.txt! Clear tasks.txt and run Duke again!");
        }
    }

    /**
     * This method takes in the data form of a task
     * and returns the Task object.
     *
     * @param taskAsData data form of a task
     * @return Task new task object
     */
    public Task create(String taskAsData) throws DukeException {
        String[] taskAsArray = taskAsData.split("/");

        char prefix = taskAsArray[0].charAt(0);
        boolean isCompleted = taskAsArray[1].equals("T");
        String name = taskAsArray[2];
        String date = taskAsArray[3];
        String time = taskAsArray[4];

        TaskCreator taskCreator = new TaskCreator(prefix,
                isCompleted,
                name,
                date,
                time);
        try {
            Task task = taskCreator.createTask();
            return task;
        } catch (DukeException e) {
            throw e;
        }


    }

    /**
     * Overwrites saved data with new data of task list
     *
     * @param tasks duke's task list
     * @throws DukeException if IOException is caught
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", false);
            PrintWriter pw = new PrintWriter(fw);

            pw.write(tasks.getNumOfTasks() + "\n");
            pw.write(tasks.taskAsData());

            pw.flush();
            pw.close();

        } catch (IOException e) {
            throw new DukeException("Failed to save data!");
        }
    }

}

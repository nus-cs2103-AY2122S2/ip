package Duke.System;

import Duke.DukeException.DukeException;
import Duke.Tasks.Task;
import Duke.Tasks.TaskCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;

/**
 * The Storage class loads data from previous sessions
 * of duke stores data from current session.
 *
 * @author  Melvin Chan Zijun
 */
public class Storage {
    /**
     * The file path for the directory where the data will
     * be stored.
     */
    private final String filePath;

    /**
     * Sole constructor.
     *
     * @param filePath - the file path of the directory where
     *                   the data of duke will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method creates the directory which the data from
     * the current session of duke will be stored in.
     * If directory already exists, user will be notified.
     * The text file tasks.txt will be created in the directory.
     *
     * @return ArrayList<Task> - returns an arraylist of tasks loaded
     *                           from tasks.txt
     * @throws DukeException - if unsuccessful creation of directory
     *                         or file
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
        } catch (IOException e){
            throw new DukeException("Failed to create tasks file!");
        }
        return new ArrayList<>();
    }

    /**
     * This method reads the tasks.txt and adds the exisiting tasks
     * on the file into a arraylist and returns it.
     *
     * @param filePath - the file path of the directory where
     *                   the data of duke will be stored
     * @return ArrayList<Task> - returns an arraylist of tasks loaded
     *                           from tasks.txt
     * @throws DukeException - if FileNotFoundException or
     *                         IOException is caught
     */
    public ArrayList<Task> read(File filePath) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int numOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task> tasks = new ArrayList<>();

            while (numOfTasks != 0) {
                // convert taskAsText into its string array form,
                // with 4 elements, being the prefix, completedState, name and date
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
     * @param taskAsData - data form of a task
     * @return Task - returns the Task object
     */
    public Task create(String taskAsData) {
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
        return taskCreator.createTask();
    }

    /**
     * This method overwrites the tasks.txt with the given
     * list of tasks in String form.
     *
     * @param tasks - the list of tasks in String form
     * @throws DukeException - if IOException is caught
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

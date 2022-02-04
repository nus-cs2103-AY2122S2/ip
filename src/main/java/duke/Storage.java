package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


/**
 * This is an FileAction class that allows the saving of
 * contents into a pre-specified file
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-25
 */

public class Storage {
    protected String filepath;
    protected FileWriter fileWriter;
    protected FileReader fileReader;
    protected BufferedWriter buffWriter;
    protected BufferedReader buffReader;
    protected TaskList tasks;

    public Storage(String path, TaskList tasks) {
        filepath = path;
        this.tasks = tasks;
    }

    /**
     * Saves content of Tasks into given file
     * @param content is the information of a Task object
     */
    public void saveTask(String content) throws IOException {
        try {
            buffWriter.write(content);
        } catch (IOException e) {
            throw new IOException("\nUnexpected error occurred where Tasks cannot be saved in file!\n");
        }
    }

    /**
     * Starts the process of file writing
     */
    public void startWriter() throws IOException {
        fileWriter = new FileWriter(filepath);
        buffWriter = new BufferedWriter(fileWriter);
    }

    /**
     * Closes file after writing into the given file
     */
    public void closeWriteFile() throws IOException {
        try {
            buffWriter.close();
        } catch (IOException e) {
            throw new IOException("\nUnexpected error occurred where file writer cannot be closed!\n");
        }
    }

    /**
     * Closes file after reading the given file
     */
    public void closeReadFile() throws IOException {
        try {
            buffReader.close();
        } catch (IOException e) {
            throw new IOException("\nUnexpected error occurred where file reader cannot be closed!\n");
        }
    }

    /**
     * Saves the current instances of Task items in Tasklist into
     * provided text file
     * @param tasks is the current instance of the TaskList in this Duke application
     */
    public void saveAllTasks(TaskList tasks) throws IOException {
        startWriter();
        for (Task task : tasks.getTasks()) {
            saveTask(task.toString());
        }
    }

    /**
     * Reads tasks saved in a hard drive file provided by the user and
     * places Task objects into the current instance of Duke
     */
    public void tasksThatHaveBeenRead() throws FileNotFoundException {
        ArrayList<Task> readTasks = new ArrayList<>();
        String line = "";
        fileReader = new FileReader(filepath);
        buffReader = new BufferedReader(fileReader);

        try {
            while ((line = buffReader.readLine()) != null) {
                assertTrue(line.contains("-")); //Assumes each line of Task information are seperated by "-"
                String[] arrOfString = line.split(" - ");
                readTasks.add(createTaskFromText(arrOfString));
            }
            tasks.setTasks(readTasks);
        } catch (IOException e) {
            System.out.println("Error in reading tasks from file!");
        }
    }

    /**
     * Creates Task based on the text provided in the given text file
     * @param line is the lines of text in the text file read
     */
    public Task createTaskFromText(String[] line) {
        String type = line[0];
        String done = line[1];
        String title = line[2];
        String date = "";
        Task newT = null;
        switch (type) {
            case "T":
                newT = new ToDo(title, Integer.valueOf(done));
                break;
            case "D":
                 date = line[3];
                newT = new Deadline(title, Integer.valueOf(done));
                ((Deadline) newT).setLocalDate(date);
                break;
            case "E":
                date = line[3];
                newT = new Event(title, Integer.valueOf(done));
                ((Event) newT).setLocalDate(date);
                break;
            default:
                System.out.println("Invalid Task type found in file!\n");
                break;
        }
        return newT;
    }
}

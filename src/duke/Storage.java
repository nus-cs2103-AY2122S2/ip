package duke;

import java.io.*;
import java.util.ArrayList;


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
     */
    public void saveTask(String cont) throws IOException {
        try {
            buffWriter.write(cont);
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
     * Asks for user input to change file read/to be written to what user inputted
     */
    public void requestChangeFile(String fileName) {
        filepath = fileName;
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
     * Saves the current instances of duke.Task items in Tasks list into
     * provided text file
     */
    public void saveAllTasks(TaskList tasks) throws IOException {
        startWriter();
        for (Task task : tasks.getTasks()) {
            saveTask(task.toString());
        }
    }

    /**
     * Reads tasks saved in the hard drive file provided by the user and
     * places duke.Task objects into the current DukeList
     */
    public void tasksThatHaveBeenRead() throws FileNotFoundException {
        ArrayList<Task> readTasks = new ArrayList<>();
        String line = "";
        fileReader = new FileReader(filepath);
        buffReader = new BufferedReader(fileReader);

        try {
            while ((line = buffReader.readLine()) != null) {
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

package karen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import karen.task.Deadline;
import karen.task.Event;
import karen.task.Task;
import karen.task.ToDo;

/**
 * Loads, manages and subsequently saves the user's task list
 */
public class Storage {
    public static final String DATA_DIR = "./data/";
    public static final String DATA_PATH = "./data/karen.txt";

    public static final String NO_FILE_MESSAGE = "No previous session/data found";
    public static final String ERR_FILE_MESSAGE = "Something went wrong with reading the previous session..";

    private ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Constructor for Storage object
     * @param ui For parsing out messages related to the managing of Storage-related objects
     */
    public Storage(Ui ui) {
        this.ui = ui;
        this.taskList = this.loadTasks();
    }

    /**
     * Gets number of tasks in taskList
     * @return Number of tasks in taskList
     */
    public int getTaskCount() {
        return this.taskList.size();
    }

    /**
     * Reads data from DATA_PATH directory and parses it into an ArrayList of Task objects.
     *
     * @return ArrayList of Task objects read from DATA_PATH directory.
     */
    private ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileInputStream readStream = new FileInputStream(DATA_PATH);
            DataInputStream in = new DataInputStream(readStream);
            br = new BufferedReader(new InputStreamReader(in));

            String readLine;
            while ((readLine = br.readLine()) != null) {
                String[] data = readLine.split("\\|");
                Task item = this.createTask(data[0], Arrays.copyOfRange(data, 2, data.length));
                if (Boolean.parseBoolean(data[1])) {
                    item.markDone();
                }
                taskList.add(item);
            }
            in.close();
        } catch (FileNotFoundException err) {
            this.ui.displayWarning(NO_FILE_MESSAGE);
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return taskList;
        } catch (IOException err) {
            this.ui.displayWarning(ERR_FILE_MESSAGE);
            return taskList;
        } finally {
            try {
                br.close();
            } catch (Exception err) {
                // do nothing
            }
        }
        return taskList;
    }

    /**
     * Writes data from taskList attribute into file to DATA_PATH directory.
     */
    public void saveTasks() {
        // formatting data for writing
        String data = "";
        for (Task item: this.taskList) {
            data = data.concat(String.format("%s\n", item.toSaveData()));
        }

        // writing data to local dir
        Writer writer = null;
        try {
            FileOutputStream writeStream = new FileOutputStream(DATA_PATH);
            OutputStreamWriter out = new OutputStreamWriter(writeStream);
            writer = new BufferedWriter(out);
            writer.write(data);
        } catch (FileNotFoundException err) {
            // deprecated
            this.ui.displayWarning(
                    String.format("Improper access for file writing.\n\tCheck if %s exists.", DATA_DIR));
        } catch (IOException err) {
            // deprecated
            this.ui.displayWarning("Something went wrong with writing to file");
        } finally {
            try {
                writer.close();
            } catch (Exception err) {
                // do nothing
            }
        }
    }

    /**
     * Creates Task object based on letters from data format saved in DATA_PATH.
     *
     * @param taskType Single letter string to indicate subclass of Task object.
     * @param taskArgs Relevant arguments to instantiate subclasses of Task Objects
     * @return Task Object
     */
    public Task createTask(String taskType, String[] taskArgs) {
        Task initTask;

        switch (taskType) {
        case "T":
            initTask = new ToDo(taskArgs[0]);
            break;
        case "D":
            initTask = new Deadline(taskArgs[0], taskArgs[1]);
            break;
        case "E":
            initTask = new Event(taskArgs[0], taskArgs[1]);
            break;
        default:
            initTask = null;
            break;
        }
        return initTask;
    }

    /**
     * Getter method for Task objects within taskList based on (0-based) index
     *
     * @param index of Task Object inside of taskList
     * @return Task object at index parameter
     * @throws IndexOutOfBoundsException if index is not within range of taskList
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return this.taskList.get(index);
    }

    /**
     * Getter method for taskList
     *
     * @return list of Task objects
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds Task object to end of taskList
     *
     * @param item Task object to be added to taskList
     */
    public void addTask(Task item) {
        this.taskList.add(item);
    }

    /**
     * Deletes Task object based on (0-based) index
     *
     * @param index of Task Object inside of taskList
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.taskList.remove(index);
    }

}

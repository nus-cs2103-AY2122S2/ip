package juke.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import juke.Juke;
import juke.exception.JukeException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Task;
import juke.task.TaskStatus;
import juke.task.Todo;

/**
 * Handles file storage including input and output.
 */
public class Storage {
    /**
     * Path name to the data file.
     */
    private static final String PATH_NAME = "data/juke.txt";

    /**
     * Reference to the Juke instance.
     */
    private final Juke juke;

    /**
     * File instance of the data file.
     */
    private File file = new File(PATH_NAME);

    /**
     * Constructor that initializes the storage class.
     *
     * @param instance Instance of juke.
     */
    public Storage(Juke instance) {
        this.juke = instance;
        this.initializeFile();
    }

    /**
     * Initializes the file, making directories and the file if it is not present.
     */
    private void initializeFile() {
        try {
            if (this.file.getParentFile().mkdirs()) {
                this.juke.getUi().formattedPrint("Directories not found. Creating new directories.");
            }
            if (this.file.createNewFile()) {
                this.juke.getUi().formattedPrint("File not found. Creating new file.");
            }
        } catch (IOException | SecurityException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the data file to the task list.
     */
    public void loadTasks() {
        ArrayList<String[]> parseArr = this.parse();
        for (String[] args : parseArr) {
            try {
                Task task = this.decode(args);
                if (task != null) {
                    this.juke
                        .getTaskList()
                        .add(task);
                }
            } catch (JukeException e) {
                this.juke.getUi().formattedPrint(e.getMessage());
                System.exit(-1);
            }
        }
    }

    /**
     * Saves the tasks from the task list to the data file.
     */
    public void saveTasks() {
        ArrayList<String[]> writeArr = new ArrayList<>();
        for (Task task : this.juke.getTaskList()) {
            String[] args = this.encode(task);
            if (args != null) {
                writeArr.add(args);
            }
        }
        this.write(writeArr);
    }

    /**
     * Parses the file format into string components.
     *
     * @return An array list of string components.
     */
    public ArrayList<String[]> parse() {
        ArrayList<String[]> array = new ArrayList<>();
        try {
            Scanner in = new Scanner(this.file);
            while (in.hasNextLine()) {
                array.add(in.nextLine().strip().split(";"));
            }
            in.close();
        } catch (FileNotFoundException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
        }
        return array;
    }

    /**
     * Writes data to the file.
     * Returns true if successful, false otherwise.
     *
     * @param array Array of string components to write.
     * @return Boolean result.
     */
    public boolean write(ArrayList<String[]> array) {
        try {
            FileWriter out = new FileWriter(file);
            for (String[] args : array) {
                String str = String.join(";", args);
                out.write(str + "\n");
            }
            out.close();
        } catch (IOException e) {
            this.juke.getUi().formattedPrint(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Decodes a line of string components into a task.
     *
     * @param args String components.
     * @return Task.
     * @throws JukeException Throws if string component is incorrect.
     */
    public Task decode(String[] args) throws JukeException {
        Task task = null;
        if (args.length > 2) {
            boolean mark = Boolean.parseBoolean(args[1]);
            switch (args[0]) {
            case "E":
                if (args.length < 4) {
                    throw new JukeException("File has incorrect arguments");
                } else {
                    task = new Event(args[2], args[3]);
                }
                break;
            case "T":
                task = new Todo(args[2]);
                break;
            case "D":
                if (args.length < 4) {
                    throw new JukeException("File has incorrect arguments");
                } else {
                    task = new Deadline(args[2], args[3]);
                }
                break;
            default:
                throw new JukeException("File has incorrect arguments");
            }
            if (mark) {
                task.markAsDone();
            }
        } else {
            throw new JukeException("File has incorrect arguments");
        }
        return task;
    }

    /**
     * Encodes a task into string components.
     *
     * @param task Task to encode.
     * @return String components.
     */
    public String[] encode(Task task) {
        String[] args = null;
        if (task instanceof Todo) {
            args = new String[3];
            args[0] = "T";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
        } else if (task instanceof Event) {
            args = new String[4];
            args[0] = "E";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
            args[3] = ((Event) task).getTime();
        } else if (task instanceof Deadline) {
            args = new String[4];
            args[0] = "D";
            if (task.getStatus() == TaskStatus.DONE) {
                args[1] = "true";
            } else {
                args[1] = "false";
            }
            args[2] = task.getDescription();
            args[3] = ((Deadline) task).getTime();
        }
        return args;
    }
}

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
import juke.task.TaskType;
import juke.task.TimeTask;
import juke.task.Todo;

/**
 * Handles file storage including input and output.
 */
public class Storage {
    /**
     * Path name to the data file.
     */
    private static final String PATH_NAME = "data/juke.txt";

    private static final String MKDIRS_MESSAGE = "Directories not found. Creating new directories.";
    private static final String MISSING_MESSAGE = "File not found.";
    private static final String CREATE_MESSAGE = "Creating new file.";
    private static final String FORMAT_ERROR_MESSAGE = "File has incorrect arguments.";

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
        initializeFile();
    }

    /**
     * Initializes the file, making directories and the file if it is not present.
     */
    private void initializeFile() {
        try {
            if (file.getParentFile().mkdirs()) {
                juke.getUi().formattedPrint(MKDIRS_MESSAGE);
            }
            if (file.createNewFile()) {
                juke.getUi().formattedPrint(MISSING_MESSAGE, CREATE_MESSAGE);
            }
        } catch (IOException | SecurityException e) {
            juke.getUi().formattedPrint(e.getMessage());
        }
    }

    /**
     * Creates a new file if file format is invalid.
     */
    private void createNewFileForInvalidData() {
        try {
            file.createNewFile();
            juke.getUi().formattedPrint(FORMAT_ERROR_MESSAGE, CREATE_MESSAGE);
        } catch (IOException | SecurityException e) {
            juke.getUi().formattedPrint(e.getMessage());
        }
    }

    /**
     * Loads the tasks from the data file to the task list.
     */
    public void loadTasks() {
        ArrayList<String[]> parseArr = null;
        try {
            parseArr = Parser.parseFile(file);
        } catch (FileNotFoundException e) {
            // Should not reach here
            assert false;
            System.exit(-1);
        }
        for (String[] args : parseArr) {
            Task task = null;
            try {
                task = decode(args);
            } catch (JukeException e) {
                createNewFileForInvalidData();
            }
            if (task != null) {
                juke.getTaskList().add(task);
            }
        }
    }

    /**
     * Saves the tasks from the task list to the data file.
     */
    public void saveTasks() {
        ArrayList<String[]> writeArr = new ArrayList<>();
        for (Task task : juke.getTaskList()) {
            String[] args = encode(task);
            if (args != null) {
                writeArr.add(args);
            }
        }
        Parser.writeFile(writeArr, file);
    }

    /**
     * Decodes a line of string components into a task.
     *
     * @param args String components.
     * @return Task.
     * @throws JukeException Throws if string component is incorrect.
     */
    public Task decode(String[] args) throws JukeException {
        boolean hasArgs = args.length > 2;
        if (!hasArgs) {
            throwDecodeError();
        }
        TaskType type = TaskType.getTaskTypeFromShortString(args[0]);
        if (type == null) {
            throwDecodeError();
        }
        Task task = constructTaskFromArgs(type, args);
        assert task != null;
        markDecodedTask(task, args);
        return task;
    }

    /**
     * Marks the decoded task according to the data.
     *
     * @param task Task to mark.
     * @param args Arguments.
     */
    private void markDecodedTask(Task task, String[] args) {
        boolean mark = Boolean.parseBoolean(args[1]);
        if (mark) {
            task.markAsDone();
        }
    }

    /**
     * Construct a task from a task type and arguments.
     * Part of the decode process.
     *
     * @param type Task type.
     * @param args Arguments.
     * @return Task.
     * @throws JukeException Throws if there is a decode error.
     */
    private Task constructTaskFromArgs(TaskType type, String[] args) throws JukeException {
        Task task = null;
        switch (type) {
        case EVENT:
            if (args.length < 4) {
                throwDecodeError();
            }
            task = new Event(args[2], args[3]);
            break;
        case TODO:
            task = new Todo(args[2]);
            break;
        case DEADLINE:
            if (args.length < 4) {
                throwDecodeError();
            }
            task = new Deadline(args[2], args[3]);
            break;
        default:
            //Should not reach here
            throwDecodeError();
        }
        assert task != null;
        return task;
    }

    /**
     * Encodes a task into string components.
     *
     * @param task Task to encode.
     * @return String components.
     */
    public String[] encode(Task task) {
        assert task != null;
        String[] args = initializeArgsLength(task);
        assert args != null;
        encodeTaskShortString(task, args);
        encodeTaskStatus(task, args);
        encodeTaskDescription(task, args);
        encodeTimeTaskDateTime(task, args);
        return args;
    }

    /**
     * Returns args to be of the right length.
     * Part of the encode process.
     *
     * @param task Task.
     * @return String arguments.
     */
    private String[] initializeArgsLength(Task task) {
        switch (task.getTaskType()) {
        case DEADLINE:
        case EVENT:
            return new String[4];
        case TODO:
        default:
            return new String[3];
        }
    }

    /**
     * Encodes the task short string into the arguments.
     * Part of the encode process.
     *
     * @param task Task.
     * @param args Arguments.
     */
    private void encodeTaskShortString(Task task, String[] args) {
        args[0] = task.getTaskType().getTaskShortString();
    }

    /**
     * Encodes the task status into the arguments.
     * Part of the encode process.
     *
     * @param task Task.
     * @param args Arguments.
     */
    private void encodeTaskStatus(Task task, String[] args) {
        if (task.getStatus() == TaskStatus.DONE) {
            args[1] = "true";
        } else {
            args[1] = "false";
        }
    }

    /**
     * Encodes the task description into the arguments.
     * Part of the encode process.
     *
     * @param task Task.
     * @param args Arguments.
     */
    private void encodeTaskDescription(Task task, String[] args) {
        args[2] = task.getDescription();
    }

    /**
     * Encodes the task date and time into the arguments.
     * Only executes if task is a time task.
     * Part of the encode process.
     *
     * @param task Task.
     * @param args Arguments.
     */
    private void encodeTimeTaskDateTime(Task task, String[] args) {
        if (task instanceof TimeTask && args.length > 3) {
            args[3] = ((TimeTask) task).getTime();
        }
    }

    /**
     * Method to throw decode error exception.
     *
     * @throws JukeException Exception.
     */
    private void throwDecodeError() throws JukeException {
        throw new JukeException(FORMAT_ERROR_MESSAGE);
    }
}

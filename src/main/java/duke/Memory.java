package duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * The Memory class handles the storage and access to available Tasks.
 * This is the equivalent to the 'TaskList' class on the example.
 *
 * @author Rdac0
 */
public class Memory {

    private ArrayList<Task> taskMem;
    private int size;
    private Echo echo;
    private File file;
    private Parser parser;

    /**
     * Creates a Memory object.
     */
    public Memory() {
        this.taskMem = new ArrayList<>();
        this.size = 0;
        this.echo = new Echo();
        this.file = new File("data");
    }

    /**
     * Returns stored size of taskMem, not actual size.
     * Although, they should be the same.
     *
     * @return Stored size of taskMem.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the String representation of a Task in Memory.
     *
     * @param address The address of the Task to request.
     * @return The String representation of the task requested.
     */
    public String getString(int address) {
        // Simple error handling, should suffice but will update
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            return taskMem.get(address).toString();
        }
    }

    /**
     * Returns a Task in Memory.
     *
     * @param address The address of the Task to request.
     * @return The Task requested.
     */
    public Task getTask(int address) {
        return taskMem.get(address);
    }

    /**
     * Returns a string representation of all Tasks in Memory.
     *
     * @return String representation.
     */
    public String listAll() {
        if (size == 0) {
            return "You've got nothing to do.";
        } else {
            String str = "Here's what you got:";
            for (int i = 1; i <= size; i++) {
                str = str + "\n " + i + ". " + this.getString(i - 1);
            }
            return str;
        }
    }

    /**
     * Finds all Task with specified keyword in their name.
     *
     * @param text Specifying keyword.
     * @return String representation of the list of found Tasks.
     */
    public String findAll(String text) {
        if (size == 0) {
            return "Trust me, I wont find anything.";
        } else {
            String str = "";

            for (int i = 1; i <= size; i++) {
                Task tempTask = this.getTask(i - 1);
                if (tempTask.isFoundInName(text)) {
                    str = str + "\n " + i + ". " + this.getString(i - 1);
                }
            }

            if (str.equals("")) {
                return "Unfortunately, I didn't find anything.";
            } else {
                str = "Here's what I found, and their ids:" + str;
                return str;
            }
        }
    }

    /**
     * Adds a Task to memory.
     *
     * @param name The name of the Task to be made.
     * @return String audit message.
     */
    public String addTask(String name) {
        taskMem.add(new Task(name));
        size++;
        parser.updateAll();
        return "added task: " + getString(size - 1);
    }

    /**
     * Adds a Deadline to Memory.
     *
     * @param name The name of the Deadline to be made.
     * @param time The due time of the Deadline.
     * @return String audit message.
     */
    public String addDeadline(String name, String time) {
        try {
            taskMem.add(new Deadline(name, time));
            size++;
            parser.updateAll();
            return "added deadline: " + getString(size - 1);
        } catch (DateTimeParseException e) {
            return "Please format your date in yyyy-mm-dd";
        }
    }

    /**
     * Adds an Event to Memory.
     *
     * @param name The name of the Event to be made.
     * @param time The time of the Event.
     * @return String audit message.
     */
    public String addEvent(String name, String time) {
        try {
            taskMem.add(new Event(name, time));
            size++;
            parser.updateAll();
            return "added event: " + getString(size - 1);
        } catch (DateTimeParseException e) {
            return "Please format your date in yyyy-mm-dd";
        }
    }

    /**
     * Sets a Task in Memory as done.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     * @return String audit message.
     */
    public String setDone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            getTask(address).setDone();
            parser.updateAll();
            return "Cool! You've done this task:\n  "
                    + getString(address);
        }
    }

    /**
     * Sets a Task in Memory as undone.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     * @return String audit message.
     */
    public String setUndone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            getTask(address).setUndone();
            parser.updateAll();
            return "This task is now undone:\n  "
                    + getString(address);
        }
    }

    /**
     * Removes a Task from Memory.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     * @return String audit message.
     */
    public String deleteTask(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            String temp = getString(address);
            taskMem.remove(address);
            size--;
            parser.updateAll();
            return "removed: " + temp;
        }
    }

    /**
     * Sets up the data file and Parser.
     */
    public void setup() {

        if (!file.exists()) {
            echo.echoString("Data folder does not exist. Creating folder...");
            file.mkdir();
        }

        file = new File("data/tasks.txt");
        if (!file.exists()) {
            echo.echoString("Task data file does not exist. Creating file...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                echo.echoString(e.getMessage());
            }
        }

        parser = new Parser(file, this);
        parser.load();
        parser.updateAll();

        echo.echoString("Setup Complete!");
    }

    /**
     * Updates the entire data file, writing from taskMem.
     */
    public void parseUpdateAll() {
        parser.updateAll();
    }
}

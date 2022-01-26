import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Memory class handles the storage and access to available Tasks.
 *
 * @author Rdac0
 */
public class Memory {

    private ArrayList<Task> taskMem;
    private int size;
    private Echo echo;

    /**
     * Creates a Memory object.
     */
    public Memory() {
        this.taskMem = new ArrayList<>();
        this.size = 0;
        this.echo = new Echo();
    }

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
     * Echoes all Tasks in Memory.
     */
    public void listAll() {
        if (size == 0) {
            echo.echoString("You've got nothing to do.");
        } else {
            for (int i = 1; i <= size; i++) {
                echo.echoString(i + ". " + this.getString(i-1));
            }
        }
    }

    /**
     * Adds a Task to memory.
     *
     * @param name The name of the Task to be made.
     */
    public void addTask(String name) {
        taskMem.add(new Task(name));
        size++;
        echo.echoString("added task: " + getString(size - 1));
    }

    /**
     * Adds a Deadline to memory.
     *
     * @param name The name of the Deadline to be made.
     * @param time The due time of the Deadline.
     */
    public void addDeadline(String name, String time) {
        try {
            taskMem.add(new Deadline(name, time));
            size++;
            echo.echoString("added deadline: " + getString(size - 1));
        }
        catch (DateTimeParseException e) {
            echo.echoString("Please format your date in yyyy-mm-dd");
        }
    }

    /**
     * Adds an Event to memory.
     *
     * @param name The name of the Event to be made.
     * @param time The time of the Event.
     */
    public void addEvent(String name, String time) {
        taskMem.add(new Event(name, time));
        size++;
        echo.echoString("added event: " + getString(size - 1));
    }

    /**
     * Sets a Task in Memory as done.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     */
    public void setDone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setDone();
            echo.echoString("Cool! You've done this task:\n  " +
                    getString(address));
        }
    }

    /**
     * Sets a Task in Memory as undone.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     */
    public void setUndone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setUndone();
            echo.echoString("This task is now undone:\n  " +
                    getString(address));
        }
    }

    /**
     * Removes a Task from memory.
     *
     * @param fakeAddress The address of the Task to request, as shown to the user.
     */
    public void deleteTask(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            echo.echoString("removed: " + getString(address));
            taskMem.remove(address);
            size--;
        }
    }
}

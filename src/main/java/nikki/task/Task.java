package nikki.task;

import java.time.format.DateTimeFormatter;

import nikki.NikkiException;

/**
 * Class to encapsulate basic properties and methods of a Task
 */
public class Task {
    /** Date format for all Task object */
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** Mark in checkbox of completed Task */
    private static final char COMPLETED_MARK = 'X';

    /** Mark in checkbox of uncompleted Task */
    private static final char INCOMPLETE_MARK = ' ';

    /** Name of Task */
    private String name;

    /** Status of completion of Task */
    private Boolean isDone;

    /** Tag of Task */
    private char tag = ' ';

    /**
     * Instantiates Task with name
     * Default tag is ' ', and default status is false
     *
     * @param name name of Task
     */
    public Task(String name) {
        this(name, ' ', false);
    }

    /**
     * Instantiates Task with name and status
     * Default tag is ' '
     *
     * @param name name of Task
     * @param isDone status of completion of Task
     */
    public Task(String name, Boolean isDone) {
        this(name, ' ', isDone);
    }

    /**
     * Instantiates Task with name and tag
     * Default status is false
     *
     * @param name name of Task
     * @param tag tag of Task
     */
    public Task(String name, char tag) {
        this(name, tag, false);
    }

    /**
     * Instantiates Task with name, tag, and status
     *
     * @param name name of Task
     * @param tag tag of Task
     * @param isDone status of completion of Task
     */
    public Task(String name, char tag, Boolean isDone) {
        this.name = name;
        this.tag = tag;
        this.isDone = isDone;
    }

    /**
     * Marks the Task (marked as done)
     *
     * @return true if the state of Task was changed by marking (not done -> done)
     */
    public Boolean mark() {
        if (!this.isDone()) {
            this.isDone = true;
            return true;
        }

        return false;
    }

    /**
     * Unmarks the Task (marked as not done)
     *
     * @return true if the state of Task was changed by unmarking (done -> not done)
     */
    public Boolean unmark() {
        if (this.isDone()) {
            this.isDone = false;
            return true;
        }

        return false;
    }

    /**
     * Getter for the tag of the Task
     *
     * @return tag of the Task
     */
    public char getTag() {
        return this.tag;
    }

    /**
     * Getter for the name of the Task
     *
     * @return name of the Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the state (done) of the Task
     *
     * @return Task is done or not
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the tag, status, and name of the Task, formatted.
     *
     * @return formatted string of the Task info
     */
    public String nameWithStatus() {
        return String.format("[%c][%c] %s",
                this.getTag(),
                this.isDone() ? Task.COMPLETED_MARK : Task.INCOMPLETE_MARK,
                this.name);
    }

    /**
     * Returns Task info in a standard format for saving in file.
     *
     * @return formatted string for saving Task
     */
    public String toFileSaveFormat() {
        return String.format("%c||%c||%s",
                this.getTag(),
                this.isDone() ? '1' : '0',
                this.name);
    }

    /**
     * Parses a formatted string from file storage, then returns the Task object
     *
     * @return Task object represented by the string
     */
    public static Task parseFileSaveFormat(String fmt) throws NikkiException {
        // Split at "||"
        String[] taskInfo = fmt.split("\\|\\|");

        if (taskInfo.length < 3) {
            throw new NikkiException("Wrong format");
        }

        // Extract relevant task information
        String taskTag = taskInfo[0];
        Boolean taskStatus = taskInfo[1].equals("1");
        String taskName = taskInfo[2];

        Task task;
        switch (taskTag) {
        case "T":
            task = new Todo(taskName, taskStatus);
            break;

        case "D":
            try {
                String deadline = taskInfo[3];
                task = new Deadline(taskName, deadline, taskStatus);
            } catch (IndexOutOfBoundsException e) {
                throw new NikkiException("Wrong format");
            }
            break;

        case "E":
            try {
                String eventDate = taskInfo[3];
                task = new Event(taskName, eventDate, taskStatus);
            } catch (IndexOutOfBoundsException e) {
                throw new NikkiException("Wrong format");
            }
            break;

        default:
            task = new Task(taskName, taskStatus);
            break;
        }

        return task;
    }

    /**
     * String representation of Task.
     *
     * @return name of Task
     */
    @Override
    public String toString() {
        return this.name;
    }
}

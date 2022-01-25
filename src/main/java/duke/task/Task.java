package duke.task;

import duke.DukeException;

public class Task {
    private static final char completedMark = 'X';
    private static final char incompleteMark = ' ';

    private String name;
    private Boolean done;
    private char tag = ' ';

    public Task(String name) {
        this(name, ' ', false);
    }

    public Task(String name, Boolean done) {
        this(name, ' ', done);
    }

    public Task(String name, char tag) {
        this(name, tag, false);
    }

    public Task(String name, char tag, Boolean done) {
        this.name = name;
        this.tag = tag;
        this.done = done;
    }

    /**
     * Marks the duke.task.Task (marked as done)
     *
     * @return true if the state of duke.task.Task was changed by marking (not done -> done)
     */
    public Boolean mark() {
        if (!this.isDone()) {
            this.done = true;
            return true;
        }

        return false;
    }

    /**
     * Unmarks the duke.task.Task (marked as not done)
     *
     * @return true if the state of duke.task.Task was changed by unmarking (done -> not done)
     */
    public Boolean unmark() {
        if (this.isDone()) {
            this.done = false;
            return true;
        }

        return false;
    }

    /**
     * Getter for the tag of the duke.task.Task
     *
     * @return tag of the duke.task.Task
     */
    public char getTag() {
        return this.tag;
    }

    /**
     * Getter for the name of the duke.task.Task
     *
     * @return name of the duke.task.Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the state (done) of the duke.task.Task
     *
     * @return duke.task is done or not
     */
    public Boolean isDone() {
        return this.done;
    }

    /**
     * Returns the tag, status, and name of the duke.task.Task, formatted.
     *
     * @return formatted string of the duke.task.Task info
     */
    public String nameWithStatus() {
        return String.format("[%c][%c] %s",
                this.getTag(),
                this.isDone() ? Task.completedMark : Task.incompleteMark,
                this.name);
    }

    /**
     * Returns duke.task.Task info in a standard format for saving in file.
     *
     * @return formatted string for saving duke.task.Task
     */
    public String fileSaveFormat() {
        return String.format("%c||%c||%s",
                this.getTag(),
                this.isDone() ? '1' : '0',
                this.name);
    }

    /**
     * Parses a formatted string from file storage, then returns the duke.task.Task object
     *
     * @return duke.task.Task object represented by the string
     */
    public static Task parseFileSaveFormat(String fmt) throws DukeException {
        // Split at "||"
        String[] taskInfo = fmt.split("\\|\\|");

        if (taskInfo.length < 3) {
            throw new DukeException("Wrong format");
        }

        // Extract relevant duke.task information
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
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("Wrong format");
            }
            break;

        case "E":
            try {
                String eventDate = taskInfo[3];
                task = new Event(taskName, eventDate, taskStatus);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("Wrong format");
            }
            break;

        default:
            task = new Task(taskName, taskStatus);
            break;
        }

        return task;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

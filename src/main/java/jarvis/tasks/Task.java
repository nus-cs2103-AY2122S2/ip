package jarvis.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import jarvis.exceptions.InvalidTaskException;
import jarvis.exceptions.TagNotFoundException;
import jarvis.tags.Tag;
import jarvis.tags.TagList;

public abstract class Task {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    private final String description;
    private Boolean isDone;
    private LocalDate dateCompleted;
    private TagList tags;

    /**
     * Constructor for the task object.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateCompleted = null;
        this.tags = new TagList();
    }

    /**
     * Factory method of task.
     *
     * @param taskString string representation of task
     * @return a task object
     * @throws InvalidTaskException invalid task
     */
    public static Task of(String taskString) throws InvalidTaskException {
        Task task;
        String[] taskArr = taskString.split(" ", 2);

        if (taskArr.length < 2) {
            throw new InvalidTaskException("The description of a task cannot be empty.");
        }
        String taskType = taskArr[0];
        String[] params;

        switch (taskType) {
        case TODO:
            task = new jarvis.tasks.Todo(taskArr[1]);
            break;

        case DEADLINE:
            params = taskArr[1].split(" /by ");
            if (params.length < 2) {
                throw new InvalidTaskException("The deadline of a task cannot be empty.");
            }
            try {
                LocalDate date = LocalDate.parse(params[1]);
                task = new Deadline(params[0], date);
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException("Invalid date format! [yyyy-mm-dd] Eg. [2019-12-01]");
            }
            break;

        case EVENT:
            params = taskArr[1].split(" /at ");
            if (params.length < 2) {
                throw new InvalidTaskException("The time of an event cannot be empty.");
            }
            try {
                LocalDate date = LocalDate.parse(params[1]);
                task = new jarvis.tasks.Event(params[0], date);
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException("Invalid date format! [yyyy-mm-dd] Eg. [2019-12-01]");
            }
            break;

        default:
            throw new InvalidTaskException("Invalid task!\nType help to see the list of commands");
        }
        return task;
    }

    /**
     * Convert a string from csv format to task.
     *
     * @param taskString csv string of task
     * @param delimiter  separator
     * @return task object
     * @throws InvalidTaskException invalid task
     */
    public static Task fromCsv(String taskString, String delimiter) throws InvalidTaskException {
        Task task;
        String[] taskArr = taskString.split(delimiter);

        if (taskArr.length < 3) {
            throw new InvalidTaskException("Insufficient values");
        }

        String taskCode = taskArr[0];
        boolean taskCompleted = Objects.equals(taskArr[1], "x");
        String taskDescription = taskArr[2];

        switch (taskCode) {
        case Todo.TASK_CODE:
            task = new Todo(taskDescription);
            break;
        case Deadline.TASK_CODE:
            if (taskArr.length < 4) {
                throw new InvalidTaskException("Insufficient values");
            }
            LocalDate by = LocalDate.parse(taskArr[3]);
            task = new Deadline(taskDescription, by);
            break;
        case Event.TASK_CODE:
            if (taskArr.length < 4) {
                throw new InvalidTaskException("Insufficient values");
            }
            LocalDate at = LocalDate.parse(taskArr[3]);
            task = new Event(taskDescription, at);
            break;
        default:
            throw new InvalidTaskException("Task format is invalid");
        }

        if (taskCompleted) {
            task.markAsCompleted();
        }
        return task;
    }

    /**
     * Checks if the task description contains the keyword.
     *
     * @param keyword String to search for
     * @return true if the description contains the keyword
     */
    public boolean matches(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Get the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the date completed of the task.
     *
     * @return date completed of the task
     */
    public String getDateCompleted() {
        return this.dateCompleted.toString();
    }

    /**
     * Get all the tags associated with the task.
     * @return tagList
     */
    public TagList getTags() {
        return this.tags;
    }

    /**
     * Add a tag to the task.
     */
    public void tag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Checks if the task contains a given tag.
     * @param tagName name of the tag
     * @return true or false
     */
    public boolean hasTag(String tagName) {
        return tags.contains(tagName);
    }

    /**
     * Gets the tag object by the tag name.
     * @param tagName name of the tag
     * @return tag object
     */
    public Tag getTag(String tagName) throws TagNotFoundException {
        return tags.getTag(tagName);
    }

    /**
     * Removes a tag from the task.
     */
    public void untag(Tag tag) {
        this.tags.remove(tag);
    }

    /**
     * Mark the current task as completed.
     */
    public void markAsCompleted() {
        this.isDone = true;
        this.dateCompleted = LocalDate.now();
    }

    /**
     * Mark the current task as uncompleted.
     */
    public void markAsUncompleted() {
        this.isDone = false;
        this.dateCompleted = null;
    }

    /**
     * Get the string representation of the task status.
     *
     * @return 'x' or ' ' depending on the status
     */
    public String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    /**
     * Get the csv representation of the task.
     *
     * @return string in csv format
     */
    public abstract String toCsv(String delimiter);

    /**
     * Gets the string representation of the task.
     * @return string of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}

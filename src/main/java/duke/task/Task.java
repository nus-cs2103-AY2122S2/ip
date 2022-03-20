package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.ui.Ui;


public class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String content;
    protected LocalDateTime date;
    protected TagList tags;
    protected Ui ui;

    /**
     * Constructs a Task object with content string.
     *
     * @param content String content to be put in the task.
     */
    public Task(String content, Ui ui) {
        this.isDone = false;
        this.content = content;
        this.date = null;
        tags = new TagList(ui);
        this.ui = ui;
    }

    /**
     * Constructs a Task object with content string and whether the task is done.
     *
     * @param content String content to be put in the task.
     * @param isDone  boolean boolean to show whether task is done.
     */
    public Task(String content, boolean isDone, Ui ui) {
        this.isDone = isDone;
        this.content = content;
        tags = new TagList(ui);
        this.ui = ui;
    }

    /**
     * Constructs a Task object with content string and date.
     *
     * @param content String content to be put in the task.
     * @param isDone  boolean boolean to show whether task is done.
     * @param tags    ArrayList of Tag objects.
     * @param ui      Ui UI object.
     */
    public Task(String content, boolean isDone, ArrayList<Tag> tags, Ui ui) {
        this.isDone = isDone;
        this.content = content;
        this.tags = new TagList(tags, ui);
        this.ui = ui;
    }

    /**
     * Constructs a Task object with content string and date.
     *
     * @param content String content to be put in the task.
     * @param date    LocalDateTime date of Task.
     * @param ui      Ui UI object.
     */
    public Task(String content, LocalDateTime date, Ui ui) {
        this.isDone = false;
        this.content = content;
        this.date = date;
        tags = new TagList(ui);
        this.ui = ui;
    }

    /**
     * Constructs a Task object with content string, date, and whether the task is done.
     *
     * @param content String content to be put in the task.
     * @param date    LocalDateTime date of Task.
     * @param isDone  boolean boolean to show whether task is done.
     * @param ui      Ui UI object.
     */
    public Task(String content, LocalDateTime date, boolean isDone, Ui ui) {
        this.isDone = isDone;
        this.content = content;
        this.date = date;
        tags = new TagList(ui);
        this.ui = ui;
    }

    /**
     * Constructs a Task object with content string, date, and whether the task is done.
     *
     * @param content String content to be put in the task.
     * @param date    LocalDateTime date of Task.
     * @param isDone  boolean boolean to show whether task is done.
     * @param tags    ArrayList of Tags.
     * @param ui      Ui UI object.
     */
    public Task(String content, LocalDateTime date, boolean isDone, ArrayList<Tag> tags, Ui ui) {
        this.isDone = isDone;
        this.content = content;
        this.date = date;
        this.tags = new TagList(tags, ui);
        this.ui = ui;
    }

    /**
     * Returns isDone instance variable.
     *
     * @return boolean isDone instance variable.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the content string.
     *
     * @return String content instance variable.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the TagList.
     *
     * @return TagList object.
     */
    public TagList getTagList() {
        return tags;
    }

    /**
     * Marks this task as done.
     *
     * @return String Result of the mark.
     */
    public String mark() {
        this.isDone = true;
        String result = "Nice! I've marked this task as done: " + this.toString();
        return result;
    }

    /**
     * Unmarks this task.
     *
     * @return String Result of the mark.
     */
    public String unmark() {
        this.isDone = false;
        String result = "I've unmarked this task: " + this.toString();
        return result;
    }

    /**
     * Compares two tasks based on date and content.
     * A date is larger if it occurs earlier, and content is larger based on String compareTo.
     *
     * @param task Task to be compared with.
     * @return int Integer that shows which is larger.
     */
    @Override
    public int compareTo(Task task) {
        if (this.date != null && task.date != null) {
            return this.date.compareTo(task.date);
        } else if (this.date != null) {
            return -1;
        } else if (task.date != null) {
            return 1;
        } else {
            return this.content.compareTo(task.content);
        }
    }
}

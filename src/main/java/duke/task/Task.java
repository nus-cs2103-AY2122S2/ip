package duke.task;

import java.time.LocalDateTime;

import duke.ui.Ui;


public class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String content;
    protected LocalDateTime date;

    /**
     * Constructs a Task object with content string.
     *
     * @param content String content to be put in the task.
     */
    public Task(String content) {
        this.isDone = false;
        this.content = content;
        this.date = null;
    }

    /**
     * Constructs a Task object with content string and whether the task is done.
     *
     * @param content String content to be put in the task.
     * @param isDone boolean boolean to show whether task is done.
     */
    public Task(String content, boolean isDone) {
        this.isDone = isDone;
        this.content = content;
    }

    /**
     * Constructs a Task object with content string and date.
     *
     * @param content String content to be put in the task.
     * @param date LocalDateTime date of Task.
     */
    public Task(String content, LocalDateTime date) {
        this.isDone = false;
        this.content = content;
        this.date = date;
    }

    /**
     * Constructs a Task object with content string, date, and whether the task is done.
     *
     * @param content String content to be put in the task.
     * @param date LocalDateTime date of Task.
     * @param isDone boolean boolean to show whether task is done.
     */
    public Task(String content, LocalDateTime date, boolean isDone) {
        this.isDone = isDone;
        this.content = content;
        this.date = date;
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
     * @return String content instance variable.
     */
    public String getContent() {
        return content;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
        Ui.printDivider();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + this.toString());
        Ui.printDivider();
    }

    /**
     * Unmarks this task.
     */
    public void unmark() {
        this.isDone = false;
        Ui.printDivider();
        System.out.println("    I've unmarked this task: ");
        System.out.println("    " + this.toString());
        Ui.printDivider();
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

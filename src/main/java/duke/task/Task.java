package duke.task;

import duke.ui.Ui;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    protected boolean isDone;
    protected String content;
    protected LocalDateTime date;

    public Task(String content) {
        this.isDone = false;
        this.content = content;
        this.date = null;
    }

    public Task(String content, boolean isDone) {
        this.isDone = isDone;
        this.content = content;
    }

    public Task(String content, LocalDateTime date) {
        this.isDone = false;
        this.content = content;
        this.date = date;
    }

    public Task(String content, LocalDateTime date, boolean isDone) {
        this.isDone = isDone;
        this.content = content;
        this.date = date;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void mark() {
        this.isDone = true;
        Ui.printDivider();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + this.toString());
        Ui.printDivider();
    }

    public void unmark() {
        this.isDone = false;
        Ui.printDivider();
        System.out.println("    I've unmarked this task: ");
        System.out.println("    " + this.toString());
        Ui.printDivider();
    }

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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task implements Comparable<Task>{
    protected boolean isDone;
    protected String content;
    protected LocalDateTime date;

    public Task(String content) {
        this.isDone = false;
        this.content = content;
        this.date = null;
    }

    public Task(String content, LocalDateTime date) {
        this.isDone = false;
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

    public void mark(ArrayList<Task> tasks) {
        this.isDone = true;
        Printer.printDivider();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + this.toString());
        Printer.printDivider();
        Duke.saveAsTextFile(tasks);
    }
    public void unmark(ArrayList<Task> tasks) {
        this.isDone = false;
        Printer.printDivider();
        System.out.println("    I've unmarked this task: ");
        System.out.println("    " + this.toString());
        Printer.printDivider();
        Duke.saveAsTextFile(tasks);
    }

    @Override
    public int compareTo(Task task) {
        if (this.date != null) {
            return this.date.compareTo(task.date);
        } else {
            return this.content.compareTo(task.content);
        }
    }
}

import java.util.ArrayList;

public class Task {
    protected boolean isDone;
    protected String content;

    public Task(String content) {
        this.isDone = false;
        this.content = content;
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
}

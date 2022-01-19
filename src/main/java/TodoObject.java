public class TodoObject {
    private boolean isDone;
    private String content;

    public TodoObject(String content) {
        this.isDone = false;
        this.content = content;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getContent() {
        return content;
    }

    public void mark() {
        this.isDone = true;
        Printer.printDivider();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    [X] " + content);
        Printer.printDivider();
    }
    public void unmark() {
        this.isDone = false;
        Printer.printDivider();
        System.out.println("    I've unmarked this task: ");
        System.out.println("    [ ] " + content);
        Printer.printDivider();
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + content;
        } else {
            return "[] " + content;
        }
    }
}

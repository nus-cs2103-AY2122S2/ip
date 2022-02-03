public class Task {
    String description;
    boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void done() {
        isDone = true;
    }

    public void undo() {
        isDone = false;
    }
}

class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}

class Event extends Task {
    String at;

    public Event(String desc, String b) {
        super(desc);
        at = b;
    }
    public String toString() {
        return "[E]" + super.toString() + " (: " + at + ")";
    }
}

class Deadline extends Task {
    String by;

    public Deadline(String desc, String b) {
        super(desc);
        by = b;
    }
    public String toString() {
        return "[D]" + super.toString() + " (: " + by + ")";
    }
}

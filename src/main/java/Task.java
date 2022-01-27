abstract class Task {
    protected String taskName;
    protected boolean done;

    protected Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.done = done;
    }

    void setDone() {
        this.done = true;
    }

    void setUndone() {
        this.done = false;
    }

    abstract String updateIntoDatabase();

    static Task createTask(String type, Boolean done, String name, String date) {
        type = type.toUpperCase();
        if (type.equals("TODO")) {
            return new ToDo(name, done);
        } else if (type.equals("DEADLINE")) {
            return new Deadline(name, done, date);
        } else if (type.equals("EVENT")) {
            return new Event(name, done, date);
        } else {
            throw new IllegalArgumentException();
        }
    };
}

final class ToDo extends Task {

    protected ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    @Override
    public String toString() {
        String tag = "[T]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        return tag + doneIndicator + " " + this.taskName;
    }

    String updateIntoDatabase() {
        return "TODO\n" + String.valueOf(this.done) + "\n" + this.taskName + "\n" + "*** Next Task ***\n";
    }
}

final class Deadline extends Task {
    String deadline;

    protected Deadline(String taskName, boolean done, String deadline) {
        super(taskName, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String tag = "[D]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String deadline = "(by: " + this.deadline + ")";
        return tag + doneIndicator + " " + this.taskName + deadline;
    }

    String updateIntoDatabase() {
        return "DEADLINE\n" + String.valueOf(this.done) + "\n" + this.taskName + "\n" + this.deadline + "\n"
                + "*** Next Task ***\n";
    }
}

final class Event extends Task {
    String eventDate;

    protected Event(String taskName, boolean done, String eventDate) {
        super(taskName, done);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        String tag = "[E]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String eventDate = "(at: " + this.eventDate + ")";
        return tag + doneIndicator + " " + this.taskName + eventDate;
    }

    String updateIntoDatabase() {
        return "EVENT\n" + String.valueOf(this.done) + "\n" + this.taskName + "\n" + this.eventDate + "\n"
                + "*** Next Task ***\n";
    }
}
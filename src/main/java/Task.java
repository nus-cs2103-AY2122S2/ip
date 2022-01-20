abstract class Task {
    protected String taskName;
    protected boolean done;

    Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    void setDone() {
        this.done = true;
    }

    void setUndone() {
        this.done = false;
    }
}

class ToDo extends Task {

    ToDo(String taskName) {
        super(taskName);
    }
    
    @Override
    public String toString() {
        String tag = "[T]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        return tag + doneIndicator + " " + this.taskName;
    }
}

class Deadline extends Task {
    String deadline;

    Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }
    
    @Override
    public String toString() {
        String tag = "[D]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String deadline = "(by:" + this.deadline + ")";
        return tag + doneIndicator + " " + this.taskName + deadline;
    }
}

class Event extends Task {
    String eventDate;

    Event(String taskName, String eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }
    
    @Override
    public String toString() {
        String tag = "[E]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String deadline = "(at:" + this.eventDate + ")";
        return tag + doneIndicator + " " + this.taskName + deadline;
    }
}
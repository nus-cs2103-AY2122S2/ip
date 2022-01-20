public class Task {
    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;
    protected Type eventType;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.eventType = Type.TODO;
        this.time = "";
    }

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.eventType = Type.TODO;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getEventType() {
        return (eventType == Type.EVENT ? "[E]" :
                eventType == Type.TODO ? "[T]" :
                eventType == Type.DEADLINE ? "[D]" : "[ ]");
    }

    @Override
    public String toString() {
        return getEventType()
                + "[" + getStatusIcon() + "] "
                + description
                + (time != "" && eventType == Type.DEADLINE ? "(by: " + time + ")" :
                (time != "" && eventType == Type.EVENT ? "(at: " + time + ")" : ""));
    }

    public void markAsComplete() {
        isDone = true;
    }

    public void markAsIncomplete() {
        isDone = false;
    }

    public void setTodo() {
        eventType = Type.TODO;
    }

    public void setEvent() {
        eventType = Type.EVENT;
    }

    public void setDeadline() {
        eventType = Type.DEADLINE;
    }
}

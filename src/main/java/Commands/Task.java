package Commands;

public class Task  {

    private String description;
    private String type;
    private boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getType() { return this.type; }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTime() { return ""; };

    public String getDate() { return ""; };

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean matchDescription(String s) {
        return this.description.contains(s);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
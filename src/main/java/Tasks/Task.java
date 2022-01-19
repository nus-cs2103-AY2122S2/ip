package Tasks;

public class Task {

    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setComplete() {
        this.status = true;
    }

    public void setIncomplete() {
        this.status = false;
    }

    @Override
    public String toString() {
        return status
                ? "[X] " + description
                : "[ ] " + description;
    }
}

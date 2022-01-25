package duke.task;

public class Task {

    private String name;
    private Boolean status;

    public Task(String description) {
        this.name = description;
        this.status = false;
    }

    public void markTask() {
        this.status = true;
    }

    public void unmarkTask() {
        this.status = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getSymbol() {
        return (this.status) ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String formatText() {
        return "Text formatted";
    }

}

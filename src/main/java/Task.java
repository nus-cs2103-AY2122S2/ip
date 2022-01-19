public class Task {

    private boolean status;
    private String details;

    public Task(String s) {
        this.details = s;
        this.status = false;
    }

    public String mark() {
        this.status = true;
        return "Task " + details + " has been marked completed.";
    }

    public String unmark() {
        this.status = false;
        return "Task " + details + " has been marked incomplete.";
    }

    public String toString() {
        String out = "";
        if (status) {
            out = "[X] " + details;
        } else {
            out = "[ ] " + details;
        }
        return out;
    }

}

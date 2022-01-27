public class Task {

    private boolean status;
    private String details;
    private int id;

    //ToDo
    public Task(String details) {
        this.details = details;
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

    @Override
    public String toString() {
        String out = "";
        if (status) {
            out = " | 1 | " + details;
        } else {
            out = " | 0 | " + details;
        }
        return out;
    }

}

package duke.task;

public abstract class Task {

    String activity;
    boolean status = false;
    String type = " "; // Necessary for spacing

    public Task(String activity, String type) {
        this.activity = activity;
        this.type = type;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract String printTask();

}

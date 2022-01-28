package duke.task;

public abstract class Task {
    String activity;
    int status = 0;
    String type = " ";

    public Task(String activity, String type) {
        this.activity = activity;
        this.type = type;
    }

    public void updateStatus(int status) {
        this.status = status;
    }

    public abstract String printTask();

}

import java.util.ArrayList;


public class Task {
    private String taskName;
    private Boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public void markTask() {
        this.completed = true;
    }

    public void unmarkTask() {
        this.completed = false;
    }



    @Override
    public String toString() {
        String completedOrNah = this.completed? "X":"";
        String display = "[" + completedOrNah + "] " + taskName;
        return display;
    }


}

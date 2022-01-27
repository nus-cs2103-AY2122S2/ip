package task;

/**
 * Task class
 */
public class Task {

    String taskName;
    boolean isMarked;


    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getName() {
        return this.taskName;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        String status = isMarked ? "X" : " ";
        return String.format("[%s] %s", status, this.taskName);
    }




}

package duke;

public class Task {
    //Attributes of a task
    String taskName;
    boolean done;

    public Task(String taskName){
        this.taskName = taskName;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void printTask() {
        if (this.done) {
            System.out.println("[X] " + this.taskName);
        } else {
            System.out.println("[ ] " + this.taskName);
        }
    }
}

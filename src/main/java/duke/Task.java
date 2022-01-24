package duke;

public class Task {
    //Attributes of a task
    private String taskName;
    private boolean done;

    public Task(String taskName){
        this.taskName = taskName;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String newName) {
        this.taskName = newName;
    }

    public void printTask(){
        if(this.getDone()){
            System.out.println("[X] " + this.getTaskName());
        } else {
            System.out.println("[ ] " + this.getTaskName());
        }
    }
}

public class Task {
    private boolean isDone;
    private String taskDescription;

    public Task (String taskDescription){
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public boolean checkisDone(){
        return this.isDone;
    }

    public String getTaskDescription(){
        return this.taskDescription;
    }

    public void markAsDone(){
        this.isDone = true;

    }

    public void markAsNotDone(){
        this.isDone = false;
    }

    public String toString(){
        String isDoneRepresentation;
        if (this.checkisDone() == true) {
            isDoneRepresentation = "[Done!] ";
        } else {
            isDoneRepresentation = "[Not Done Yet!] ";
        }
        return isDoneRepresentation + this.getTaskDescription();
    }

}

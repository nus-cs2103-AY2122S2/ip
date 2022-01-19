public class Task {
    private boolean isDone;
    private String taskDescription;

    public Task (String taskDescription){
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public boolean checkIsDone(){
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
        if (this.checkIsDone() == true) {
            isDoneRepresentation = "[Done!] ";
        } else {
            isDoneRepresentation = "[] ";
        }
        return isDoneRepresentation + this.getTaskDescription();
    }

}

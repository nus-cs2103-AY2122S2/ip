public class Task {
    String description;
    boolean isDone;

    public Task(String description) { //task constructor
        this.description = description;
        this.isDone = false; //initialise as false
    }

    public String getStatusIcon() { //will return either "X" or " " depending if task is done or not
        return isDone
                ? "X" //if true mark with X
                : " "; //if false do not mark, leave as empty space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
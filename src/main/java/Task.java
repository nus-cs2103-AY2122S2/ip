public class Task {
    protected String description;
    protected boolean isDone;
    private static final int DESCRIPTION_OFFSET = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){
        if(this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskNotDone() {
        this.isDone = false;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription(); // start from 1 because 0 is a space
    }
}

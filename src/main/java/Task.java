public class Task {
    private String title;
    private boolean isDone;

    public Task(String title)  {
        this.title = title;
        this.isDone = false;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String stateIcon = this.isDone ? "X" : " ";
        return "["+stateIcon+"]"+" "+this.title;
    }
}

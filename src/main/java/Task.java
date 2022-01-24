public class Task {
    private final String name;
    private boolean isDone;
    //can make isDone final for good practice

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void switchStatus() {
        this.isDone = !this.isDone;
    }

    public String markAsDone() {
        String output;
        if (this.isDone) {
            output = "Sorry, the task is actually done!";
        } else {
            output = String.format("Nice!, I have marked this task as done: \n      %s", this);
        }
        this.isDone = true;
        return output;
    }

    public String markAsNotDone() {
        String output;
        if (!this.isDone) {
            output = "Sorry, the task has not been done!";
        } else {
            output = String.format("Ok, I have marked this task as not done: \n      %s", this);
        }
        this.isDone = false;
        return output;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}

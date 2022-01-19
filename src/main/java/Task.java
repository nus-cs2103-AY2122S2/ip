public class Task {
    protected String taskName;
    protected boolean isMarked;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isMarked = false;
    }

    public void toggleMarked() {
        this.isMarked = !this.isMarked;
    }

    public String getTaskName() {
        return this.taskName;
    }


    public String toMarkedString() {
        if (this.isMarked) {
            return "[X] " + this.toString();
        } else {
            return "[ ] " + this.toString();
        }
    }

    @Override
    public String toString() {
        return this.taskName;
//        if (this.isMarked) {
//            return "[X] " + this.taskName;
//        } else {
//            return "[ ] " + this.taskName;
//        }
    }
}

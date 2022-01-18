public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = " ";
        if (isDone) {
            status = "X";
        }
        return "[" + status + "] " + name;
    }

    public void markAsDone() throws InvalidActionException {
        if (isDone) { throw new InvalidActionException("Task already done!"); }
        this.isDone = true;
    }

    public void markUndone() throws InvalidActionException {
        if (!isDone) { throw new InvalidActionException("Task already not done!"); }
        this.isDone = false;
    }
}

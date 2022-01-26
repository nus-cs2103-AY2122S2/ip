public abstract class Command {
    Task task;
    String details;
    boolean isExit;

    public Command() {

    }

    public Command(String d) {
        this.details = d;
        this.isExit = false;
    }

    public Command(Task task) {
        this.task = task;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException;

    public boolean haveTask() {
        return this.task != null;
    }

    public void setExit(boolean b) {
        this.isExit = b;
    }
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public abstract String toString();
}

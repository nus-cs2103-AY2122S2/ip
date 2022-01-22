abstract class Command{
    protected Task task;
    protected Integer index;

    public Command(Task t, Integer number) {
        this.task = t;
        this.index = number;
    }

    public abstract void execute(TaskList tasks);

    public abstract boolean isExit();
}

public abstract class Command {
    protected Task task;
    protected int num;

    public Command(Task task, Integer num) {
        this.task = task;
        this.num = num;
    }

    public abstract void execute(TaskList tasks);
    public abstract boolean isExit();
}

public abstract class Command {
    public abstract boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui);
}

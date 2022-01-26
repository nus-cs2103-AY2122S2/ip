public class TodoCommand extends Command {
    public TodoCommand (Task task) {
        super(task);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.task.getDescription().isEmpty()) {
            throw new DogeException("So doing NOTHING is a task? Task details cannot be left empty!");
        } else {
            tasks.addTask(this.task);
        }
    }

    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }

}

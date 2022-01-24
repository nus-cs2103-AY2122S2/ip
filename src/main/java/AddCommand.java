public class AddCommand extends Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out = tasks.add(task);
        tasks.save(storage);
        ui.outputMessage(out);
        return false;
    }
}

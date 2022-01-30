public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(this.task);
        ui.showMessage("TASK ADDED:\n"
                + task + "\n"
                + taskList.size() + " TASK(S) NOW.");
        storage.writeToFile(taskList);
        return true;
    }
}

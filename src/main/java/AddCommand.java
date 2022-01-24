public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(this.task);
        ui.printOutput("Got it! I've added this task:\n  "
                + ui.showIndent() + this.task + "\n"
                + ui.showIndent() + taskList.getListStatus());

        storage.saveToHardDisk(taskList);
    }

    public boolean isExit() {
        return false;
    }
}

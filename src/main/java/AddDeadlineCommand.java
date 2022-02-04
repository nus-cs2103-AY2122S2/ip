public class AddDeadlineCommand extends Command {
    private final Deadline newDeadline;

    AddDeadlineCommand(Deadline newDeadline) {
        super();
        this.newDeadline = newDeadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new Deadline(newDeadline.getTask(), newDeadline.getDate()));
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");

    }
}

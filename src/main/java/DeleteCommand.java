public class DeleteCommand extends Command {
    public DeleteCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(this.tokenizedCommand[1]) - 1;
        Task t = tasks.getTask(index);
        tasks.removeTask(t);
        storage.updateSavedTasks(t.getSaveFormat(), "");
        ui.printMsg("Noted. I've removed this task:\n  " + t + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}

public class UnmarkCommand extends Command {
    String commandArgument;
    public UnmarkCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = Integer.parseInt(commandArgument) - 1;
        if (index < 0 || index > tasks.getNumberOfTasks() - 1) {
            throw new InvalidIndexException();
        }
        Task currentTask = tasks.getTaskByIndex(index);
        currentTask.markAsNotDone();
        ui.printConfirmUnmark(currentTask);
        storage.writeTaskToFile(tasks);
    }
}

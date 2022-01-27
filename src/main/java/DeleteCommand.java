public class DeleteCommand implements Command {
    private static final int COMMAND_LENGTH = 6;
    private static final String MESSAGE = "Got it! I have added this task:";

    private final int index;

    public DeleteCommand(String input) throws UltoiException {
        try {
            this.index = Integer.parseInt(input.substring(this.COMMAND_LENGTH + 1)) - 1;
        } catch (Exception e) {
            throw UltoiException.commandMismatchException();
        }
    }

    public void execute(UltoiUi ui, TaskList tasks, Storage storage) throws UltoiException {
        Task deletedTask;

        try {
            deletedTask = tasks.deleteTask(this.index);
        } catch (Exception e) {
            throw UltoiException.indexOutOfBoundException();
        }

        storage.save(tasks);
        ui.showMsg(generateMsg(deletedTask, tasks));
        return;
    }

    private String generateMsg(Task deletedTask, TaskList tasks) {
        return this.MESSAGE + "\n"
                + UltoiUi.INDENT + deletedTask.toString() + "\n"
                + tasks.generateNumOfTasksMsg();
    }
}

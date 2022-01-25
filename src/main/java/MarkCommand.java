public class MarkCommand extends Command{
    private static final boolean IS_EXIT = false;
    private int taskIndex;
    private boolean isDone;

    public MarkCommand(int taskIndex, boolean isDone) {
        super(IS_EXIT);
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(taskIndex, isDone);
            String message;
            if (isDone) {
                message = Ui.MARK_MESSAGE;
            } else {
                message = String.format(Ui.UNMARK_MESSAGE);
            }
            message += String.format("\n  %s", tasks.getTask(taskIndex).toString());
            ui.appendMessage(message);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}

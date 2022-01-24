import java.io.IOException;

public class DeleteCommand extends Command{
    private String description;
    private final BotException exception = new BotException();

    public DeleteCommand(String description) {
        this.description = description;
    }

    private static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) throws IOException {
        taskList.deleteCommand(description);
        if (! checkNumeric(description)) {
            exception.notNumeric("delete");
        } else {
            int taskNumber = Integer.parseInt(description);
            Task removeTask = taskList.removeTask(taskNumber);
            storage.deleteTask(taskNumber);
            ui.showDeleteTask(removeTask, taskList.getTotalTask());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

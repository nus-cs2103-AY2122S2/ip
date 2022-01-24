import java.io.IOException;
import java.util.Optional;

public class UpdateMarkCommand extends Command{
    private String description;
    private boolean isDone;
    private final BotException exception = new BotException();

    public UpdateMarkCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private static boolean checkNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable)
            throws IOException {
        String stateDescription;
        if (isDone) {
            stateDescription = "mark";
        } else {
            stateDescription = "unmark";
        }

        if (! checkNumeric(description)) {
            exception.notNumeric(stateDescription);
        } else {
            int taskNumber = Integer.parseInt(description);
            Task task = taskList.getTask(taskNumber);
            ui.showTaskMark(task, isDone);
            storage.changeStatusTask(taskNumber, task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] inputWords;

    public DeleteCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_DELETE);
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(Messages.OUT_OF_BOUNDS_MSG(taskNumber));
            }
            ui.print(Messages.DELETE_SUCCESS);
            ui.print(tasks.getTaskStatement(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            tasks.printTaskCount();
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Messages.DELETE_ERROR);
        } catch (NumberFormatException e) {
            ui.showError(Messages.UNKNOWN_DELETE);
        }
    }
}

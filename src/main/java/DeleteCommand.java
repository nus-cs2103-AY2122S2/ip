import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] inputWords;

    public DeleteCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to delete clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("Cannot delete" +
                        " as task %d does not exist!", taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.getTaskStatement(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            tasks.printTaskCount();
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError("Error saving.");
        }
    }
}

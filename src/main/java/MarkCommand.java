import java.io.IOException;

public class MarkCommand extends Command {

    private String[] inputWords;

    public MarkCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) { //e.g mark 2, cannot be mark 2 2 or just mark
                throw new InvalidArgumentException("Please specify what to mark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.getTaskStatement(taskNumber - 1));
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError("Error saving.");
        }
    }
}

import java.io.IOException;

public class UnmarkCommand extends Command {

    private String[] inputWords;

    public UnmarkCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to unmark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            tasks.get(taskNumber - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.getTaskStatement(taskNumber - 1));
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError("Error saving.");
        }
    }
}

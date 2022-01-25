import java.io.IOException;

public class MarkCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private int position;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = this.fullCommand.split(" ", 2);
        this.position = Integer.parseInt(splicedFullCommand[1]);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException {
        if (position < 1 || position > tasks.getTaskSize()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            if (task.isDone == true) {
                throw new DukeException("Task is already marked as done!");
            } else {
                task.mark();
                storage.setInFile(position, task.taskDescriptionForFile());
                ui.showMarkMessage(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

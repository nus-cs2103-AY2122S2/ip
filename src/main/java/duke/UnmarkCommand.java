package main.java.duke;
import java.io.IOException;

public class UnmarkCommand extends Command {

    private int taskToUnmark;

    public UnmarkCommand(int taskToMark) {
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        tasks.unmark(taskToUnmark);
        ui.showUnmarkTask(tasks.getTask(taskToUnmark));
        try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
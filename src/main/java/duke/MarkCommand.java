package main.java.duke;
import java.io.IOException;

public class MarkCommand extends Command {

    private int taskToMark;

    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        tasks.mark(taskToMark);
        ui.showMarkTask(tasks.getTask(taskToMark));
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
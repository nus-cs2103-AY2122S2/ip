import java.io.IOException;

public class MarkCommand extends Command{
    private boolean markType;
    private int taskId;

    public MarkCommand(boolean markType, int taskId) {
        this.markType = markType;
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws PykeException, IOException {
        if (taskId <= 0 || taskId > taskList.getSize()) {
            throw new InvalidNumberException();
        } else {
            taskList.setTaskStatus(taskId - 1, markType);
            storage.saveFile(taskList);
            if (!markType) {
                ui.outputText("OK, I've marked this task as not done yet: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            } else {
                ui.outputText("Nice! I've marked this task as done: \n  " + taskList.getTaskOutputStyle(taskId - 1));
            }
        }
    }
    public boolean isExit() {
        return false;
    }
}

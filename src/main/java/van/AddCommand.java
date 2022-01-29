package van;

public class AddCommand implements Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        taskList.addTask(newTask);
        ui.taskMessage(newTask, taskList.getTaskCount());
        return false;
    }

    @Override
    public String toString() {
        return newTask.getStatus();
    }
}

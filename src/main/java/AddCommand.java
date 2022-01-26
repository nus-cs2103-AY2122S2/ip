public class AddCommand extends Command {
    Task task;

    public AddCommand(String userTaskString, Task task) {
        super(userTaskString);
        this.task = task;
    }

    public void executeTask(TaskList taskList, FileManager fileManager) {
        taskList.addTask(this.task, true);
    }

}

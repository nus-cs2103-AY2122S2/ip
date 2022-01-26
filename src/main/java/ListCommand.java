public class ListCommand extends Command{
    int taskIdx;

    public ListCommand(String userTaskString) {
        super(userTaskString);
    }

    public void executeTask(TaskList taskList, FileManager fileManager) {
        taskList.printUserTasks();
    }

}



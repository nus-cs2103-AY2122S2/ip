public class DeleteCommand extends Command{
    int taskIdx;

    public DeleteCommand(String userTaskString, int taskIdx) {
        super(userTaskString);
        this.taskIdx = taskIdx;
    }

    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.deleteTask(this.taskIdx);
    }

}



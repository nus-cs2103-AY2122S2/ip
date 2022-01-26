public class MarkCommand extends Command{
    int taskIdx;

    public MarkCommand(String userTaskString, int taskIdx) {
        super(userTaskString);
        this.taskIdx = taskIdx;
    }

    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.markTaskDone(taskIdx, true);
    }
}


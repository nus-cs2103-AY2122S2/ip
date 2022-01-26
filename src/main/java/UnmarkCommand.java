public class UnmarkCommand extends Command{
    int taskIdx;

    public UnmarkCommand(String userTaskString, int taskIdx) {
        super(userTaskString);
        this.taskIdx = taskIdx;
    }

    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.markTaskNotDone(taskIdx, true);
    }
}


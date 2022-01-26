public class ByeCommand extends Command{
    int taskIdx;
    FileManager fileManager;

    public ByeCommand(String userTaskString) {
        super(userTaskString);
        this.fileManager = fileManager;
    }

    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        fileManager.saveTasks();
    }

}



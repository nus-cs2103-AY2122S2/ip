public abstract class Command {
    String userTaskString;

    public Command(String userTaskString) {
        this.userTaskString = userTaskString;
    }

    public String getUserTaskString(){
        return this.userTaskString;
    }

    public abstract void executeTask(TaskList taskList, FileManager fileManager) throws DukeException;

}

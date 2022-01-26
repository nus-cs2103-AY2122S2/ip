public class InvalidCommand extends Command{
    Ui ui;

    public InvalidCommand(String userTaskString) {
        super(userTaskString);
        this.ui = new Ui();
    }

    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        ui.throwDukeException("Invalid Input! Please either add in a Todo, Deadline or Event!");
    }

}



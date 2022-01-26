public class ListCommand extends Command {
    public ListCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.printResponse(("Here are the tasks in your list: \n" + taskList.getTaskListStr()));
    }
}

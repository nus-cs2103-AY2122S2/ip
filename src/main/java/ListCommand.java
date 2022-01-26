public class ListCommand extends Command{

    public ListCommand() {
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(ui.MSG_EMPTYTASK);
        } else {
            ui.print(ui.taskListMsg(taskList));
        }
    }

}

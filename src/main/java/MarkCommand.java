public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        if ((index - 1) > taskList.getSize() || (index - 1) < 0) {
            throw new FunBoxExceptions("Wrong index!");
        } else {
            taskList.setTaskDone(this.index - 1);
        }
    }
}

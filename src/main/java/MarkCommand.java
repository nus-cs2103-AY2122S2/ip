public class MarkCommand extends Command {
    protected static final int KEYWORD = 1;
    protected int index;

    MarkCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        taskList.markAsDone(this.index);
        storage.needUpdate();
        ui.markAsDoneRes(taskList.get(index).toString());
    };
}

public class MarkCommand extends Command {
    public enum Mark {
        MARK,
        UNMARK;
    }
    private int index;
    private Mark mark;
    public MarkCommand(int index, Mark mark, String[] commandArray) {
        super(commandArray);
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        if (mark.equals(Mark.MARK)) {
            taskList.markTask(index);
            storage.save(taskList);
        } else if (mark.equals(Mark.UNMARK)) {
            taskList.unmarkTask(index);
            storage.save(taskList);
        }
    }
}

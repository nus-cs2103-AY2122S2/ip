public class ModifyCommand extends Command {
    public static final String DONE_FORMAT = "This task is finally done:\n  %s";
    public static final String UNDONE_FORMAT = "This task is now incomplete - unacceptable:\n  %s";
    protected ModifyType modifyAction;
    private int taskIndex;

    public ModifyCommand (int taskIndex, ModifyType modifyAction) {
        this.taskIndex = taskIndex;
        this.modifyAction = modifyAction;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task getTask = storage.getTask(this.taskIndex);
            switch (this.modifyAction) {
                case MARK:
                    getTask.markDone();
                    ui.displayUserInput(String.format(DONE_FORMAT, getTask.toString()));
                    break;
                case UNMARK:
                    getTask.markUndone();
                    ui.displayUserInput(String.format(UNDONE_FORMAT, getTask.toString()));
                    break;
            }
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(String.format("Are you sure that [%d] is even in the 'list' command?", this.taskIndex+1));
        }



    }
}

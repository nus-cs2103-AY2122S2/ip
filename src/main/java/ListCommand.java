class ListCommand extends Command {

    ListCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }

    @Override
    boolean isExit() {
        return false;
    }
}

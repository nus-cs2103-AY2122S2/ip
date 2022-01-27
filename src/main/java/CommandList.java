class CommandList extends Command {
    TaskList taskList;

    public CommandList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        Ui.wrapPrint(taskList.toString());
    }
}

public class TodoCommand extends Command {
    private final String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = taskList.addTodo(taskName);
        ui.sayTaskAddingLines(taskNumber);
    }
}


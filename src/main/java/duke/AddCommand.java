package duke;

/**
 * Command to add task to list
 */
class AddCommand extends Command {
    Task task;

    AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        ui.showAddTask(task);
    }

    @Override
    boolean isExit() {
        return false;
    }
}

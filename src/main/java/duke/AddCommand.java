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
    String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        return ui.addTaskString(task);
    }

    @Override
    boolean isExit() {
        return false;
    }
}

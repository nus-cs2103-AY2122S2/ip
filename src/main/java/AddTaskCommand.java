/**
 * Adds a task to the task list
 */

public class AddTaskCommand extends Command {

    private String input;

    public AddTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        TaskManager.taskList.add(input);
    }
}

/**
 * Adds a task to the task list
 */

public class AddTaskCommand extends Command {

    private String input;

    public AddTaskCommand(String input) {
        this.input = input;
    }

    /**
     * Formats the echo for the task that was added to the user as confirmation
     * @return Formatted echo
     */
    private String echoAddedTask() {
        String response = "added: " + input;
        String formattedString = Duke.formatAnswer(response);

        return formattedString;
    }

    @Override
    public void execute() {
        TaskManager.taskList.add(new Task(input));
        System.out.println(echoAddedTask()); // Echo confirmation
    }
}

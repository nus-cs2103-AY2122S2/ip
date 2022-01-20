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
    private String formatOutput() {
        String response = "added: " + input;
        String formattedString = Duke.indent(response, 1);
        String finalFormatted = Duke.formatLines(formattedString);

        return finalFormatted;
    }

    @Override
    public void execute() {
        TaskManager.taskList.add(new Task(input));

        // Console prints
        String output = formatOutput();
        System.out.println(output); // Echo confirmation
    }
}

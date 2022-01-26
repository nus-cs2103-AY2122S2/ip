package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class UnmarkCommand implements Command {
    public static final String KEYWORD = "unmark";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(false);
            return new CommandOutput(String.format("OK, I've marked this task as not done yet:\n  %s", task), true);
        } catch (Exception e) {
            String commandFormat = "Please enter the command as \"" + KEYWORD + " <index>\".\n" +
                    "The index of unmark must be within the size of the list.";
            return new CommandOutput("Error: Invalid index.\n" + commandFormat, false);
        }
    }
}

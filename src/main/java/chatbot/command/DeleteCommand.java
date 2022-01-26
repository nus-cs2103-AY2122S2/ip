package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class DeleteCommand implements Command {
    public static final String KEYWORD = "delete";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.remove(index - 1);
            return new CommandOutput(String.format("Noted. I've removed this task:\n  %s", task), true);
        } catch (Exception e) {
            String commandFormat = "Error: Please enter the command as \"" + KEYWORD + " <index>\".\n" +
                    "The index of delete must be within the size of the list.";
            return new CommandOutput("Invalid index.\n" + commandFormat, false);
        }
    }
}

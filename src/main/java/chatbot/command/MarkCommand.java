package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class MarkCommand implements Command {
    public static final String KEYWORD = "mark";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(true);
            return new CommandOutput(String.format("Nice! I've marked this task as done:\n  %s", taskList), true);
        } catch (Exception e) {
            String commandFormat = "Please enter the command as \"" + KEYWORD + " <index>\".\n" +
                    "The index of mark must be within the size of the list.";
            return new CommandOutput("Error: Invalid index.\n" + commandFormat, false);
        }
    }
}

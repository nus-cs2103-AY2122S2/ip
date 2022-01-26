package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;

import java.util.Arrays;

public class ToDoCommand implements Command {
    public static final String KEYWORD = "todo";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        // Parse input.
        String desc = "";
        try {
            desc = String.join(" ", Arrays.asList(input).subList(1, input.length));
        } catch (Exception ignored) {
        }

        String commandFormat = "Please enter the command as \"" + KEYWORD + " <desc>\".\n" +
                "The description cannot be empty.";
        if (desc.isBlank()) {
            return new CommandOutput("Error: Invalid description\n" + commandFormat, false);
        }

        // Add event.
        Task task = new ToDo(desc);
        taskList.add(task);
        return new CommandOutput(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, taskList.size()), true);
    }
}
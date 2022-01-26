package chatbot.command;

import chatbot.task.Task;
import chatbot.task.ToDo;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoCommand implements Command {
    public static final String KEYWORD = "todo";

    @Override
    public CommandOutput execute(String[] input, ArrayList<Task> tasks) {
        // Parse input.
        String desc = "";
        try {
            desc = String.join(" ", Arrays.asList(input).subList(1, input.length));
        } catch (Exception ignored) {
        }

        if (desc.isBlank()) {
            return new CommandOutput("☹ OOPS!!! The description of a todo cannot be empty.", false);
        }

        // Add event.
        Task task = new ToDo(desc);
        tasks.add(task);
        return new CommandOutput(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size()), true);
    }
}
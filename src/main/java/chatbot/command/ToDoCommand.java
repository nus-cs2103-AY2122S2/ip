package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;

import java.util.Arrays;

public class ToDoCommand extends Command {
    private static final String TRIGGER = "todo";
    private static final String FORMAT = "Command Format: \"" + TRIGGER + " <desc>\"";

    public ToDoCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        // Parse input.
        String desc = "";
        try {
            desc = String.join(" ", Arrays.asList(input).subList(1, input.length));
        } catch (Exception e) {
            // No need to handle exception here as the checks are done below.
        }

        if (desc.isBlank()) {
            return new CommandOutput("Error: Empty description\n" + FORMAT, "/audio/ding.wav");
        }

        // Add event.
        Task task = new ToDo(desc);
        taskList.add(task);
        return new CommandOutput(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                        taskList.size()), "/audio/ding.wav");
    }
}
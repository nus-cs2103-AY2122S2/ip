package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.task.ToDo;

import java.util.Arrays;

public class ToDoCommand extends Command {
    public static final String TRIGGER = "todo";
    public static final String FORMAT = TRIGGER + " <desc>";

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
            return new CommandOutput("Error: Empty description\n" + "Command format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }

        // Add event.
        Task task = new ToDo(desc);
        taskList.add(task);
        return new CommandOutput(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                        taskList.size()), "/audio/wav/notification.wav");
    }
}
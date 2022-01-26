package chatbot.command;

import chatbot.task.Event;
import chatbot.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class EventCommand implements Command {
    public static final String KEYWORD = "event";

    @Override
    public CommandOutput execute(String[] input, ArrayList<Task> tasks) {
        // Parse input.
        String desc = "";
        String time = "";
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/at")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            time = String.join(" ", Arrays.asList(input).subList(i + 1, input.length));
        } catch (Exception ignored) {
        }

        if (desc.isBlank()) {
            return new CommandOutput("☹ OOPS!!! The description of an event cannot be empty.", false);
        } else if (time.isBlank()) {
            return new CommandOutput("☹ OOPS!!! The time of an event cannot be empty.", false);
        }

        // Add event.
        Task task = new Event(desc, time);
        tasks.add(task);
        return new CommandOutput(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size()), true);
    }
}
package chatbot.command;

import chatbot.task.Deadline;
import chatbot.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class DeadlineCommand implements Command {
    public static final String KEYWORD = "deadline";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        // Parse input.
        String desc = "";
        String time = "";
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/by")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            time = String.join(" ", Arrays.asList(input).subList(i + 1, input.length));
        } catch (Exception ignored) {
        }

        if (desc.isBlank()) {
            return "☹ OOPS!!! The description of an deadline cannot be empty.";
        } else if (time.isBlank()) {
            return "☹ OOPS!!! The time of a deadline cannot be empty.";
        }

        // Add event.
        Task task = new Deadline(desc, time);
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
package chatbot.command;

import chatbot.task.Deadline;
import chatbot.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DeadlineCommand implements Command {
    public static final String KEYWORD = "deadline";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        String desc = "";
        LocalDate date = null;
        LocalTime time = null;
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/at")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            date = LocalDate.parse(input[i+1]);
            time = LocalTime.parse(input[i+2]);
        } catch (Exception ignored) {
        }

        String commandFormat = "Please enter the command as \"deadline <desc> /by <date> <time>\"\n" +
                "Dates must be in YYYY-MM-DD format.\n" +
                "Time must be in the HH:MM or HH:MM:SS format.";
        if (desc.isBlank()) {
            return "Invalid description.\n" + commandFormat;
        }
        if (date == null) {
            return "Invalid date.\n" + commandFormat;
        }
        if (time == null) {
            return "Invalid time.\n" + commandFormat;
        }

        // Add event.
        Task task = new Deadline(desc, date, time);
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
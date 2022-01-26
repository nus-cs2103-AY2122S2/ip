package chatbot.command;

import chatbot.task.Event;
import chatbot.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class EventCommand implements Command {
    public static final String KEYWORD = "event";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        String desc = "";
        LocalDate startDate = null;
        LocalTime startTime = null;
        LocalDate endDate = null;
        LocalTime endTime = null;
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/at")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            startDate = LocalDate.parse(input[i+1]);
            startTime = LocalTime.parse(input[i+2]);
            endDate = LocalDate.parse(input[i+3]);
            endTime = LocalTime.parse(input[i+4]);
        } catch (Exception ignored) {
        }

        String commandFormat = "Please enter the command as \"event <desc> /at <start_date> <start_time> <end_date> <end_time>\"\n" +
                "Dates must be in YYYY-MM-DD format.\n" +
                "Time must be in the HH:MM or HH:MM:SS format.";
        if (desc.isBlank()) {
            return "Invalid description.\n" + commandFormat;
        }
        if (startDate == null) {
            return "Invalid start date.\n" + commandFormat;
        }
        if (startTime == null) {
            return "Invalid start time.\n" + commandFormat;
        }
        if (endDate == null) {
            return "Invalid end date.\n" + commandFormat;
        }
        if (endTime == null) {
            return "Invalid end time.\n" + commandFormat;
        }
        if (startDate.isAfter(endDate)) {
            return "Start date cannot be after end date.\n" + commandFormat;
        }
        if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            return "Start time cannot be after end time.\n" + commandFormat;
        }

        // Add event.
        Task task = new Event(desc, startDate, startTime, endDate, endTime);
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size());
    }
}
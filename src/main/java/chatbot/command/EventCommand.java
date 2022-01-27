package chatbot.command;

import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EventCommand implements Command {
    public static final String KEYWORD = "event";
    public static final String FORMAT =
            "Command format: \"" + KEYWORD + " <desc> /at <start_date> <start_time> <end_date> <end_time>\"\n"
                    + "Date format: YYYY-MM-DD\n" + "Time format: HH:MM or HH:MM:SS";

    @Override public CommandOutput execute(String[] input, TaskList taskList) {
        // Parse input.
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
            startDate = LocalDate.parse(input[i + 1]);
            startTime = LocalTime.parse(input[i + 2]);
            endDate = LocalDate.parse(input[i + 3]);
            endTime = LocalTime.parse(input[i + 4]);
        } catch (Exception ignored) {
        }


        if (desc.isBlank()) {
            return new CommandOutput("Error: Invalid description\n" + FORMAT, "audio/ding.wav");
        } else if (startDate == null) {
            return new CommandOutput("Error: Invalid start date\n" + FORMAT, "audio/ding.wav");
        } else if (startTime == null) {
            return new CommandOutput("Error: Invalid start time\n" + FORMAT, "audio/ding.wav");
        } else if (endDate == null) {
            return new CommandOutput("Error: Invalid end date\n" + FORMAT, "audio/ding.wav");
        } else if (endTime == null) {
            return new CommandOutput("Error: Invalid end time\n" + FORMAT, "audio/ding.wav");
        } else if (startDate.isAfter(endDate)) {
            return new CommandOutput("Error: Start date cannot be after end date\n" + FORMAT, "audio/ding.wav");
        } else if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            return new CommandOutput("Error: Start time cannot be after end time\n" + FORMAT, "audio/ding.wav");
        }

        // Add event.
        Task task = new Event(desc, startDate, startTime, endDate, endTime);
        taskList.add(task);
        return new CommandOutput(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d taskList in the list.", task,
                        taskList.size()), "audio/ding.wav");
    }
}

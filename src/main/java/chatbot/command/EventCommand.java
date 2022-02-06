package chatbot.command;

import chatbot.sfx.Sfx;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EventCommand extends Command {
    public static final String TRIGGER = "event";
    public static final String FORMAT =
            TRIGGER + " <desc> /at <start_date> <start_time> <end_date> <end_time>\n" + "Date format: YYYY-MM-DD\n"
                    + "Time format: HH:MM or HH:MM:SS";

    public EventCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
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
        } catch (Exception e) {
            // No need to handle exception here as the checks are done below.
        }

        if (desc.isBlank()) {
            return new CommandOutput("Error: Blank description\nCommand format: " + FORMAT, Sfx.SFX_ERROR_BLANK_DESC);
        } else if (startDate == null) {
            return new CommandOutput("Error: Invalid start date\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_START_DATE);
        } else if (startTime == null) {
            return new CommandOutput("Error: Invalid start time\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_START_TIME);
        } else if (endDate == null) {
            return new CommandOutput("Error: Invalid end date\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_END_DATE);
        } else if (endTime == null) {
            return new CommandOutput("Error: Invalid end time\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_END_TIME);
        } else if (startDate.isAfter(endDate)) {
            return new CommandOutput("Error: Start date cannot be after end date\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_DATE_ORDER);
        } else if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            return new CommandOutput("Error: Start time cannot be after end time\nCommand format: " + FORMAT,
                    Sfx.SFX_ERROR_INVALID_TIME_ORDER);
        }

        // Add event.
        Task task = new Event(desc, startDate, startTime, endDate, endTime);
        taskList.add(task);
        return new CommandOutput(
                String.format("Alrighty! I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                        taskList.size()), Sfx.SFX_COMMAND_ADD);
    }
}

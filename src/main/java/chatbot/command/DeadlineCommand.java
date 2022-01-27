package chatbot.command;

import chatbot.task.Deadline;
import chatbot.task.Task;
import chatbot.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class DeadlineCommand implements Command {
    public static final String KEYWORD = "deadline";
    public static final String FORMAT =
            "Command Format: \"" + KEYWORD + " <desc> /by <date> <time>\"\n" + "Date format: YYYY-MM-DD\n"
                    + "Time format: HH:MM or HH:MM:SS";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        // Parse input.
        String desc = "";
        LocalDate date = null;
        LocalTime time = null;
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/at")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            date = LocalDate.parse(input[i + 1]);
            time = LocalTime.parse(input[i + 2]);
        } catch (Exception ignored) {
        }

        if (desc.isBlank()) {
            return new CommandOutput("Invalid description\n" + FORMAT, "/audio/ding.wav");
        } else if (date == null) {
            return new CommandOutput("Invalid date\n" + FORMAT, "/audio/ding.wav");
        } else if (time == null) {
            return new CommandOutput("Invalid time\n" + FORMAT, "/audio/ding.wav");
        }

        // Add event.
        Task task = new Deadline(desc, date, time);
        taskList.add(task);
        return new CommandOutput(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d taskList in the list.", task,
                        taskList.size()), "/audio/ding.wav");
    }
}

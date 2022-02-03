package chatbot.command;

import chatbot.task.Deadline;
import chatbot.task.Task;
import chatbot.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class DeadlineCommand extends Command {
    public static final String TRIGGER = "deadline";
    public static final String FORMAT =
            TRIGGER + " <desc> /by <date> <time>\n" + "Date format: YYYY-MM-DD\n"
                    + "Time format: HH:MM or HH:MM:SS";

    public DeadlineCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        // Parse input.
        String desc = "";
        LocalDate date = null;
        LocalTime time = null;
        try {
            int i = 1;
            while (i < input.length && !input[i].equals("/by")) {
                ++i;
            }
            desc = String.join(" ", Arrays.asList(input).subList(1, i));
            date = LocalDate.parse(input[i + 1]);
            time = LocalTime.parse(input[i + 2]);
        } catch (Exception e) {
            // No need to handle exception here as the checks are done below.
        }

        if (desc.isBlank()) {
            return new CommandOutput("Invalid description\n" + "Command format: " + FORMAT, "/audio/wav/notification.wav");
        } else if (date == null) {
            return new CommandOutput("Invalid date\n" + "Command format: " + FORMAT, "/audio/wav/notification.wav");
        } else if (time == null) {
            return new CommandOutput("Invalid time\n" + "Command format: " + FORMAT, "/audio/wav/notification.wav");
        }

        // Add event.
        Task task = new Deadline(desc, date, time);
        taskList.add(task);
        return new CommandOutput(
                String.format("Got it. I've added this task:\n  %s\nNow you have %d taskList in the list.", task,
                        taskList.size()), "/audio/wav/notification.wav");
    }
}
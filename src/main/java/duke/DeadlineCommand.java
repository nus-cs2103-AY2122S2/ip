package duke;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    /**
     * Executes the task based on the responsibility of the deadline command.
     *
     * @param taskList current list of task in the hard disk.
     * @param userInputTask user input command to the input.
     * @param userInputs separated words from user input.
     * @param storage the hard disk.
     * @return String description of the execution result.
     * @throws IOException
     */
    @Override
    public String execute(TaskList taskList, String userInputTask,
                          String[] userInputs, Storage storage) throws IOException {
        // handle error from empty task description
        try {
            Parser.parserDeadlineValidator(userInputTask);
        } catch (DukeException e) {
            if (e.getMessage().equals("The description of a deadline cannot be empty.")) {
                return "The description of a deadline cannot be empty.";
            } else if (e.getMessage().equals("Deadline tasks require a by day.")) {
                return "Deadline tasks require a by day.";
            } else if (e.getMessage().equals("Deadline tasks can only have one by day.")) {
                return "Deadline tasks can only have one by day.";
            }
        }

        // splitting deadline into description and by
        String[] deadlineTaskArr = userInputTask.split(" /by ");
        String[] byAndTime = deadlineTaskArr[1].split(" ");
        String by = byAndTime[0];

        String time = byAndTime[1];
        assert !time.isEmpty() : "Deadline time is required";

        try {
            String deadlineTaskTime = byAndTime[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Deadline time is required";
        }

        String deadlineTime = byAndTime[1];

        // handle error when time is not in the hh:mm 24hr clock format
        try {
            LocalTime.parse(deadlineTime);
        } catch (DateTimeParseException e) {
            return "Time must be in the hh:mm 24hr format";
        }

        // handle error when there is invalid deadline date format
        try {
            Parser.deadlineDateFormatValidator(by);
        } catch (DukeException e) {
            return "OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
        }

        return Parser.parserDeadline(taskList, userInputTask, storage);
    }
}

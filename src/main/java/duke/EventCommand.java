package duke;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    /**
     * Executes the task based on the responsibility of the event command.
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
        try {
            Parser.parserEventValidator(userInputTask);
        } catch (DukeException e) {
            if (e.getMessage().equals("The description of an event cannot be empty.")) {
                return "The description of an event cannot be empty.";
            } else if (e.getMessage().equals("Event tasks require an at date and time.")) {
                return "Event tasks require an at date and time.";
            } else {
                return "Event tasks can only have one at date and time.";
            }
        }

        // splitting event into description and dateTime
        String[] eventTaskArr = userInputTask.split(" /at ");
        String[] eventDateAndTime = eventTaskArr[1].split(" ");
        String eventDate = eventDateAndTime[0];

        try {
            String eventTime = eventDateAndTime[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Event time is required";
        }

        String eventTime = eventDateAndTime[1];

        // handle error when time is not in the hh:mm 24hr clock format
        try {
            LocalTime.parse(eventTime);
        } catch (DateTimeParseException e) {
            return "Time must be in the hh:mm 24hr format";
        }

        // handle error when there is invalid deadline date format
        try {
            Parser.eventDateFormatValidator(eventDate);
        } catch (DukeException e) {
            return "OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
        }
        return Parser.parserEvent(taskList, userInputTask, storage);
    }
}

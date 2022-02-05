import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.io.IOException;

public class Parser {

    /**
     * Parses input from InputHandler and returns a new Task to be added to TaskList
     * @param type CommandType of input, including (TODO, DEADLINE, EVENT)
     * @param splitInput SplitInput from InputHandler is user's input, split by empty spaces for processing
     * @return Task object of new task to be added to TaskList
     * @throws DukeException If format is wrong
     */
    public Task parse(InputHandler.CommandType type, String[] splitInput) throws DukeException {
        switch (type) {
        case TODO:
            String[] nameArray = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String todoName = String.join(" ", nameArray);
            return new Todo(todoName);

        case EVENT:
            String[] stringArrayExcludingEvent = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String stringExcludingEvent = String.join(" ", stringArrayExcludingEvent);
            String[] eventNameAndTimeArray = stringExcludingEvent.split("/at ");
            String eventName = eventNameAndTimeArray[0];
            String eventTime = eventNameAndTimeArray[1];
            String[] eventTimeArray = eventTime.split(" ");
            try {
                Event newEvent = (eventTimeArray.length > 1) ? new Event(eventName, eventTimeArray[0], eventTimeArray[1]) : new Event(eventName, eventTimeArray[0]);
                return newEvent;
            } catch (DateTimeParseException e) {
                throw new DukeException(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");

            }

        case DEADLINE:
            String[] stringArrayExcludingDeadline = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String stringExcludingDeadline = String.join(" ", stringArrayExcludingDeadline);
            String[] deadlineNameAndTimeArray = stringExcludingDeadline.split("/by ");
            String deadlineName = deadlineNameAndTimeArray[0];
            String deadlineTime = deadlineNameAndTimeArray[1];
            String[] deadlineTimeArray = deadlineTime.split(" ");
            try {
                Deadline newDeadline = (deadlineTimeArray.length > 1) ? new Deadline(deadlineName, deadlineTimeArray[0], deadlineTimeArray[1]) : new Deadline(deadlineName, deadlineTimeArray[0]);
                return newDeadline;
            } catch (DateTimeParseException e) {
                throw new DukeException(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");

            }

        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: todo [task], event [task] /at [time],"
                    + " deadline [task] /by [time], mark [index], unmark [index], delete [index], bye");
        }
    }

    /**
     * Parses input from InputHandler and writes/deletes/prints from storage accordingly
     * @param type CommandType of input, including (LIST, MARK, UNMARK, DELETE)
     * @param storage Storage object in InputHandler to write/delete/get data from
     * @param splitInput SplitInput from InputHandler is user's input, split by empty spaces for processing
     * @throws DukeException Handles unrecognised commands
     * @throws IOException Handles IO Errors
     */
    public void parse(InputHandler.CommandType type, Storage storage, String[] splitInput) throws DukeException, IOException {
        switch (type) {
        case LIST:
            System.out.println("Here are the tasks in your list:");
            System.out.println(storage.list());
            break;

        case MARK:
            System.out.println("Nice! I've marked this task as done:\n");
            int taskToBeMarkedIndex = Integer.parseInt(splitInput[1]) - 1;
            Task taskToBeMarked = storage.get(taskToBeMarkedIndex);
            taskToBeMarked.setMarkedTask();
            break;

        case UNMARK:
            System.out.println("OK, I've marked this task as not done yet:\n");
            int taskToBeUnmarkedIndex = Integer.parseInt(splitInput[1]) - 1;
            Task taskToBeUnmarked = storage.get(taskToBeUnmarkedIndex);
            taskToBeUnmarked.setUnmarkedTask();
            break;

        case DELETE:
            int idx = Integer.parseInt(splitInput[1]) - 1;
            Task taskToBeDeleted = storage.get(idx);
            storage.deleteData(idx);
            System.out.println("Noted. I've removed this task:\n" + taskToBeDeleted + "\nNow you have " + storage.taskListSize() + " tasks in the list");
            break;

        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: todo [task], event [task] /at [time],"
                    + " deadline [task] /by [time], mark [index], unmark [index], delete [index], bye");
        }
    }

}
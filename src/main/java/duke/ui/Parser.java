package duke.ui;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;

import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.ArrayList;

import java.io.IOException;

public class Parser {

    String defaultErrorMessage = ":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: todo [task]," +
            " event [task] /at [time], deadline [task] /by [time], mark [index], unmark [index], delete [index], bye";

    /**
     * Parses input from InputHandler and returns a new Task to be added to TaskList. Handles event, deadline, todo commands
     * @param type CommandType of input, including (TODO, DEADLINE, EVENT)
     * @param splitInput SplitInput from InputHandler is user's input, split by empty spaces for processing
     * @return Task object of new task to be added to TaskList
     * @throws DukeException If format is wrong
     */
    public Task parse(InputHandler.CommandType type, String[] splitInput) throws DukeException {
        switch (type) {
        case TODO:
            //Removes the todo command word: i.e. todo task -> task
            String[] nameArray = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String todoName = String.join(" ", nameArray);
            return new Todo(todoName);

        case EVENT:
            //Removes the event command word and separates into date and time (optional)
            String[] stringArrayExcludingEvent = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String stringExcludingEvent = String.join(" ", stringArrayExcludingEvent);
            String[] eventNameAndTimeArray = stringExcludingEvent.split("/at ");
            assert eventNameAndTimeArray.length > 1 : "Make sure to include the /at command!";
            String eventNameWithExtraSpace = eventNameAndTimeArray[0];
            String eventName = eventNameWithExtraSpace.substring(0, eventNameWithExtraSpace.length() -1);
            String eventTime = eventNameAndTimeArray[1];
            String[] eventTimeArray = eventTime.split(" ");

            try {
                Event newEvent = (eventTimeArray.length > 1) ? new Event(eventName, eventTimeArray[0], eventTimeArray[1]) : new Event(eventName, eventTimeArray[0]);
                return newEvent;
            } catch (DateTimeParseException e) {
                //Datetime unable to be parsed
                throw new DukeException(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");

            }

        case DEADLINE:
            //Removes the deadline command word and separates into date and time (optional)
            String[] stringArrayExcludingDeadline = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String stringExcludingDeadline = String.join(" ", stringArrayExcludingDeadline);
            String[] deadlineNameAndTimeArray = stringExcludingDeadline.split("/by ");
            assert deadlineNameAndTimeArray.length > 1 : "Make sure to include the /by command!";
            String deadlineNameWithSpace = deadlineNameAndTimeArray[0];
            String deadlineName = deadlineNameWithSpace.substring(0, deadlineNameWithSpace.length() - 1);
            String deadlineTime = deadlineNameAndTimeArray[1];
            String[] deadlineTimeArray = deadlineTime.split(" ");

            try {
                Deadline newDeadline = (deadlineTimeArray.length > 1) ? new Deadline(deadlineName, deadlineTimeArray[0], deadlineTimeArray[1]) : new Deadline(deadlineName, deadlineTimeArray[0]);
                return newDeadline;
            } catch (DateTimeParseException e) {
                //Datetime unable to be parsed
                throw new DukeException(":( OOPS!!! The correct format for date and time is yyyy-mm-dd and hh:mm");

            }

        default:
            throw new DukeException(defaultErrorMessage);
        }
    }

    /**
     * Parses input from InputHandler and writes/deletes/prints from storage accordingly. Handles list, mark, unmark, delete commands
     * @param type CommandType of input, including (LIST, MARK, UNMARK, DELETE)
     * @param storage Storage object in InputHandler to write/delete/get data from
     * @param splitInput SplitInput from InputHandler is user's input, split by empty spaces for processing
     * @throws DukeException Handles unrecognised commands
     * @throws IOException Handles IO Errors
     */
    public String parse(InputHandler.CommandType type, Storage storage, String[] splitInput) throws DukeException, IOException {

        String wrongMarkFormatErrorMessage = "Make sure mark is in the format: mark [index]!";
        String wrongUnmarkFormatErrorMessage = "Make sure unmark is in the format: unmark [index]!";
        String wrongDeleteFormatErrorMessage = "Make sure delete is in the format delete [index]!";
        String cannotFindTaskMessage = "Uh oh! No task matches the description you've given :(";

        String listCommandStringIntro = "Here are the tasks in your list:\n";
        String markedMessage = "Nice! I've marked this task as done:\n";
        String unmarkedMessage = "OK, I've marked this task as not done yet:\n";

        switch (type) {
        case LIST:
            return listCommandStringIntro + storage.list();

        case MARK:
            //Marks task by index
            try {
                int taskToBeMarkedIndex = Integer.parseInt(splitInput[1]) - 1;
                assert taskToBeMarkedIndex < storage.taskListSize() : "Index too big!";
                Task taskToBeMarked = storage.get(taskToBeMarkedIndex);
                taskToBeMarked.setMarkedTask();
                storage.rewriteData();
                return markedMessage + taskToBeMarked;
            } catch (NumberFormatException e) {
                //Addresses the error of a non-integer being passed in
                throw new DukeException(wrongMarkFormatErrorMessage);
            }

        case UNMARK:
            //Unmarks task by index
            try {
                int taskToBeUnmarkedIndex = Integer.parseInt(splitInput[1]) - 1;
                assert taskToBeUnmarkedIndex < storage.taskListSize() : "Index too big!";
                Task taskToBeUnmarked = storage.get(taskToBeUnmarkedIndex);
                taskToBeUnmarked.setUnmarkedTask();
                storage.rewriteData();
                return unmarkedMessage + taskToBeUnmarked;
            } catch (NumberFormatException e) {
                //Addresses the issue of a non-integer being passed in
                throw new DukeException(wrongUnmarkFormatErrorMessage);
            }

        case DELETE:
            //Delete task by index
            try {
                int idxOfTaskToBeDeleted = Integer.parseInt(splitInput[1]) - 1;
                System.out.println(idxOfTaskToBeDeleted);
                assert idxOfTaskToBeDeleted < storage.taskListSize() : "Index too big!";
                Task taskToBeDeleted = storage.get(idxOfTaskToBeDeleted);
                storage.deleteData(idxOfTaskToBeDeleted);
                return "Noted. I've removed this task:\n" + taskToBeDeleted + "\nNow you have " + storage.taskListSize() + " tasks in the list";
            } catch (NumberFormatException e) {
                //Addresses the issue of a non-integer being passed in
                throw new DukeException(wrongDeleteFormatErrorMessage);
            }

        case FIND:
            //Removes the find command and iterates through the TaskList to find a task name that contains the keyword
            String[] stringArrayExcludingFind = Arrays.copyOfRange(splitInput, 1, splitInput.length);
            String nameOfKeyWord = String.join(" ", stringArrayExcludingFind);
            ArrayList<Task> arrayOfTasks = storage.accessTaskList().list;
            ArrayList<Integer> indexOfFoundObjects = new ArrayList<>();

            for (int i = 0; i < arrayOfTasks.size(); i++) {
                Task currentTask = arrayOfTasks.get(i);
                if (currentTask.name.contains(nameOfKeyWord)) {
                    indexOfFoundObjects.add(i);
                }
            }
            String outputString = "";
            if (!indexOfFoundObjects.isEmpty()) {
                //Task found and print
                for (int j = 0; j < indexOfFoundObjects.size(); j++) {
                    outputString += (j + 1) + "." + storage.get(indexOfFoundObjects.get(j));
                }
                return outputString;
            } else {
                //Unable to find
                throw new DukeException(cannotFindTaskMessage);
            }

        default:
            throw new DukeException(defaultErrorMessage);
        }
    }

}
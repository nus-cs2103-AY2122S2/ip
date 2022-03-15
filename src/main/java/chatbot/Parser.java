package chatbot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
/**
* Parser class for the bot, to handle incoming inputs from the user.
*/
public class Parser {
    private static final String UNRECOGNIZED_COMMAND = "Sorry Sir, I do not understand that command.";
    private static final String NOT_VALID_NUMBER = "Please enter a valid number Sir.";
    private static final String COMMAND_REQUIRES_NUMBER = "Sorry Sir, this command requires a number.";
    private static final String MISSING_TASK_INFO = "Sorry Sir, the <%s> command cannot be empty.";
    private static final String BYE_MESSAGE = "Farewell Sir. May you have a wonderful day.";
    /**
     * Returns the String of the reply to the user's input inputText
     *
     * @param    inputText    input of the user
     * @param    taskList     TaskList of the user
     * @param    storage      Storage of the user denoting the location of the save file
     * @return                the String containing the reply
     * @see      TaskList
     * @see      Storage
     */
    public static String parseTextGui(
            String inputText, TaskList taskList, Storage storage) {
        inputText = inputText.trim();
        String[] inputStringArray = inputText.split(" ");
        try {
            if (inputText.equals("bye")) { // bye command
                return BYE_MESSAGE;
            } else if (inputText.equals("list") || inputText.equals("l")) { // list command
                String message = taskList.getTaskListMessage();
                return message;

            } else if (inputStringArray[0].equals("mark")
                    || inputStringArray[0].equals("unmark")
                    || inputStringArray[0].equals("m")
                    || inputStringArray[0].equals("um")) {
                // mark / unmark command
                try {
                    String inputNumberString = inputStringArray[1];
                    int taskIndex = convertToTaskIndex(inputNumberString);
                    String message = taskList.markTask(taskIndex, inputStringArray[0]);
                    storage.saveData(taskList);
                    return message;
                } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
                    throw new DukeException(COMMAND_REQUIRES_NUMBER);
                }

            } else if (inputStringArray[0].equals("todo")
                    || inputStringArray[0].equals("deadline")
                    || inputStringArray[0].equals("event")
                    || inputStringArray[0].equals("t")
                    || inputStringArray[0].equals("d")
                    || inputStringArray[0].equals("e")) {
                Task newTask = createNewTaskFromInput(inputStringArray[0], inputText);
                String message = taskList.insertNewTask(newTask);
                storage.saveData(taskList);
                return message;

            } else if (inputStringArray[0].equals("delete")
                    || inputStringArray[0].equals("del")) {
                try {
                    String inputNumberString = inputStringArray[1];
                    int deleteTaskIndex = convertToTaskIndex(inputNumberString);
                    String message = taskList.deleteTask(deleteTaskIndex);
                    storage.saveData(taskList);
                    return message;
                } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
                    throw new DukeException(COMMAND_REQUIRES_NUMBER);
                }
            } else if (inputStringArray[0].equals("find")
                    || inputStringArray[0].equals("f")) {
                try {
                    String message = taskList.findTaskName(inputStringArray[1]);
                    return message;
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new DukeException(String.format(MISSING_TASK_INFO, "find"));
                }
            } else { // other text input
                throw new DukeException(UNRECOGNIZED_COMMAND);
            }
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }
    /**
    * Returns the Task created from the user inputText.
    * The type parameter determines which type of Task is created.
    * Use "todo", "deadline", "event" to create the respective Task objects.
    *
    * @param    type          the type of Task to be created
    * @param    inputText     input of the user
    * @return                 the created Task
    * @throws   DukeException throws a DukeException
    * @see      Task
    */
    public static Task createNewTaskFromInput(String type, String inputText) throws DukeException {
        Task newTask;
        String taskName = "";
        String taskDateTime;
        String missingDateTime = "Sorry Sir, the description of <" + type + "> is missing a date/time.";
        String missingTaskInfo = "Sorry Sir, the <" + type + "> command cannot be empty.";
        String wrongDateTimeFormat = "Sorry Sir, the date/time needs to be in the format: YYYY-MM-DD HH:MM";
        LocalDate taskDate;
        LocalTime taskTime;
        if (type.equals("deadline") || type.equals("d") || type.equals("event") || type.equals("e")) { 
            String[] inputStringArray = inputText.split(" /by ");
            try {
                String[] taskNameArray = inputStringArray[0].split(" ");
                taskName += taskNameArray[1];
                for (int i = 2; i < taskNameArray.length; i++) {
                    taskName += " " + taskNameArray[i];
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            try {
                taskDateTime = inputStringArray[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                throw new DukeException(missingDateTime);
            }
            try {
                String[] taskDateTimeArray = taskDateTime.split(" ");
                taskDate = LocalDate.parse(taskDateTimeArray[0]);
                taskTime = null;
                if (taskDateTimeArray.length > 1) {
                    taskTime = LocalTime.parse(taskDateTimeArray[1]);
                }
            } catch (DateTimeParseException exception) {
                throw new DukeException(wrongDateTimeFormat);
            }
            if (type.equals("deadline") || type.equals("d")) { // deadline
                newTask = new Deadline(taskName, taskDate, taskTime);
            } else { // event
                newTask = new Event(taskName, taskDate, taskTime);
            }

        } else { // is a to-do task
            try {
                String[] taskNameArray = inputText.split(" ");
                taskName += taskNameArray[1];
                for (int i = 2; i < taskNameArray.length; i++) {
                    taskName += " " + taskNameArray[i];
                }
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            newTask = new Todo(taskName);
        }
        return newTask;
    }

    /**
    * Returns the integer index of the Task in the ArrayList from user input String,
    * after processing the inputText String from the user.
    * Taking in Strings that are not valid integers throws a DukeException.
    *
    * @param    inputNumberString   input of the user
    * @return                       the index of the Task
    * @throws   DukeException       throws a DukeException
    */
    public static int convertToTaskIndex(String inputNumberString) throws DukeException {
        // check if there is an integer after the text command input
        try {
            int inputNumberInteger = Integer.parseInt(inputNumberString);
            return inputNumberInteger - 1;

        } catch (NumberFormatException exception) { // not a number
            throw new DukeException(NOT_VALID_NUMBER);
        }
    }
}

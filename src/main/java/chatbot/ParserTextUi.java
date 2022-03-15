package chatbot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
/**
* Parser class for the bot for command line text ui, to handle incoming inputs from the user.
*/
public class ParserTextUi {
    private static final String UNRECOGNIZED_COMMAND = "Sorry Sir, I do not understand that command.";
    private static final String NOT_VALID_NUMBER = "Please enter a valid number Sir.";
    private static final String COMMAND_REQUIRES_NUMBER = "Sorry Sir, this command requires a number.";
    private static final String BYE_MESSAGE = "Farewell Sir. May you have a wonderful day.";
    private static final String MISSING_DATE_TIME = "Sorry Sir, the description of <%s> is missing a date/time.";
    private static final String MISSING_TASK_INFO = "Sorry Sir, the <%s> command cannot be empty.";
    private static final String WRONG_DATE_TIME_FORMAT = "Sorry Sir, the date/time needs to be in the format: YYYY-MM-DD HH:MM";
    /**
    * Displays the farewell message to the user.
    */
    public static void displayFarewell() {
        Ui.displayMessage(BYE_MESSAGE);
    }
    /**
    * Returns the boolean of whether the bot should expect more inputs from the user,
    * after processing the inputText String from the user.
    *
    * @param    inputText    input of the user
    * @param    taskList     TaskList of the user
    * @param    storage      Storage of the user denoting the location of the save file
    * @return                the boolean to denote whether there are more future inputs
    * @see      TaskList
    * @see      Storage
    */
    public static boolean parseTextAndWillContinue(
            String inputText, TaskList taskList, Storage storage) { // returns true if bot should continue parsing text
        inputText = inputText.trim();
        String[] inputStringArray = inputText.split(" ");
        try {
            if (inputText.equals("bye")) { // bye command
                displayFarewell();
                return false;
            } else if (inputText.equals("list")) { // list command
                String message = taskList.getTaskListMessage();
                Ui.displayMessage(message);
                return true;

            } else if (inputStringArray[0].equals("mark") || inputStringArray[0].equals("unmark")) {
                // mark / unmark command
                try {
                    String inputNumberString = inputStringArray[1];
                    int taskIndex = convertToTaskIndex(inputNumberString);
                    String message = taskList.markTask(taskIndex, inputStringArray[0]);
                    storage.saveData(taskList);
                    Ui.displayMessage(message);
                    return true;
                } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
                    throw new DukeException(COMMAND_REQUIRES_NUMBER);
                }

            } else if (inputStringArray[0].equals("todo")
                || inputStringArray[0].equals("deadline")
                || inputStringArray[0].equals("event")) {
                Task newTask = createNewTaskFromInput(inputStringArray[0], inputText);
                String message = taskList.insertNewTask(newTask);
                storage.saveData(taskList);
                Ui.displayMessage(message);
                return true;

            } else if (inputStringArray[0].equals("delete")) {
                try {
                    String inputNumberString = inputStringArray[1];
                    int deleteTaskIndex = convertToTaskIndex(inputNumberString);
                    String message = taskList.deleteTask(deleteTaskIndex);
                    storage.saveData(taskList);
                    Ui.displayMessage(message);
                    return true;
                } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
                    throw new DukeException(COMMAND_REQUIRES_NUMBER);
                }
            } else if (inputStringArray[0].equals("find")) {
                try {
                    String message = taskList.findTaskName(inputStringArray[1]);
                    Ui.displayMessage(message);
                    return true;
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new DukeException(String.format(MISSING_TASK_INFO, "find"));
                }
            } else { // other text input
                throw new DukeException(UNRECOGNIZED_COMMAND);
            }
        } catch (DukeException dukeException) {
            Ui.displayMessage(dukeException.toString());
            return true;
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
        if (type.equals("deadline")) { // is a deadline task
            newTask = createDeadlineFromInput(inputText);
        } else if (type.equals("event")) { // is an event task
            newTask = createEventFromInput(inputText);
        } else { // is a to-do task
            newTask = createTodoFromInput(inputText);
        }
        return newTask;
    }
    /**
    * Returns the Deadline created from the user inputText.
    *
    * @param    inputText     input of the user
    * @return                 the created Deadline
    * @throws   DukeException throws a DukeException
    * @see      Task
    */
    private static Task createDeadlineFromInput(String inputText) throws DukeException {
        Task newTask;
        String taskName;
        String taskDateTime;
        String[] inputStringArray = inputText.split(" /by ");
        try {
            taskName = inputStringArray[0].substring(9);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(MISSING_TASK_INFO, "deadline"));
        }
        try {
            taskDateTime = inputStringArray[1];
        } catch (Exception ArrayIndexOutOfBoundsException) {
            throw new DukeException(String.format(MISSING_DATE_TIME, "deadline"));
        }
        try {
            String[] taskDateTimeArray = taskDateTime.split(" ");
            LocalDate taskDate = LocalDate.parse(taskDateTimeArray[0]);
            LocalTime taskTime = null;
            if (taskDateTimeArray.length > 1) {
                taskTime = LocalTime.parse(taskDateTimeArray[1]);
            }
            newTask = new Deadline(taskName, taskDate, taskTime);
        } catch (DateTimeParseException exception) {
            throw new DukeException(WRONG_DATE_TIME_FORMAT);
        }
        return newTask;
    }
    /**
    * Returns the Event created from the user inputText.
    *
    * @param    inputText     input of the user
    * @return                 the created Event
    * @throws   DukeException throws a DukeException
    * @see      Task
    */
    private static Task createEventFromInput(String inputText) throws DukeException {
        Task newTask;
        String taskName;
        String taskDateTime;
        String[] inputStringArray = inputText.split(" /at ");
        try {
            taskName = inputStringArray[0].substring(6);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(MISSING_TASK_INFO, "event"));
        }
        try {
            taskDateTime = inputStringArray[1];
        } catch (Exception ArrayIndexOutOfBoundsException) {
            throw new DukeException(String.format(MISSING_DATE_TIME, "event"));
        }
        try {
            String[] taskDateTimeArray = taskDateTime.split(" ");
            LocalDate taskDate = LocalDate.parse(taskDateTimeArray[0]);
            LocalTime taskTime = null;
            if (taskDateTimeArray.length > 1) {
                taskTime = LocalTime.parse(taskDateTimeArray[1]);
            }
            newTask = new Event(taskName, taskDate, taskTime);
        } catch (DateTimeParseException exception) {
            throw new DukeException(WRONG_DATE_TIME_FORMAT);
        }
        return newTask;
    }
    /**
    * Returns the Todo created from the user inputText.
    *
    * @param    inputText     input of the user
    * @return                 the created Todo
    * @throws   DukeException throws a DukeException
    * @see      Task
    */
    private static Task createTodoFromInput(String inputText) throws DukeException {
        String taskName;
        try {
            taskName = inputText.substring(5);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(MISSING_TASK_INFO, "todo"));
        }
        return new Todo(taskName);
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

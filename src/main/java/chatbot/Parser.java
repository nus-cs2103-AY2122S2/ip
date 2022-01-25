package chatbot;
import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {
    private static final String UNRECOGNIZED_COMMAND = "Sorry Sir, I do not understand that command.";
    private static final String NOT_VALID_NUMBER = "Please enter a valid number Sir.";
    private static final String COMMAND_REQUIRES_NUMBER = "Sorry Sir, this command requires a number.";

    // farewell message
    public static void displayFarewell() {
        String byeMessage = "Farewell Sir. May you have a wonderful day.";
        Ui.displayMessage(byeMessage);
    }
    
    public static boolean parseText(String inputText, TaskList taskList, Storage storage) { // returns true if bot should continue parsing text
        String[] inputStringArray = inputText.split(" ");
        try {
            if (inputText.equals("bye")) { // bye command
                displayFarewell();
                return false;
                
            } else if (inputText.equals("list")) { // list command
                String message = taskList.getTaskListMessage();
                Ui.displayMessage(message);
                return true;

            } else if (inputStringArray[0].equals("mark") || inputStringArray[0].equals("unmark")) { // mark / unmark command
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
            || inputStringArray[0].equals("event")){
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
            } else { // other text input
                throw new DukeException(UNRECOGNIZED_COMMAND);
            }
        } catch (DukeException dukeException) {
            Ui.displayMessage(dukeException.toString());
            return true;
        }
    }

    // creates the specified Task type given an input string
    public static Task createNewTaskFromInput(String type, String inputText) throws DukeException {
        Task newTask;
        String taskName = "";
        String taskDateTime = "";
        String missingDateTime = "Sorry Sir, the description of <" + type + "> is missing a date/time.";
        String missingTaskInfo = "Sorry Sir, the <" + type + "> command cannot be empty.";
        String wrongDateTimeFormat = "Sorry Sir, the date/time needs to be in the format: YYYY-MM-DD HH:MM";

        if (type.equals("deadline")) { // is a deadline task
            String[] inputStringArray = inputText.split(" /by ");
            try { 
                taskName = inputStringArray[0].substring(9);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            try {
                taskDateTime = inputStringArray[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                throw new DukeException(missingDateTime);
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
                throw new DukeException(wrongDateTimeFormat);
            }

        } else if (type.equals("event")) { // is an event task
            String[] inputStringArray = inputText.split(" /at ");
            try { 
                taskName = inputStringArray[0].substring(6);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            try {
                taskDateTime = inputStringArray[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                throw new DukeException(missingDateTime);
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
                throw new DukeException(wrongDateTimeFormat);
            }

        } else { // is a to-do task
            try { 
                taskName = inputText.substring(5);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            newTask = new Todo(taskName);
        }

        return newTask;
    }

    public static int convertToTaskIndex(String inputNumberString) throws DukeException {
        // check if there is an integer after the text command input
        try {
            Integer inputNumberInteger = Integer.parseInt(inputNumberString);
            int taskIndex = inputNumberInteger - 1;
            return taskIndex;

        } catch (NumberFormatException exception) { // not a number
            throw new DukeException(NOT_VALID_NUMBER);
        } 
    }
}

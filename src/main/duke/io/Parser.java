package main.duke.io;

import main.duke.commands.*;
import main.duke.DukeException;

import java.util.Arrays;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * @param userInput the whole console command line from the user
     *
     * @return Command to be executed
     *
     * @throws DukeException when invalid input detected
     */
    public Command parse(String userInput) throws DukeException {
        assert (userInput.length() != 0);
        String[] inputArray = userInput.split(" ");
        String userCommand = inputArray[0];
        Command newCommand;
        switch (userCommand) {
            case "bye":
                newCommand = new CBye();
                break;
            case "list":
                newCommand = new CList();
                break;
            case "mark":
                newCommand = createNewMark(inputArray);
                break;
            case "unmark":
                newCommand = createNewUnmark(inputArray);
                break;
            case "delete":
                newCommand = createNewDelete(inputArray);
                break;
            case "todo":
                newCommand = createNewToDo(inputArray);
                break;
            case "deadline":
                newCommand = createNewDeadline(userInput, inputArray);
                break;
            case "event":
                newCommand = createNewEvent(userInput, inputArray);
                break;
            case "find":
                String findString = String.join(" ",
                        Arrays.copyOfRange(inputArray, 1, inputArray.length));
                newCommand = new CFind(findString);
                break;
            case "undo":
                newCommand = new CUndo();
                break;
            default:
                throw new DukeException("Sorry. I do not understand your input.");
        }
        return newCommand;
    }

    private Command createNewMark(String[] inputArray) throws DukeException{
        try {
            int markIndex = Integer.parseInt(inputArray[1]) - 1;
            return new CMark(markIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the task you wish to mark.");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid index format.");
        }
    }

    private Command createNewUnmark(String[] inputArray) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(inputArray[1]) - 1;
            return new CUnmark(unmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the task you wish to unmark.");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid index format.");
        }
    }

    private Command createNewDelete(String[] inputArray) throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(inputArray[1]) - 1;
            return new CDelete(deleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the task you wish to delete.");
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid index format.");
        }
    }

    private Command createNewToDo(String[] inputArray) throws DukeException {
        String todoDescription = String.join(" ",
                Arrays.copyOfRange(inputArray, 1, inputArray.length));
        if (todoDescription.equals("")) {
            throw new DukeException("Please specify the description of the todo task.");
        }
        return new CTodo(todoDescription);
    }

    private  Command createNewDeadline(String userInput, String[] inputArray) throws DukeException {
        if (!userInput.contains("/by")) {
            throw new DukeException("Please specify the due date using the /by keyword.");
        } else {
            try {
                int byIndex = Arrays.asList(inputArray).indexOf("/by");
                String deadlineDescription = String.join(" ",
                        Arrays.copyOfRange(inputArray, 1, byIndex));
                String dueDate = String.join(" ",
                        Arrays.copyOfRange(inputArray, byIndex + 1, inputArray.length));
                LocalDateTime newDueDate = LocalDateTime.parse(dueDate,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
                // check if the date and time input is in the right format
                if (deadlineDescription.equals("") || dueDate.equals("")) {
                    throw new DukeException("Please specify the description/due date of the deadline task.");
                }
                return new CDeadline(deadlineDescription, newDueDate);
            } catch (DateTimeParseException e){
                throw new DukeException("Please provide the date time in this format YYYY-MM-DD 0000");
            }
        }
    }

    private  Command createNewEvent(String userInput, String[] inputArray) throws DukeException {
        if (!userInput.contains("/at")) {
            throw new DukeException("Please specify the date time using the /at keyword.");
        } else {
            try {
                int byIndex = Arrays.asList(inputArray).indexOf("/at");
                String eventDescription = String.join(" ",
                        Arrays.copyOfRange(inputArray, 1, byIndex));
                String dateTime = String.join(" ",
                        Arrays.copyOfRange(inputArray, byIndex + 1, inputArray.length));
                LocalDateTime newDateTime = LocalDateTime.parse(dateTime,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
                // check if the date and time input is in the right format
                if (eventDescription.equals("") || dateTime.equals("")) {
                    throw new DukeException("Please specify the description/date time of the event task.");
                }
                return new CEvent(eventDescription, newDateTime);
            } catch (DateTimeParseException e){
                throw new DukeException("Please provide the date time in this format YYYY-MM-DD 0000");
            }
        }
    }
}

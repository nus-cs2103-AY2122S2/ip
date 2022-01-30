package main.io;

import main.commands.Command;
import main.commands.CBye;
import main.commands.CMark;
import main.commands.CDelete;
import main.commands.CList;
import main.commands.CTodo;
import main.commands.CDeadline;
import main.commands.CEvent;
import main.commands.CUnmark;
import main.DukeException;

import java.util.Arrays;

public class Parser {
    public Command parse(String userInput) throws DukeException {
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
                try {
                    int markIndex = Integer.parseInt(inputArray[1]) - 1;
                    newCommand = new CMark(markIndex);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify the task you wish to mark.");
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid index format.");
                }
                break;
            case "unmark":
                try {
                    int unmarkIndex = Integer.parseInt(inputArray[1]) - 1;
                    newCommand = new CUnmark(unmarkIndex);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify the task you wish to unmark.");
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid index format.");
                }
                break;
            case "delete":
                try {
                    int deleteIndex = Integer.parseInt(inputArray[1]) - 1;
                    newCommand = new CDelete(deleteIndex);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify the task you wish to delete.");
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid index format.");
                }
                break;
            case "todo":
                String todoDescription = String.join(" ",
                        Arrays.copyOfRange(inputArray, 1, inputArray.length));
                if (todoDescription.equals("")) {
                    throw new DukeException("Please specify the description of the todo task.");
                }
                newCommand = new CTodo(todoDescription);
                break;
            case "deadline":
                if (!userInput.contains("/by")) {
                    throw new DukeException("Please specify the due date using the /by keyword.");
                } else {
                    int byIndex = Arrays.asList(inputArray).indexOf("/by");
                    String deadlineDescription = String.join(" ",
                            Arrays.copyOfRange(inputArray, 1, byIndex));
                    String dueDate = String.join(" ",
                            Arrays.copyOfRange(inputArray, byIndex + 1, inputArray.length));
                    if (deadlineDescription.equals("") || dueDate.equals("")) {
                        throw new DukeException("Please specify the description/due date of the deadline task.");
                    }
                    newCommand = new CDeadline(deadlineDescription, dueDate);
                }
                break;
            case "event":
                if (!userInput.contains("/at")) {
                    throw new DukeException("Please specify the date time using the /at keyword.");
                } else {
                    int byIndex = Arrays.asList(inputArray).indexOf("/at");
                    String eventDescription = String.join(" ",
                            Arrays.copyOfRange(inputArray, 1, byIndex));
                    String dateTime= String.join(" ",
                            Arrays.copyOfRange(inputArray, byIndex + 1, inputArray.length));
                    if (eventDescription.equals("") || dateTime.equals("")) {
                        throw new DukeException("Please specify the description/date time of the event task.");
                    }
                    newCommand = new CEvent(eventDescription, dateTime);
                }
                break;
            default:
                throw new DukeException("Sorry. I do not understand your input.");
        }
        return newCommand;
    }
}

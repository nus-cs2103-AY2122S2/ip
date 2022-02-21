package siri;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//Parser.java reused and edited from Brigette Santoso E0564307
/**
 * Parser is the logic of siri that makes sense of the user inputs
 */
public class Parser {


    static public Command parse(String fullCommand) throws InvalidInputException {
        String firstWordOfCommand = getFirstWord(fullCommand);
        int lengthOfFullCommand = fullCommand.length();
        int lengthOfFirstWord = firstWordOfCommand.length();
        boolean isEmptyString = firstWordOfCommand.length() == 0;
        boolean isSingleWord = lengthOfFirstWord == lengthOfFullCommand;

        if (isEmptyString || !isValidCommand(firstWordOfCommand)) {
            throw new InvalidInputException("Please enter a valid task with a description.");
        } else if (isSingleWord) {
            switch (firstWordOfCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "help":
                return new HelpCommand();
            default:
                throw new InvalidInputException("The description of this command cannot be empty.");
            }
        } else {
            String restOfCommand = getRemainingCommand(fullCommand);
            switch (firstWordOfCommand) {
            case "mark":
                return new MarkCommand(Integer.parseInt(restOfCommand));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(restOfCommand));
            case "delete":
                return new DeleteCommand(Integer.parseInt(restOfCommand));
            case "find":
                return new FindCommand(restOfCommand);
            case "todo":
                return new AddTodoCommand(restOfCommand);
            case "deadline":
                String[] deadlineDetails = restOfCommand.split(" /by ");
                String deadlineDescription = deadlineDetails[0];
                String deadlineDueDate = deadlineDetails[1];
                if (!isDate(deadlineDueDate)) {
                    return new AddDeadlineCommand(deadlineDescription, deadlineDueDate);
                } else {
                    return new AddDeadlineCommand(deadlineDescription, LocalDate.parse(deadlineDueDate));
                }
            case "event":
                String[] eventDetails = restOfCommand.split(" /at ");
                String eventDescription = eventDetails[0];
                String eventDate = eventDetails[1];
                if (!isDate(eventDate)) {
                    return new AddEventCommand(eventDescription, eventDate);
                } else {
                    return new AddEventCommand(eventDescription, LocalDate.parse(eventDate));
                }
            default:
                throw new InvalidInputException("The description of this command cannot be empty.");
            }
        }
    }

    /**
     * Checks if the input s is in the valid Date format, e.g 2019-10-10
     * @param s String of a date
     * @return boolean True or False
     */
    public static boolean isDate(String s) {
        try {
            LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the first string word in the specified sentence/command
     * @param command Full user inputted command
     * @return first word of the user input
     */
    public static String getFirstWord(String command) {
        int index = command.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return command.substring(0, index).trim(); //Extract first word.
        } else {
            return command; //Text is the first word itself.
        }
    }

    /**
     * Returns the string after the specified command
     * @param command User input command
     * @return remainder of the string command after specified command
     */
    public static String getRemainingCommand(String command) {
        int index = command.indexOf(' ');
        int lastIndexOfCommand = command.length();
        return command.substring(index, lastIndexOfCommand).trim(); //Extract first word.
    }

    /**
     * Checks if the user input for a recognised command, e.g todo, list, mark
     * @param command User input command
     * @return boolean True or False
     */
    public static boolean isValidCommand(String command) {
        return (command.equals("bye") || command.equals("list") || command.equals("find")
                || command.equals("delete") || command.equals("mark") || command.equals("unmark")
                || command.equals("todo") || command.equals("deadline") || command.equals("event")
                || command.equals("help"));
    }

}


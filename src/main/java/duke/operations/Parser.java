package duke.operations;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser for Duke. It deals with making sense of the user command.
 */
public class Parser {

    /**
     * Converts an input of type String into LocalDate. E.g., 12-12-2022.
     *
     * @param str input date of format "dd-MM-yyyy". E.g., 12-12-2022.
     * @return input of type LocalDate of format "dd-MM-yyyy". E.g., 12-12-2022.
     */
    public static LocalDate stringToLocalDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(str, formatter);
    }

    /**
     * Converts an input of type String into LocalTime. E.g., 1900.
     *
     * @param str input time of format "HHmm". E.g., 1900.
     * @return input of type LocalTime of format "HHmm". E.g., 1900.
     */
    public static LocalTime stringToLocalTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(str, formatter);
    }

    /**
     * Converts an input of type LocalDate into String. E.g., 12-12-2022.
     *
     * @param date input date of format "dd-MM-yyyy". E.g., 12-12-2022.
     * @return input of type String of format "dd-MM-yyyy". E.g., 12-12-2022.
     */
    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    /**
     * Converts an input of type LocalTime into String. E.g., 1900.
     *
     * @param time input time of format "HHmm". E.g., 1900.
     * @return input of type String of format "HHmm". E.g., 1900.
     */
    public static String localTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return time.format(formatter);
    }

    /**
     * Parse the user input and handles the various commands depending on the input.
     * E.g., if user keys in "todo run", this task will go to the respective if block and handle it.
     *
     * @param input the user input.
     * @return a <code>Command</code> that executes the logic.
     * @throws DukeException handles incomplete and unknown inputs. E.g., "deadline do project /by 12312312".
     */
    public static Command parse(String input) throws DukeException {
        // Create a String array to read various functions
        String[] strs = input.split(" ");

        // Store first word as variable
        String firstWord = strs[0];

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        else if (input.equalsIgnoreCase("list")) {
            return new PrintCommand();
        }

        else if (firstWord.equalsIgnoreCase("mark")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
            return new MarkCommand(listIndex);
        }

        else if (firstWord.equalsIgnoreCase("unmark")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after mark/unmark
            return new UnmarkCommand(listIndex);
        }

        else if (firstWord.equalsIgnoreCase("todo")) {
            String subString = input.substring(4).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                Task toDo = new ToDo(subString);
                Ui.line();
                System.out.println("     Remember to do your task!");
                return new AddCommand(toDo);
            }
        }

        else if(firstWord.equalsIgnoreCase("deadline")) {
            String subString = input.substring(8).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                if (!temp[1].substring(0,3).equals("by ")) {
                    throw new UnknownInputException();
                } else {
                    String deadlineDateTime = temp[1].substring(3); // retrieves the String after '/by'
                    String[] temp1 = deadlineDateTime.split(" ");
                    try {
                        LocalDate deadlineDate = stringToLocalDate(temp1[0]);
                        LocalTime deadlineTime = stringToLocalTime(temp1[1]);
                        Task deadline = new Deadline(temp[0], deadlineDate, deadlineTime);
                        Ui.line();
                        System.out.println("     This task is on a timer!");
                        return new AddCommand(deadline);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("baka, that's the wrong format. Enter dd-MM-yyyy HHmm");
                    }
                }
            }
        }

        else if (firstWord.equalsIgnoreCase("event")) {
            String subString = input.substring(5).trim(); // take the remaining of the input String
            if (subString.length() == 0) {
                throw new IncompleteInputException(firstWord);
            } else {
                String[] temp = subString.split(" /"); // breaks the subString into 2 parts
                if (!temp[1].substring(0,3).equals("at ")) {
                    throw new UnknownInputException();
                } else {
                    String eventDateTime = temp[1].substring(3); // retrieves the String after '/at'
                    String[] temp1 = eventDateTime.split(" ");
                    try {
                        LocalDate eventDate = stringToLocalDate(temp1[0]);
                        String eventTime = temp1[1];
                        String[] splitEventTimes = eventTime.split("-");
                        LocalTime eventStartTime = stringToLocalTime(splitEventTimes[0]);
                        LocalTime eventEndTime = stringToLocalTime(splitEventTimes[1]);
                        Task event = new Event(temp[0], eventDate, eventStartTime, eventEndTime);
                        Ui.line();
                        System.out.println("     Emergency event on this date!");
                        return new AddCommand(event);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("baka, that's the wrong format. Enter dd-MM-yyyy HHmm-HHmm");
                    }
                }
            }
        }

        else if (firstWord.equalsIgnoreCase("delete")) {
            int listIndex = Integer.parseInt(strs[1]); // retrieve the index after delete
            Task taskToBeDeleted = TaskList.taskArr.get(listIndex - 1);
            return new DeleteCommand(taskToBeDeleted);
        }

        else {
            throw new UnknownInputException();
        }
    }
}

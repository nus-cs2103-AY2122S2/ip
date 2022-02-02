package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.io.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    Storage storage;

    /**
     * Represents the parser to decode user's input in the Duke application.
     *
     * @param storage Storage of task in local persistent disk.
     */
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method formats the user's input into a Command type.
     *
     * @param inputTxt User's input line.
     * @return Returns the command to be executed.
     */
    public Command parse(String inputTxt) {
        try {
            if (inputTxt.isEmpty()) {
                throw new DukeException(Ui.MSG_EMPTYINPUT);
            }

            String command = inputTxt.split(" ")[0].toUpperCase();
            CommandType action = CommandType.valueOf(command);
            switch (action) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case TODO:
            case FIND:
                return formatCmdWithSingleInput(action, inputTxt);
            case DEADLINE:
            case EVENT:
                return formatCmdWithTime(action, inputTxt);
            case DONE:
            case DELETE:
                return formatCmdWithIdSelection(action, inputTxt);
            default:
                throw new DukeException(Ui.MSG_INVALIDCMD);
            }
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            Ui.print(Ui.MSG_INVLIADCMDFORMAT);
        } catch (NumberFormatException e) {
            Ui.print(Ui.MSG_INVALDTASKIDFORMAT);
        } catch (IndexOutOfBoundsException e) {
            Ui.print(Ui.MSG_INVALIDTASKID);
        } catch (DateTimeParseException e) {
            Ui.print(Ui.MSG_INVALIDDATETIMEFORMAT);
        } catch (IllegalArgumentException e) {
            Ui.print(Ui.MSG_INVLIADCMDFORMAT);
        }
        return new InvalidCommand();
    }

    private Command formatCmdWithSingleInput(CommandType commandType, String inputTxt) throws DukeException {
        String description = inputTxt.substring(inputTxt.indexOf(' ')).trim();
        if (description.isEmpty()) {
            throw new DukeException(Ui.MSG_EMPTYINPUT);
        } else if (commandType.equals(CommandType.TODO)) {
            return new AddCommand(CommandType.TODO, description);
        } else {
            return new FindCommand(description.toLowerCase());
        }
    }

    private Command formatCmdWithIdSelection(CommandType commandType, String inputTxt) throws DukeException {
        String[] formatInputTxt = inputTxt.split(" ");
        if (formatInputTxt.length != 2) {
            throw new DukeException(Ui.MSG_INVLIADCMDFORMAT);
        }
        int taskId = Integer.parseInt(formatInputTxt[1]) - 1;
        if (commandType.equals(CommandType.DONE)) {
            return new DoneCommand(taskId);
        } else {
            return new DeleteCommand(taskId);
        }
    }

    private Command formatCmdWithTime(CommandType commandType, String inputTxt) throws DukeException {
        String[] formatInputTxt, dateTime;
        LocalDate date;
        LocalTime time;
        String due, description;

        if (commandType.equals(CommandType.DEADLINE)) {
            formatInputTxt = inputTxt.split(" /by ");
        } else {
            formatInputTxt = inputTxt.split(" /at ");
        }

        description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' ')).trim();
        due = formatInputTxt[1].trim();
        if (description.isEmpty() || due.isEmpty()) {
            throw new DukeException(Ui.MSG_INVLIADCMDFORMAT);
        }

        dateTime = due.split(" ");
        if (dateTime.length != 2) {
            throw new DukeException(Ui.MSG_INVALIDDATETIMEFORMAT);
        }
        date = formatDate(dateTime[0]);
        time = formatTime(date, dateTime[1]);

        if (commandType.equals(CommandType.DEADLINE)) {
            return new AddCommand(CommandType.DEADLINE, description, date, time);
        } else {
            return new AddCommand(CommandType.EVENT, description, date, time);
        }
    }

    /**
     * This method formats the date input.
     *
     * @param sDate User's date input in String format.
     * @return The formatted date.
     * @throws DateTimeParseException
     * @throws DukeException          Invalid date format.
     * @see DateTimeParseException
     */
    public static LocalDate formatDate(String sDate) throws DateTimeParseException, DukeException {
        LocalDate inputDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (inputDate.isBefore(LocalDate.now())) {
            throw new DukeException(Ui.MSG_INVALIDDATETIME);
        } else {
            return inputDate;
        }
    }

    /**
     * @param date  Formatted input date.
     * @param sTime User's time input in String format.
     * @return Returns the formatted time.
     * @throws DateTimeParseException
     * @throws DukeException          Invalid time format.
     * @see DateTimeParseException
     */
    public static LocalTime formatTime(LocalDate date, String sTime) throws DateTimeParseException, DukeException {
        LocalTime inputTime = LocalTime.parse(sTime, DateTimeFormatter.ofPattern("HH:mm"));
        if (date.equals(LocalDate.now()) && inputTime.isBefore(LocalTime.now())) {
            throw new DukeException(Ui.MSG_INVALIDDATETIME);
        } else {
            return inputTime;
        }
    }
}

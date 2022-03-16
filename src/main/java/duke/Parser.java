package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.Ui;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.RemindersCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;

public class Parser {

    public static final String SEPARATOR_REPLACEMENT = "-";
    public static final String FORMAT_DATE = "dd-MM-yyyy";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String SEPARATOR_INPUT = " ";

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

            String command = inputTxt.split(SEPARATOR_INPUT)[0].toUpperCase();
            CommandType action = CommandType.valueOf(command);
            switch (action) {
            case BYE:
                return new ExitCommand();
            case HELP:
                return new HelpCommand();
            case LIST:
                return new ListCommand();
            case TODO:
            case FIND:
                return formatCmdWithSingleInput(action, inputTxt);
            case DEADLINE:
            case EVENT:
                return formatCmdWithTime(action, inputTxt);
            case DONE:
            case UNDO:
            case DELETE:
                return formatCmdWithIdSelection(action, inputTxt);
            case REMINDERS:
                return formatCmdWithDateSelection(action, inputTxt);
            default:
                throw new DukeException(Ui.MSG_INVALIDCMD);
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new InvalidCommand(Ui.MSG_INVALDTASKIDFORMAT);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | IllegalArgumentException e) {
            return new InvalidCommand(Ui.MSG_INVLIADCMDFORMAT);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(Ui.MSG_INVALIDTASKID);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(Ui.MSG_INVALIDDATETIMEFORMAT);
        }
    }

    private Command formatCmdWithSingleInput(CommandType commandType, String inputTxt) throws DukeException {
        String description = inputTxt.substring(inputTxt.indexOf(' ')).trim().replaceAll("\\|", SEPARATOR_REPLACEMENT);
        if (description.isEmpty()) {
            throw new DukeException(Ui.MSG_EMPTYINPUT);
        } else if (commandType.equals(CommandType.TODO)) {
            return new AddCommand(CommandType.TODO, description);
        } else {
            return new FindCommand(description.toLowerCase());
        }
    }

    private Command formatCmdWithDateSelection(CommandType commandType, String inputTxt) throws DukeException {
        String[] formatInputTxt = inputTxt.split(SEPARATOR_INPUT);
        if (formatInputTxt.length != 2) {
            throw new DukeException(Ui.MSG_INVLIADCMDFORMAT);
        }
        int dayRange = Integer.parseInt(formatInputTxt[1]);
        return new RemindersCommand(dayRange);
    }

    private Command formatCmdWithIdSelection(CommandType commandType, String inputTxt) throws DukeException {
        String[] formatInputTxt = inputTxt.split(SEPARATOR_INPUT);
        if (formatInputTxt.length != 2) {
            throw new DukeException(Ui.MSG_INVLIADCMDFORMAT);
        }
        int taskId = Integer.parseInt(formatInputTxt[1]) - 1;
        if (commandType.equals(CommandType.DONE)) {
            return new DoneCommand(taskId);
        } else if (commandType.equals(CommandType.UNDO)) {
            return new UndoCommand(taskId);
        } else {
            return new DeleteCommand(taskId);
        }
    }

    private Command formatCmdWithTime(CommandType commandType, String inputTxt) throws DukeException {

        String[] formatInputTxt = commandType.equals(CommandType.DEADLINE)
                ? inputTxt.split(Deadline.DELIMITER)
                : inputTxt.split(Event.DELIMITER);
        String description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' '))
                .trim().replaceAll("\\|", SEPARATOR_REPLACEMENT);
        String due = formatInputTxt[1].trim();

        if (description.isEmpty() || due.isEmpty()) {
            throw new DukeException(Ui.MSG_INVLIADCMDFORMAT);
        }

        String[] dateTime = due.split(SEPARATOR_INPUT);
        if (dateTime.length != 2) {
            throw new DukeException(Ui.MSG_INVALIDDATETIMEFORMAT);
        }
        LocalDate date = formatDate(dateTime[0]);
        LocalTime time = formatTime(date, dateTime[1]);

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
     * @exception  DateTimeParseException
     * @throws DukeException          Invalid date format.
     * @see DateTimeParseException
     */
    public LocalDate formatDate(String sDate) throws DateTimeParseException, DukeException {
        LocalDate inputDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern(FORMAT_DATE));
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
     * @exception DateTimeParseException
     * @throws DukeException          Invalid time format.
     * @see DateTimeParseException
     */
    public LocalTime formatTime(LocalDate date, String sTime) throws DateTimeParseException, DukeException {
        LocalTime inputTime = LocalTime.parse(sTime, DateTimeFormatter.ofPattern(FORMAT_TIME));
        if (date.equals(LocalDate.now()) && inputTime.isBefore(LocalTime.now())) {
            throw new DukeException(Ui.MSG_INVALIDDATETIME);
        } else {
            return inputTime;
        }
    }
}

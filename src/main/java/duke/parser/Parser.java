package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    Ui ui;
    Storage storage;

    public Parser(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    public Command parse(String inputTxt) {
        try {
            if (inputTxt.isEmpty()) {
                throw new DukeException(ui.MSG_EMPTYINPUT);
            }

            String command;
            command = inputTxt.split(" ")[0].toUpperCase();
            CommandType action = CommandType.valueOf(command);
            switch (action) {
                case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case TODO:
                String description = inputTxt.substring(inputTxt.indexOf(' ')).trim();
                if (description.isEmpty()) {
                    throw new DukeException(ui.MSG_EMPTYINPUT);
                } else {
                    return new AddCommand(CommandType.TODO, description);
                }
                case DEADLINE:
                    return formatCmdWithTime(CommandType.DEADLINE, inputTxt);
                case EVENT:
                    return formatCmdWithTime(CommandType.EVENT, inputTxt);
                case DONE:
                    return formatCmdWithIdSelection(CommandType.DONE, inputTxt);
                case DELETE:
                    return formatCmdWithIdSelection(CommandType.DELETE, inputTxt);
                default:
                    throw new DukeException(ui.MSG_INVALIDCMD);
        }

        } catch (DukeException e) {
            ui.print(e.getMessage());
        } catch (NumberFormatException e) {
            ui.print(Ui.MSG_INVALIDTASKID);
        } catch (ArrayIndexOutOfBoundsException|StringIndexOutOfBoundsException e) {
            ui.print(Ui.MSG_INVLIADCMDFORMAT);
        }  catch (IndexOutOfBoundsException e) {
            ui.print(Ui.MSG_INVALIDTASKID);
        } catch (DateTimeParseException e) {
            ui.print(Ui.MSG_INVALIDDATETIMEFORMAT);
        } catch (IllegalArgumentException e) {
            ui.print(Ui.MSG_INVLIADCMDFORMAT);
        }
        return new InvalidCommand();
    }

    private Command formatCmdWithIdSelection(CommandType commandType, String inputTxt) throws DukeException {
        String[] formatInputTxt = inputTxt.split(" ");
        if (formatInputTxt.length != 2) {
            throw new DukeException(ui.MSG_INVLIADCMDFORMAT);
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
            throw new DukeException(ui.MSG_INVLIADCMDFORMAT);
        }

        dateTime = due.split(" ");
        if (dateTime.length != 2) {
            throw new DukeException(ui.MSG_INVALIDDATETIMEFORMAT);
        }
        date = formatDate(dateTime[0]);
        time = formatTime(date, dateTime[1]);

        if (commandType.equals(CommandType.DEADLINE)) {
            return new AddCommand(CommandType.DEADLINE, description, date, time);
        } else {
            return new AddCommand(CommandType.EVENT, description, date, time);
        }
    }

    public static LocalDate formatDate(String sDate) throws DateTimeParseException, DukeException {
        LocalDate inputDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (inputDate.isBefore(LocalDate.now())) {
            throw new DukeException(Ui.MSG_INVALIDDATETIME);
        } else {
            return inputDate;
        }
    }

    public static LocalTime formatTime(LocalDate date, String sTime) throws DateTimeParseException, DukeException {
        LocalTime inputTime = LocalTime.parse(sTime, DateTimeFormatter.ofPattern("HH:mm"));
        if (date.equals(LocalDate.now()) && inputTime.isBefore(LocalTime.now())) {
            throw new DukeException(Ui.MSG_INVALIDDATETIME);
        } else {
            return inputTime;
        }
    }

}

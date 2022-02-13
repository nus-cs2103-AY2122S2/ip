package paggro.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notabledate.NotableDate;
import paggro.storage.Storage;
import paggro.task.Event;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates an event command which creates a new event entry.
 */
public class EventCommand extends Command {
    private static final String FOUR_SPACE = "    ";

    /**
     * Constructor of EventCommand object.
     *
     * @param parameters String containing event description.
     */
    public EventCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a event command which creates a new Event object.
     *
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        ArrayList<String> args = checkParameters(getParameters());
        Task task;
        String description = args.get(0);
        String dateStr = args.get(1);
        LocalDate localDate = parseDate(dateStr);
        NotableDate notableDate = lister.checkDate(localDate);
        String timeStr = args.get(2);
        LocalTime localTime;
        if (timeStr != null) {
            localTime = parseTime(timeStr);
            task = new Event(description, notableDate, localTime, false);
        } else {
            task = new Event(description, notableDate, false);
        }

        notableDate.addTask(task);
        lister.add(task);
        assert lister.getTasks().size() > 0 : "Tasks should have at least one item";

        try {
            storage.addToStorage(task);
        } catch (IOException e) {
            throw new PaggroException(FOUR_SPACE + "Could not add to paggro.txt =.=");
        }

        return ui.showAdded(task) + "\n" + ui.showNumber(lister.getTasks().size());
    }

    private ArrayList<String> checkParameters(String parameters) throws PaggroException {
        String[] desArr = this.getParameters().split(" /", 2);
        ArrayList<String> args = new ArrayList<>();
        try {
            args.add(desArr[0]);
            String dateTimeString = desArr[1];
            String[] dateTimeArr = dateTimeString.split(" ");
            args.add(dateTimeArr[0]);
            String timeString = null;
            if (dateTimeArr.length > 1) {
                timeString = dateTimeArr[1];
            }
            args.add(timeString);
        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
            final String eventFormatError = "Really? =.= The use of the event command must be as follows:\n"
                    + "      event <DESCRIPTION> /<DATE AND/OR TIME>";
            throw new PaggroException(FOUR_SPACE + eventFormatError);
        }
        return args;
    }

    private LocalDate parseDate(String dateStr) throws PaggroException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date;
        } catch (DateTimeParseException e) {
            final String dateInputError = "Really? =.= Date inputs must be in this format:\n"
                    + "      YYYY-MM-DD";
            throw new PaggroException(FOUR_SPACE + dateInputError);
        }
    }

    private LocalTime parseTime(String timeStr) throws PaggroException {
        try {
            LocalTime time = LocalTime.parse(timeStr);
            return time;
        } catch (DateTimeParseException e) {
            final String timeInputError = "Really? =.= Time inputs must be in this format:\n"
                    + "      HH:MM";
            throw new PaggroException(FOUR_SPACE + timeInputError);
        }
    }
}

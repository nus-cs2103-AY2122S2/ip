package paggro.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notabledate.NotableDate;
import paggro.storage.Storage;
import paggro.task.Deadline;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a deadline command which creates a new deadline entry.
 */
public class DeadlineCommand extends Command {
    private static final String FOUR_SPACE = "    ";

    /**
     * Constructor of DeadlineCommand object.
     *
     * @param parameters String containing deadline description.
     */
    public DeadlineCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a deadline command which creates a new Deadline object.
     *
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        String[] desArr = this.getParameters().split(" /", 2);
        Task task;
        try {
            String des = desArr[0];
            String dateTimeString = desArr[1];
            String[] dateTimeArr = dateTimeString.split(" ");
            String dateString = dateTimeArr[0];
            String timeString = null;
            LocalDate date = LocalDate.parse(dateString);
            NotableDate nDate = lister.checkDate(date);
            if (dateTimeArr.length > 1) {
                timeString = dateTimeArr[1];
                try {
                    LocalTime time = LocalTime.parse(timeString);
                    task = (new Deadline(des, nDate, time, false));
                } catch (DateTimeParseException e) {
                    final String timeInputError = "Really? =.= Time inputs must be in this format:\n"
                            + "      HH:MM";
                    throw new PaggroException(FOUR_SPACE + timeInputError);
                }
            } else {
                task = new Deadline(des, nDate, false);
            }
            nDate.addTask(task);
        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
            final String deadlineFormatError = "Really? =.= The use of the deadline command must be as follows:\n"
                    + "      deadline <DESCRIPTION> /<DATE AND/OR TIME>";
            throw new PaggroException(FOUR_SPACE + deadlineFormatError);
        } catch (DateTimeParseException e) {
            final String dateInputError = "Really? =.= Date inputs must be in this format:\n"
                    + "      YYYY-MM-DD";
            throw new PaggroException(FOUR_SPACE + dateInputError);
        }
        lister.add(task);
        assert lister.getTasks().size() > 0 : "Tasks should have at least one item";

        try {
            storage.addToStorage(task);
        } catch (IOException e) {
            throw new PaggroException(FOUR_SPACE + "Could not add to paggro.txt =.=");
        }

        return ui.showAdded(task) + "\n" + ui.showNumber(lister.getTasks().size());
    }
}

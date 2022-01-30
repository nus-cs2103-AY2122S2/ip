package paggro.command;

import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notableDate.NotableDate;
import paggro.storage.Storage;
import paggro.task.Deadline;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a deadline command which creates a new deadline entry.
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor of DeadlineCommand object.
     * @param parameters String containing deadline description.
     */
    public DeadlineCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a deadline command which creates a new Deadline object.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @throws PaggroException
     */
    @Override
    public void execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        String[] desArr = parameters.split(" /", 2);
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
                    throw new PaggroException("    Really? =.= Time inputs must be in this format:\n" +
                            "      HH:MM");
                }
            } else {
                task = new Deadline(des, nDate, false);
            }
            nDate.addTask(task);
        } catch (ArrayIndexOutOfBoundsException e) { // date not given or wrongly formatted
            throw new PaggroException("    Really? =.= The use of the deadline command must be as follows:\n" +
                    "      deadline <DESCRIPTION> /<DATE AND/OR TIME>");
        } catch (DateTimeParseException e) {
            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                    "      YYYY-MM-DD"));
        }
        lister.add(task);

        try {
            storage.addToStorage(task);
        } catch (IOException e) {
            throw new PaggroException("    Could not add to paggro.txt =.=");
        }

        ui.showAdded(task);
        ui.showNumber(lister.tasks.size());
    }
}
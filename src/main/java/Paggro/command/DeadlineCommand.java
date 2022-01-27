package paggro.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;
import paggro.exception.PaggroException;
import paggro.task.Task;
import paggro.task.Deadline;
import paggro.notableDate.NotableDate;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String parameters) {
        super(parameters);
    }

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
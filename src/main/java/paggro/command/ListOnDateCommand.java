package paggro.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notableDate.NotableDate;
import paggro.storage.Storage;
import paggro.ui.Ui;

/**
 * This class encapsulates a listOnDate command which lists out the tasks on a specific date.
 */
public class ListOnDateCommand extends Command {
    /**
     * Constructor of ListOnDateCommand
     */
    public ListOnDateCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a listOnDate command which lists out the tasks on a specific date.
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @throws PaggroException
     */
    @Override
    public void execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        try {
            LocalDate date = LocalDate.parse(this.parameters);
            if (!lister.dateMap.containsKey(date)) {
                ui.showEmptyDate();
            } else {
                NotableDate nDate = lister.dateMap.get(date);
                ui.showList(nDate.tasks);
            }
        } catch (DateTimeParseException e) {
            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n" +
                    "      YYYY-MM-DD"));
        }
    }
}


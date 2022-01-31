package paggro.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notabledate.NotableDate;
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
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        try {
            LocalDate date = LocalDate.parse(this.getParameters());
            if (!lister.getDateMap().containsKey(date)) {
                return ui.showEmptyDate();
            } else {
                NotableDate nDate = lister.getDateMap().get(date);
                return ui.showList(nDate.getTasks());
            }
        } catch (DateTimeParseException e) {
            throw new PaggroException(("    Really? =.= Date inputs must be in this format:\n"
                    + "      YYYY-MM-DD"));
        }
    }
}


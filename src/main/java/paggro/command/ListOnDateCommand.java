package paggro.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import paggro.lister.Lister;
import paggro.ui.Ui;
import paggro.storage.Storage;
import paggro.exception.PaggroException;
import paggro.notableDate.NotableDate;

public class ListOnDateCommand extends Command {
    public ListOnDateCommand(String parameters) {
        super(parameters);
    }

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


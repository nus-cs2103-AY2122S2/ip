package paggro.command;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Tag;
import paggro.ui.Ui;
/**
 * This class encapsulates a listOnDate command which lists out the tasks on a specific date.
 */
public class ListTagCommand extends Command {
    private static final String FOUR_SPACE = "    ";

    /**
     * Constructor of ListOnDateCommand
     *
     * @param parameters String of date to be searched for.
     */
    public ListTagCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of a listOnDate command which lists out the tasks on a specific date.
     *
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        String tagStr = this.getParameters();
        if (!lister.getTagMap().containsKey(tagStr)) {
            return ui.showEmptyTag();
        } else {
            Tag tag = lister.getTagMap().get(tagStr);
            return ui.showList(tag.getTasks());
        }
    }
}


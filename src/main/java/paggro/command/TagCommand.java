package paggro.command;

import java.io.IOException;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.storage.Storage;
import paggro.task.Tag;
import paggro.task.Task;
import paggro.ui.Ui;

/**
 * This class encapsulates a mark command which marks a given task as done.
 */
public class TagCommand extends Command {
    private static final String FOUR_SPACE = "    ";

    /**
     * Constructor of MarkCommand
     *
     * @param parameters String of index to be marked as done.
     */
    public TagCommand(String parameters) {
        super(parameters);
    }

    /**
     * Carries out the execution of the tag command which tags the given task with a string.
     *
     * @param lister The Lister object for the command to execute on.
     * @param ui The Ui object for the command to execute on.
     * @param storage The Storage object for the command to execute on.
     * @return String of response to the command.
     * @throws PaggroException
     */
    @Override
    public String execute(Lister lister, Ui ui, Storage storage) throws PaggroException {
        int index;
        String tagDes;
        String[] args = this.getParameters().split(" ", 2);
        try {
            index = Integer.parseInt(args[0]);
            tagDes = args[1];
        } catch (NumberFormatException e) { // parameter was not a number
            final String invalidInputError = "Really? Can you input an actual number this time... =.=";
            throw new PaggroException(FOUR_SPACE + invalidInputError);
        } catch (ArrayIndexOutOfBoundsException e) {
            final String eventFormatError = "Really? =.= The use of the tag command must be as follows:\n"
                    + "      tag <INDEX> <TAG DESCRIPTION>";
            throw new PaggroException(FOUR_SPACE + eventFormatError);
        }

        if (index > lister.getTasks().size()) {
            final String invalidIndexError = "Really? There is no item indexed at " + index + "... =.=";
            throw new PaggroException(FOUR_SPACE + invalidIndexError);
        }

        Tag tag = lister.checkTag(tagDes);
        lister.tag(index - 1, tag);
        Task task = lister.getTasks().get(index - 1);
        tag.addTaskToTag(task);

        try {
            storage.updateInStorage(index, task);
        } catch (IOException e) {
            throw new PaggroException(FOUR_SPACE + "Could not tag in paggro.txt =.=");
        }

        return ui.showTagged(task);
    }
}

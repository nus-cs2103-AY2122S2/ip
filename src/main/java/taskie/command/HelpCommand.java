package taskie.command;

import taskie.storage.Storage;
import taskie.tasklist.TaskList;
import taskie.ui.Ui;


/**
 * A class that specifies the behavior of a command that provides help to the user.
 */
public class HelpCommand extends Command {
    private String keyword;

    public HelpCommand() {
        super("help");
        this.keyword = "";
    }

    public HelpCommand(String keyword) {
        super("help");
        this.keyword = keyword;
    }

    /**
     * Executes the actions of the command.
     *
     * @param tasks TaskList to act on.
     * @param ui Ui to use when printing messages.
     * @param storage Storage to call for loading and saving tasks.
     * @param response StringBuilder object to append results to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, StringBuilder response) {
        if (hasKeyword()) {
            response.append(ui.getHelpMessage(keyword));
        } else {
            response.append(ui.getHelpMessage());
        }
        assert response.length() > 0; // response should not be empty
    }

    private boolean hasKeyword() {
        return !keyword.equals("");
    }
}

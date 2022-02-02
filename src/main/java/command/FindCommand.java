package command;

import main.Storage;
import main.TaskList;
import main.TessUi;

/**
 * Create a command to find tasks according to a keyword.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class FindCommand extends Command {
    /** Index of the keyword to find */
    protected static final int KEYWORD = 1;
    protected static final int COMMAND = 0;

    protected String command;
    protected String keyword;

    FindCommand(String[] cmdArr) {
        super(cmdArr[COMMAND]);
        this.command = cmdArr[COMMAND];
        this.keyword = cmdArr[KEYWORD];
    }

    /**
     * Execute the command on the system to find tasks according to keywords.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        if (this.command.equals("find")) {
            ui.filterRes(keyword, taskList.filterByKeyword(keyword).toString());
        } else {
            ui.admitBug();
        }
    }
}

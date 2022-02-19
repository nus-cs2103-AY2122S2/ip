package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This FindCommand class will find a list of added tasks that match the keyword
 * when executed.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_EXAMPLE = "find list";
    private String keyword;


    /**
     * Constructor for FindCommand which finds matching tasks in the list of tasks.
     *
     * @param keyword Keyword to find matching tasks in list of tasks.
     */
    public FindCommand(String keyword) {
        super(COMMAND_WORD, COMMAND_EXAMPLE);
        this.keyword = keyword;
    }

    /**
     * Executes command by adding task into Duke.Duke.util.TaskList.
     *
     * @param taskList List of tasks
     * @param ui       Ui provided
     * @param storage  Saved history
     * @return message to tell user of the tasks matching with keyword given
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = taskList.findTaskMatchingKeyword(this.keyword);
        return output;
    }
}

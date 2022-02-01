package duke.command;

import duke.exception.DukeException;

import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * This FindCommand class will find a list of added tasks that match the keyword
 * when executed.
 */
public class FindCommand extends Command {

    private final String keyword;
    public static final String COMMAND_WORD = "find";


    /**
     * Constructor for FindCommand which finds matching tasks in the list of tasks.
     *
     * @param keyword Keyword to find matching tasks in list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes command by adding task into Duke.Duke.util.TaskList.
     * @param taskList List of tasks
     * @param ui       Ui provided
     * @param storage  Saved history
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTaskMatchingKeyword(this.keyword);
    }
}

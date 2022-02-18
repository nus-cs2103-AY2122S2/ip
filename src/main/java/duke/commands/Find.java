package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.exceptions.UndoException;
import duke.tasks.TaskList;

/**
 * Represents a command that allows users to find tasks
 * based on the input that the user has keyed into Duke.
 * */
public class Find extends Command {
    private final String query;

    /**
     * Initialize A Find Command.
     *
     * @param query A search query that the user has keyed in.
     */
    public Find(String query) {
        this.query = query;
    }

    /**
     * Returns a success string after the system has queried the task list given the search query.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return A success message after the queried results are retrieved from the task list.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        return TaskList.findTask(query);
    }

    /**
     * Returns an UndoException since a find command cannot be undone.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @throws DukeException since the program is unable to undo a find command.
     */
    @Override
    public String undo(TaskList taskList) throws DukeException {
        throw new UndoException("NOTHING");
    }

}

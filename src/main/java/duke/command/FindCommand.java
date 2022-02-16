package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

/**
 * Represents a command to find a task with keywords.
 */
public class FindCommand extends Command {
    private String keyword;
    private DukeException exception;

    /**
     * Initializes the find command with user input.
     *
     * @param fullCommand The user input.
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);

        String[] fullCommandParsed = fullCommand.split(" ");
        try {
            this.keyword = fullCommandParsed[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.exception = new DukeException("Please input a keyword so that I can find your task for you!");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        }
        TaskList filteredTasks = tasks.filterByKeyword(this.keyword);
        return filteredTasks.toStringTags(ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

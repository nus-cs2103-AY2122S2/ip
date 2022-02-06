package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.task.TaskList;

/**
 * Created when user inputs a command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Creates a ExitCommand object.
     */
    public ExitCommand() {}

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getResponseAfterCommand(TaskList taskList) {
        return "Bye. See you again soon!";
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

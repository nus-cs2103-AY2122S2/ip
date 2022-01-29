package seedu.duke.command;

import seedu.duke.chatbot.Storage;
import seedu.duke.chatbot.Ui;
import seedu.duke.task.TaskList;

/**
 * Created when user inputs a command to exit Duke.
 */
public class ExitCommand extends Command {
    private final String userName;

    /**
     * Creates a ExitCommand object.
     * @param userName which is the name of the user
     */
    public ExitCommand(String userName) {
        this.userName = userName;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(String.format("Bye %s. See you again soon!", this.userName));
        return taskList;
    }

    /**
     *{@inheritDoc}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package seedu.duke.command;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import seedu.duke.chatbot.Storage;
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
    public TaskList execute(TaskList taskList, Storage storage) {
        //@@author isabelteo - reused
        //reused from https://stackoverflow.com/questions/15747277
        // /how-to-make-java-program-exit-after-a-couple-of-seconds
        Executors
                .newSingleThreadScheduledExecutor()
                .schedule(() -> System.exit(0), 2, TimeUnit.SECONDS);
        //@@author
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

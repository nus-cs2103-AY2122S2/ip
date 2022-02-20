package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.TaskStatus;

/**
 * Command to mark or unmark a task.
 */
public class MarkCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Marked task \'%s\' as done.";

    /**
     * The type of status to change the task to.
     */
    private TaskStatus status;

    /**
     * Constructor to initialize the task status.
     *
     * @param status Task status.
     */
    public MarkCommand(TaskStatus status) {
        this.status = status;
    }

    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to mark or unmark.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!this.hasDefaultArgument()) {
            setErroneousResult(new JukeMissingArgumentException(status.getCommandName()));
            return this;
        }
        return this;
    }

    /**
     * Returns if there are unnecessary parameters.
     *
     * @return Boolean result.
     */
    private boolean hasUnnecessaryParameters() {
        for (String param : paramArgs.keySet()) {
            if (isDefaultParameter(param)) {
                continue;
            }
            setErroneousResult(new JukeInvalidParameterException(param));
            return true;
        }
        return false;
    }

    /**
     * Tries to execute the command, updating the result.
     * Marks or unmarks a task as done.
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        if (this.isSuccessful()) {
            return this;
        }
        this.checkParametersAndArguments();
        if (this.isErroneous()) {
            return this;
        }
        assert isEmpty();
        try {
            markTask();
        } catch (NumberFormatException e) {
            setErroneousResult(e);
            return this;
        } catch (IndexOutOfBoundsException e) {
            setErroneousResult(new JukeInvalidTaskIndexException());
            return this;
        }
        juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Marks or unmarks the task as done.
     *
     * @throws NumberFormatException Throws if cannot parse to integer.
     * @throws IndexOutOfBoundsException Throws if index is out of bounds.
     */
    private void markTask() throws NumberFormatException, IndexOutOfBoundsException {
        int index = Integer.parseInt(getDefaultArgument()) - 1;
        switch (status) {
        case DONE:
            juke.getTaskList().markTask(index);
            break;
        case NOT_DONE:
            juke.getTaskList().unmarkTask(index);
            break;
        default:
        }
        setSuccessfulResult(String.format(SUCCESS_MESSAGE,
            juke.getTaskList().get(index).getDescription()));
    }

    /**
     * Returns the task status of the command.
     *
     * @return Task status.
     */
    public TaskStatus getStatus() {
        return this.status;
    }
}

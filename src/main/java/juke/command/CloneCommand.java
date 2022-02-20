package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.Task;

/**
 * Command for cloning tasks.
 */
public class CloneCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Successfully cloned task: %s.";
    private static final String COMMAND_NAME = "clone";

    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to clone.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!hasDefaultArgument()) {
            setErroneousResult(new JukeMissingArgumentException(COMMAND_NAME));
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
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        if (isSuccessful()) {
            return this;
        }
        checkParametersAndArguments();
        if (isErroneous()) {
            return this;
        }
        assert isEmpty();
        try {
            cloneTask();
        } catch (NumberFormatException e) {
            setErroneousResult(e);
            return this;
        } catch (IndexOutOfBoundsException e) {
            setErroneousResult(new JukeInvalidTaskIndexException());
            return this;
        } catch (CloneNotSupportedException e) {
            // Should not reach here
            assert false;
            setErroneousResult(e);
            return this;
        }
        this.juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Clones the task at the given index.
     *
     * @throws NumberFormatException Throws if cannot parse to integer.
     * @throws CloneNotSupportedException Should not throw.
     */
    private void cloneTask() throws NumberFormatException, CloneNotSupportedException {
        int index = Integer.parseInt(getDefaultArgument()) - 1;
        Task task = (Task) juke.getTaskList().get(index).clone();
        juke.getTaskList().add(task);
        setSuccessfulResult(String.format(SUCCESS_MESSAGE, task.getDescription()));
    }
}

package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;
import juke.exception.JukeParseException;
import juke.task.Task;
import juke.task.TimeTask;

/**
 * Command for editing tasks.
 */
public class EditCommand extends Command {
    private static final String SUCCESS_MESSAGE = "Successfully edited task: %s.";
    private static final String COMMAND_NAME = "edit";

    private static final String DESCRIPTION_PARAMETER = "d";
    private static final String TIME_PARAMETER = "t";

    /**
     * Task to edit.
     */
    private Task task;

    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to edit.
     * '-d' to edit the description field.
     * '-t' to edit the time field of time tasks.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        if (isMissingParameters()) {
            return this;
        }
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
     * Returns if missing parameters.
     *
     * @return Boolean result.
     */
    private boolean isMissingParameters() {
        if (paramArgs.size() == 1) {
            assert paramArgs.containsKey(DEFAULT_PARAMETER);
            setErroneousResult(new JukeMissingArgumentException(COMMAND_NAME));
            return true;
        }

        return false;
    }

    /**
     * Returns if there are unnecessary parameters.
     *
     * @return Boolean result.
     */
    private boolean hasUnnecessaryParameters() {
        for (String param : this.paramArgs.keySet()) {
            if (!isDefaultParameter(param)
                && !param.equals(DESCRIPTION_PARAMETER)
                && !param.equals(TIME_PARAMETER)) {
                setErroneousResult(new JukeInvalidParameterException(param));
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to execute the command, updating the result.
     * Edits the task at the given index.
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
            getTask();
        } catch (NumberFormatException e) {
            setErroneousResult(e);
            return this;
        }
        assert paramArgs.size() > 1;
        assert task != null;
        String oldDescription = task.getDescription();
        if (!canEditTask()) {
            return this;
        }
        setSuccessfulResult(String.format(SUCCESS_MESSAGE, oldDescription));
        juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Gets the task at the given index.
     *
     * @throws NumberFormatException Throws if cannot parse to integer.
     */
    private void getTask() throws NumberFormatException {
        int index = Integer.parseInt(getDefaultArgument()) - 1;
        task = juke.getTaskList().get(index);
    }

    /**
     * Edits the task at the given index.
     *
     * @return Boolean result.
     */
    private boolean canEditTask() {
        if (hasParameter(DESCRIPTION_PARAMETER)) {
            if (!hasArgument(DESCRIPTION_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(COMMAND_NAME));
                return false;
            }
            task.setDescription(getArgument(DESCRIPTION_PARAMETER));
        }
        if (hasParameter(TIME_PARAMETER)) {
            if (!(task instanceof TimeTask)) {
                setErroneousResult(new JukeInvalidParameterException(TIME_PARAMETER));
                return false;
            }
            if (!hasArgument(TIME_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(COMMAND_NAME));
                return false;
            }
            assert task instanceof TimeTask;
            try {
                // Checkstyle error with type-cast brackets
                ((TimeTask) task).setTime(getArgument(TIME_PARAMETER));
            } catch (JukeParseException e) {
                setErroneousResult(e);
                return false;
            }
        }
        return true;
    }
}

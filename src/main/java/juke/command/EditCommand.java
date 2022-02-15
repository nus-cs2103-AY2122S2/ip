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
        if (paramArgs.size() == 1) {
            assert paramArgs.containsKey(DEFAULT_PARAMETER);
            result = Result.error(new JukeMissingArgumentException("edit"));
        }
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param) && !param.equals("d") && !param.equals("t")) {
                this.result = Result.error(new JukeInvalidParameterException(param));
                return this;
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeMissingArgumentException("edit"));
            return this;
        }
        assert hasDefaultArgument();
        return this;
    }

    /**
     * Tries to execute the command, updating the result.
     * Edits the task at the given index.
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        Task task;
        if (this.isSuccessful()) {
            return this;
        }
        this.checkParametersAndArguments();
        if (this.isErroneous()) {
            return this;
        }
        try {
            int index = Integer.parseInt(getDefaultArgument()) - 1;
            task = juke.getTaskList().get(index);
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        }
        assert paramArgs.size() > 1;
        assert task != null;
        String oldDescription = task.getDescription();
        if (hasParameter("d")) {
            if (!hasArgument("d")) {
                result = Result.error(new JukeMissingArgumentException("edit"));
                return this;
            }
            task.setDescription(getArgument("d"));
        }
        if (hasParameter("t")) {
            if (!(task instanceof TimeTask)) {
                result = Result.error(new JukeInvalidParameterException("t"));
                return this;
            }
            if (!hasArgument("t")) {
                result = Result.error(new JukeMissingArgumentException("edit"));
                return this;
            }
            assert task instanceof TimeTask;
            try {
                // Checkstyle error with type-cast brackets
                ((TimeTask) task).setTime(getArgument("t"));
            } catch (JukeParseException e) {
                result = Result.error(e);
                return this;
            }
        }
        result = Result.success(String.format("Successfully edited task: %s.", oldDescription));
        juke.getStorage().saveTasks();
        return this;
    }
}

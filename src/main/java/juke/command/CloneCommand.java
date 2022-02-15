package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;
import juke.task.Task;

/**
 * Command for cloning tasks.
 */
public class CloneCommand extends Command {
    /**
     * Checks if the parameters and arguments are valid.
     * Requires an integer relating to the index of the task to clone.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param)) {
                this.result = Result.error(new JukeInvalidParameterException(param));
                return this;
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeMissingArgumentException("clone"));
            return this;
        }
        return this;
    }

    /**
     * Tries to execute the command, updating the result.
     * Clones the task at the given index.
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
        try {
            int index = Integer.parseInt(this.getDefaultArgument()) - 1;
            Task task = (Task) juke.getTaskList().get(index).clone();
            assert task instanceof Task;
            juke.getTaskList().add(task);
            result = Result.success(String.format("Successfully cloned task: %s.", task.getDescription()));
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        } catch (CloneNotSupportedException e) {
            // Should not reach here
            assert false;
            this.result = Result.error(e);
            return this;
        }
        this.juke.getStorage().saveTasks();
        return this;
    }
}

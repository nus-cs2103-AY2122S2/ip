package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.TaskStatus;

/**
 * Command to mark or unmark a task.
 */
public class MarkCommand extends Command {
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
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param)) {
                this.result = Result.error(new JukeInvalidParameterException(param));
                return this;
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeMissingArgumentException(this.status.getCommandName()));
            return this;
        }
        return this;
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
        try {
            int index = Integer.parseInt(this.getDefaultArgument()) - 1;
            switch (this.status) {
            case DONE:
                if (this.juke.getTaskList().markTask(index)) {
                    this.result = Result.success(String.format("Marked task \'%s\' as done.",
                        this.juke.getTaskList().get(index).getDescription()));
                } else {
                    this.result = Result.error(new JukeInvalidTaskIndexException());
                    return this;
                }
                break;
            case NOT_DONE:
                if (this.juke.getTaskList().unmarkTask(index)) {
                    this.result = Result.success(String.format("Marked task \'%s\' as done.",
                        this.juke.getTaskList().get(index).getDescription()));
                } else {
                    this.result = Result.error(new JukeInvalidTaskIndexException());
                    return this;
                }
                break;
            default:
            }
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        }
        this.juke.getStorage().saveTasks();
        return this;
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

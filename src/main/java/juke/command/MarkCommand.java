package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeInvalidTaskIndexException;
import juke.exception.JukeMissingArgumentException;
import juke.task.TaskStatus;

public class MarkCommand extends Command {
    private TaskStatus status;
    
    public MarkCommand(TaskStatus status) {
        this.status = status;
    }
    
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
    
    @Override
    public Command execute() {
        if (this.isSuccessful()) {
            return this;
        }
        this.checkParametersAndArguments();
        if (this.isErranous()) {
            return this;
        }
        try {
            int index = Integer.parseInt(this.getDefaultArgument()) - 1;
            switch (this.status) {
            case DONE:
                if (this.juke.getTaskList().markTask(index)) {
                    this.result = Result.success(String.format("Marked task \u00ab%s\u00bb as done.",
                        this.juke.getTaskList().get(index).getDescription()));
                } else {
                    this.result = Result.error(new JukeInvalidTaskIndexException());
                    return this;
                }
                break;
            case NOT_DONE:
                if (this.juke.getTaskList().unmarkTask(index)) {
                    this.result = Result.success(String.format("Marked task \u00ab%s\u00bb as done.",
                        this.juke.getTaskList().get(index).getDescription()));
                } else {
                    this.result = Result.error(new JukeInvalidTaskIndexException());
                    return this;
                }
                break;
            }
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        }
        this.juke.getStorage().saveTasks();
        return this;
    }
    
    public TaskStatus getStatus() {
        return this.status;
    }
}

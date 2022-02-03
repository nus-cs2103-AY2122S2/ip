package juke.command;

import juke.exception.JukeException;
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
                this.result = Result.error(new JukeException("Unknown parameter " + param));
                return this;
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeException("Missing default argument"));
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
                    this.result = Result.success("Marked task as done.");
                } else {
                    this.result = Result.error(new JukeException("Invalid index"));
                    return this;
                }
                break;
            case NOT_DONE:
                if (this.juke.getTaskList().unmarkTask(index)) {
                    this.result = Result.success("Marked task as not done");
                } else {
                    this.result = Result.error(new JukeException("Invalid index"));
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
}

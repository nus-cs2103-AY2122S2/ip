package juke.command;

import juke.exception.JukeException;
import juke.task.Task;
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
            if (index < 0 || index >= juke.getTaskList().size()) {
                this.result = Result.error(new JukeException("Invalid index"));
                return this;
            }
            Task task = this.juke.getTaskList().get(index);
            switch (this.status) {
            case DONE:
                task.markAsDone();
                this.result = Result.success("Marked task as done.");
                break;
            case NOT_DONE:
                task.markAsNotDone();
                this.result = Result.success("Marked task as not done");
                break;
            }
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        }
        return this;
    }
}

package juke.command;

import juke.exception.JukeException;

public class DeleteCommand extends Command {
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
            this.juke.getTaskList().remove(index);
            this.result = Result.success("Successfully deleted.");
        } catch (NumberFormatException e) {
            this.result = Result.error(e);
            return this;
        }
        this.juke.saveFile();
        return this;
    }
}

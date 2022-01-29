package juke.command;

import juke.exception.JukeException;

/**
 * Command for echo.
 */
public class EchoCommand extends Command {
    private String message;
    
    @Override
    public Command checkParametersAndArguments() {
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param)) {
                this.result = new Result.Error(new JukeException("Unknwon parameter " + param));
                return this;
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = new Result.Error(new JukeException("Missing default argument"));
            return this;
        }
        return this;
    }
    
    @Override
    public Command execute() {
        if (this.result instanceof Result.Success) {
            return this;
        }
        this.checkParametersAndArguments();
        if (this.result instanceof Result.Error) {
            return this;
        }
        this.message = this.getDefaultArgument();
        this.result = new Result.Success(this.message);
        return this;
    }
}

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
        this.message = this.getDefaultArgument();
        this.result = new Result.Success(this.message);
        return this;
    }
}

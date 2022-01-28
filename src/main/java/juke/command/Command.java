package juke.command;

import juke.Juke;
import juke.common.Ui;
import juke.exception.JukeException;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public abstract class Command {
    protected static final String DEFAULT_PARAMETER = "";
    
    protected HashMap<String, Optional<String>> paramArgs;
    protected Result result;
    
    public Command() {
        this.paramArgs = new HashMap<>();
        this.paramArgs.put("", Optional.empty());
        this.result = new Result.Empty();
    }
    
    public abstract Command checkParametersAndArguments();
    
    public abstract Command execute();
    
    
    public boolean hasParameter(String param) {
        return this.paramArgs.containsKey(param);
    }
    
    public boolean hasArgument(String param) {
        return this.paramArgs.get(param).isPresent();
    }
    
    public String getArgument(String param) {
        if (this.hasParameter(param)) {
            return this.paramArgs.get(param).orElseGet(() -> {
                this.result = new Result.Error(new JukeException("Missing argument for parameter " + param));
                return "";
            });
        } else {
            this.result = new Result.Error(new JukeException("Missing parameter " + param));
            return "";
        }
    }
    
    public boolean isDefaultParameter(String param) {
        return param.equals(DEFAULT_PARAMETER);
    }
    
    public boolean hasDefaultArgument() {
        return this.hasArgument(DEFAULT_PARAMETER);
    }
    
    public String getDefaultArgument() {
        return this.paramArgs.get(DEFAULT_PARAMETER).orElseGet(() -> {
            this.result = new Result.Error(new JukeException("Missing default argument"));
            return "";
        });
    }
    
    public Command addParameter(String param, String args) {
        Optional<String> option = Optional.empty();
        // Lazy evaluation right here
        if (args != null && !args.isBlank()) {
            option = Optional.of(args);
        }
        if (this.paramArgs.containsKey(param)) {
            this.paramArgs.replace(param, option);
        } else {
            this.paramArgs.put(param, option);
        }
        return this;
    }
    
    public Command removeParameter(String param) {
        if (param == null || param.isBlank()) {
            this.paramArgs.replace("", Optional.empty());
        } else {
            this.paramArgs.remove(param);
        }
        return this;
    }
    
    public Result getResult() {
        return this.result;
    }
    
    public boolean isSuccessful() {
        return this.result instanceof Result.Success;
    }
}

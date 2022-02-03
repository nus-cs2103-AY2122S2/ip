package juke.command;

import juke.exception.JukeException;
import juke.task.Task;

import java.util.List;

public class FindCommand extends Command {
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
        List<Task> tasks = this.juke.getTaskList().search(this.getDefaultArgument());
        if (tasks.size() == 0) {
            this.result = Result.error(new JukeException("No results found"));
            return this;
        }
        String[] descriptions = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            descriptions[i] = tasks.get(i).toString();
        }
        this.result = new Result.Success(descriptions);
        return this;
    }
}

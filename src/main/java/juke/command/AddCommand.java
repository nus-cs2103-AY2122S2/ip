package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;
import juke.exception.JukeParseException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.TaskType;
import juke.task.Todo;

public class AddCommand extends Command {
    private TaskType type;
    
    public AddCommand(TaskType type) {
        this.type = type;
    }
    
    @Override
    public Command checkParametersAndArguments() {
        switch (this.type) {
        case EVENT:
            if (this.hasParameter("at")) {
                if (this.hasArgument("at")) {
                
                } else {
                    this.result = Result.error(new JukeMissingArgumentException(this.type.getCommandName()));
                    return this;
                }
            } else {
                this.result = Result.error(new JukeMissingArgumentException(this.type.getCommandName()));
                return this;
            }
            break;
        case DEADLINE:
            if (this.hasParameter("by")) {
                if (this.hasArgument("by")) {
            
                } else {
                    this.result = Result.error(new JukeMissingArgumentException(this.type.getCommandName()));
                    return this;
                }
            } else {
                this.result = Result.error(new JukeMissingArgumentException(this.type.getCommandName()));
                return this;
            }
            break;
        }
        for (String param : this.paramArgs.keySet()) {
            if (!this.isDefaultParameter(param)) {
                switch (this.type) {
                case EVENT:
                    if (!param.equals("at")) {
                        this.result = Result.error(new JukeInvalidParameterException(param));
                        return this;
                    }
                    break;
                case DEADLINE:
                    if (!param.equals("by")) {
                        this.result = Result.error(new JukeInvalidParameterException(param));
                        return this;
                    }
                    break;
                default:
                    this.result = Result.error(new JukeInvalidParameterException(param));
                    return this;
                }
            }
        }
        if (!this.hasDefaultArgument()) {
            this.result = Result.error(new JukeMissingArgumentException(this.type.getCommandName()));
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
            switch (this.type) {
            case TODO:
                this.juke.getTaskList().add(new Todo(this.getDefaultArgument()));
                break;
            case EVENT:
                this.juke.getTaskList().add(new Event(this.getDefaultArgument(),
                        this.getArgument("at")));
                break;
            case DEADLINE:
                this.juke.getTaskList().add(new Deadline(this.getDefaultArgument(),
                        this.getArgument("by")));
                break;
            }
            this.result = Result.success(String.format("New %s added: %s.",
                    this.type.getCommandName(), this.getDefaultArgument()));
        } catch (JukeParseException e) {
            this.result = Result.error(e);
            return this;
        }
        this.juke.getStorage().saveTasks();
        return this;
    }
    
    public TaskType getType() {
        return this.type;
    }
}

package juke.command;

import juke.exception.JukeInvalidParameterException;
import juke.exception.JukeMissingArgumentException;
import juke.exception.JukeParseException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.TaskType;
import juke.task.Todo;

/**
 * Command for adding tasks.
 */
public class AddCommand extends Command {
    /**
     * Task type of the task to add.
     */
    private TaskType type;

    /**
     * Constructor to initialize command with the task type.
     *
     * @param type Type of task.
     */
    public AddCommand(TaskType type) {
        this.type = type;
    }

    /**
     * Checks if the parameters and arguments are valid.
     * Event tasks require '-at'.
     * Deadline tasks require '-by'.
     * Todo tasks do no require any additional parameters.
     *
     * @return This command.
     */
    @Override
    public Command checkParametersAndArguments() {
        switch (type) {
        case EVENT:
            assert type.getCommandName() == TaskType.EVENT.getCommandName();
            if (hasParameter("at")) {
                if (!hasArgument("at")) {
                    result = Result.error(new JukeMissingArgumentException(type.getCommandName()));
                    return this;
                }
            } else {
                result = Result.error(new JukeMissingArgumentException(type.getCommandName()));
                return this;
            }
            break;
        case DEADLINE:
            assert type.getCommandName() == TaskType.DEADLINE.getCommandName();
            if (hasParameter("by")) {
                if (!hasArgument("by")) {
                    result = Result.error(new JukeMissingArgumentException(type.getCommandName()));
                    return this;
                }
            } else {
                result = Result.error(new JukeMissingArgumentException(type.getCommandName()));
                return this;
            }
            break;
        default:
        }
        for (String param : paramArgs.keySet()) {
            if (!isDefaultParameter(param)) {
                switch (type) {
                case EVENT:
                    if (!param.equals("at")) {
                        result = Result.error(new JukeInvalidParameterException(param));
                        return this;
                    }
                    break;
                case DEADLINE:
                    if (!param.equals("by")) {
                        result = Result.error(new JukeInvalidParameterException(param));
                        return this;
                    }
                    break;
                default:
                    result = Result.error(new JukeInvalidParameterException(param));
                    return this;
                }
            }
        }
        if (!hasDefaultArgument()) {
            result = Result.error(new JukeMissingArgumentException(type.getCommandName()));
            return this;
        }
        assert hasDefaultArgument();
        return this;
    }

    /**
     * Tries to execute the command, updating the result.
     * Adds the task to the task list.
     *
     * @return This command.
     */
    @Override
    public Command execute() {
        if (isSuccessful()) {
            return this;
        }
        checkParametersAndArguments();
        if (isErroneous()) {
            return this;
        }
        assert result instanceof Result.Empty;
        try {
            switch (type) {
            case TODO:
                juke.getTaskList().add(new Todo(getDefaultArgument()));
                break;
            case EVENT:
                juke.getTaskList().add(new Event(getDefaultArgument(),
                        getArgument("at")));
                break;
            case DEADLINE:
                juke.getTaskList().add(new Deadline(getDefaultArgument(),
                        getArgument("by")));
                break;
            default:
            }
            result = Result.success(String.format("New %s added: %s.",
                    type.getCommandName(), getDefaultArgument()));
        } catch (JukeParseException e) {
            result = Result.error(e);
            return this;
        }
        juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Returns the task type of the command.
     *
     * @return Task type.
     */
    public TaskType getType() {
        return type;
    }
}

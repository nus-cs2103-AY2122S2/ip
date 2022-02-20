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
    private static final String SUCCESS_MESSAGE = "New %s task added: %s.";

    private static final String EVENT_PARAMETER = "at";
    private static final String DEADLINE_PARAMETER = "by";

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
        if (isMissingTimeParameter()) {
            return this;
        }
        if (hasUnnecessaryParameters()) {
            return this;
        }
        if (!hasDefaultArgument()) {
            setErroneousResult(new JukeMissingArgumentException(type.getCommandName()));
            return this;
        }
        assert hasDefaultArgument();
        return this;
    }

    /**
     * Returns if time parameters are missing.
     *
     * @return Boolean result.
     */
    private boolean isMissingTimeParameter() {
        switch (type) {
        case EVENT:
            assert type.getCommandName() == TaskType.EVENT.getCommandName();
            if (!hasParameter(EVENT_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(type.getCommandName()));
                return true;
            }
            if (!hasArgument(EVENT_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(type.getCommandName()));
                return true;
            }
            break;
        case DEADLINE:
            assert type.getCommandName() == TaskType.DEADLINE.getCommandName();
            if (!hasParameter(DEADLINE_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(type.getCommandName()));
                return true;
            }
            if (!hasArgument(DEADLINE_PARAMETER)) {
                setErroneousResult(new JukeMissingArgumentException(type.getCommandName()));
                return true;
            }
            break;
        default:
        }
        return false;
    }

    /**
     * Returns if there are unnecessary parameters.
     *
     * @return Boolean result.
     */
    private boolean hasUnnecessaryParameters() {
        for (String param : paramArgs.keySet()) {
            if (isDefaultParameter(param)) {
                continue;
            }
            switch (type) {
            case EVENT:
                if (!param.equals(EVENT_PARAMETER)) {
                    setErroneousResult(new JukeInvalidParameterException(param));
                    return true;
                }
                break;
            case DEADLINE:
                if (!param.equals(DEADLINE_PARAMETER)) {
                    setErroneousResult(new JukeInvalidParameterException(param));
                    return true;
                }
                break;
            default:
                setErroneousResult(new JukeInvalidParameterException(param));
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to execute the command, updating the result.
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
        assert isEmpty();
        try {
            addTaskToList();
        } catch (JukeParseException e) {
            setErroneousResult(e);
            return this;
        }
        juke.getStorage().saveTasks();
        return this;
    }

    /**
     * Adds the task to the list.
     *
     * @throws JukeParseException Throws if eror with parsing.
     */
    private void addTaskToList() throws JukeParseException {
        switch (type) {
        case TODO:
            juke.getTaskList().add(new Todo(getDefaultArgument()));
            break;
        case EVENT:
            juke.getTaskList().add(new Event(getDefaultArgument(),
                getArgument(EVENT_PARAMETER)));
            break;
        case DEADLINE:
            juke.getTaskList().add(new Deadline(getDefaultArgument(),
                getArgument(DEADLINE_PARAMETER)));
            break;
        default:
        }
        setSuccessfulResult(String.format(SUCCESS_MESSAGE,
            type.getCommandName(), getDefaultArgument()));
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

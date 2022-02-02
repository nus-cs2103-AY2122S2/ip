package chatbot.command;

import chatbot.task.TaskList;

/**
 * Command interface to execute user input.
 */
public abstract class Command {
    private final String trigger;

    /**
     * Constructs a command with a specified trigger.
     *
     * @param trigger the command trigger
     */
    public Command(String trigger) {
        this.trigger = trigger;
    }

    public String getTrigger() {
        return this.trigger;
    }

    /**
     * Executes the command with the specified arguments onto the specified task list.
     *
     * @param input    the arguments of the command
     * @param taskList the task list to execute the command on
     * @return returns the output of the command
     */
    public abstract CommandOutput execute(String[] input, TaskList taskList);
}
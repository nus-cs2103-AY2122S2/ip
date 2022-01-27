package chatbot.command;

import chatbot.task.TaskList;

/**
 * Command interface to execute user input.
 */
public interface Command {
    /**
     * Executes the command with the specified arguments onto the specified task list.
     * @param input the arguments of the command
     * @param taskList the task list to execute the command on
     * @return returns the output of the command
     */
    CommandOutput execute(String[] input, TaskList taskList);
}
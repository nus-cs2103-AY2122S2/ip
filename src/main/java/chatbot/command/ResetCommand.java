package chatbot.command;

import chatbot.task.TaskList;

public class ResetCommand implements Command {
    public static final String KEYWORD = "reset";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments.\nReset does not accept any arguments.", false);
        }

        taskList.clear();
        return new CommandOutput("All tasks removed from the task list.", true);
    }
}
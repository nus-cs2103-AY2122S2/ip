package chatbot.command;

import chatbot.task.TaskList;

public class ResetCommand implements Command {
    public static final String KEYWORD = "reset";
    public static final String FORMAT = "Command format: \"" + KEYWORD;

    @Override public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + FORMAT, "/audio/ding.wav");
        }

        taskList.clear();
        return new CommandOutput("All tasks removed from the task list.", "/audio/ding.wav");
    }
}
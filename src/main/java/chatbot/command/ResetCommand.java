package chatbot.command;

import chatbot.task.Task;

import java.util.ArrayList;

public class ResetCommand implements Command {
    public static final String KEYWORD = "reset";

    @Override
    public CommandOutput execute(String[] input, ArrayList<Task> tasks) {
        if (input.length > 1) {
            return new CommandOutput("To reset the tasks, enter \"reset\" without any additional parameters.", false);
        }

        tasks.clear();
        return new CommandOutput("All tasks removed from the task list.", true);
    }
}
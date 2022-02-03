package chatbot.command;

import chatbot.task.TaskList;

public class ResetCommand extends Command {
    private static final String TRIGGER = "reset";
    private static final String FORMAT = "Command format: \"" + TRIGGER;

    public ResetCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + FORMAT, "/audio/notification.wav");
        }

        taskList.clear();
        return new CommandOutput("All tasks removed from the task list.", "/audio/notification.wav");
    }
}
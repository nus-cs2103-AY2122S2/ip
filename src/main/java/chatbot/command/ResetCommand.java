package chatbot.command;

import chatbot.task.TaskList;

public class ResetCommand extends Command {
    public static final String TRIGGER = "reset";
    public static final String FORMAT = TRIGGER;

    public ResetCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + "Command format: " + FORMAT, "/audio/wav/notification.wav");
        }

        taskList.clear();
        return new CommandOutput("All tasks removed from the task list.", "/audio/wav/notification.wav");
    }
}
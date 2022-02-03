package chatbot.command;

import chatbot.task.TaskList;

public class TerminateCommand extends Command {
    private static final String TRIGGER = "bye";
    private static final String FORMAT = "Command format: \"" + TRIGGER;

    public TerminateCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + FORMAT, "/audio/notification.wav");
        }
        return new CommandOutput("Bye. Hope to see you again soon!", "/audio/notification.wav", true);
    }
}

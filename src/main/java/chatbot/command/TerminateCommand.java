package chatbot.command;

import chatbot.task.TaskList;

public class TerminateCommand extends Command {
    public static final String TRIGGER = "bye";
    public static final String FORMAT = TRIGGER;

    public TerminateCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }
        return new CommandOutput("Bye bye! I will see you again, won't I?", "/audio/wav/notification.wav", true);
    }
}

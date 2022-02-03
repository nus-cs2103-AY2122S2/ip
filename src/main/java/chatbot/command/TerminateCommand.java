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
            return new CommandOutput("Error: Invalid arguments\n" + "Command format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }
        return new CommandOutput("Bye. Hope to see you again soon!", "/audio/wav/notification.wav", true);
    }
}

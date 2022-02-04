package chatbot.command;

import chatbot.task.TaskList;

public class HelloCommand extends Command {
    public static final String TRIGGER = "hello";
    public static final String FORMAT = TRIGGER;

    public HelloCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }
        return new CommandOutput(
                "Hey there!\nType \"help\" for a list of commands.",
                "/audio/wav/notification.wav");
    }
}

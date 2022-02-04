package chatbot.command;

import chatbot.task.TaskList;

public class OwoCommand extends Command {
    public static final String TRIGGER = "owo";
    public static final String FORMAT = TRIGGER;

    public OwoCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + "Command format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }
        return new CommandOutput("(。O⁄ ⁄ω⁄ ⁄ O。)", "/audio/wav/notification.wav");
    }
}

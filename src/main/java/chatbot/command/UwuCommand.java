package chatbot.command;

import chatbot.task.TaskList;

public class UwuCommand extends Command {
    public static final String TRIGGER = "uwu";
    public static final String FORMAT = TRIGGER;

    public UwuCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\n" + "Command format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }
        return new CommandOutput("(⁄˘⁄ ⁄ ω⁄ ⁄ ˘⁄)♡", "/audio/wav/notification.wav");
    }
}

package chatbot.command;

import chatbot.sfx.Sfx;
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
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT, Sfx.SFX_ERROR_INVALID_ARGS);
        }
        return new CommandOutput("(⁄˘⁄ ⁄ ω⁄ ⁄ ˘⁄)♡", Sfx.SFX_COMMAND_UWU);
    }
}

package chatbot.command;

import chatbot.task.TaskList;

public class HelpCommand extends Command {
    public static final String TRIGGER = "help";
    public static final String FORMAT = TRIGGER;

    public HelpCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length > 1) {
            return new CommandOutput("Error: Invalid arguments\nCommand format: " + FORMAT,
                    "/audio/wav/notification.wav");
        }

        String output = "Commands:\n";
        output += HelpCommand.TRIGGER + ": list commands\n";

        output += ToDoCommand.TRIGGER + ": add to-do\n";
        output += DeadlineCommand.TRIGGER + ": add deadline\n";
        output += EventCommand.TRIGGER + ": add event\n";

        output += MarkCommand.TRIGGER + ": mark task as done\n";
        output += UnmarkCommand.TRIGGER + ": mark task as not done\n";

        output += ListCommand.TRIGGER + ": list current tasks\n";
        output += FindCommand.TRIGGER + ": find task via keyword search\n";
        output += DeleteCommand.TRIGGER + ": delete task\n";
        output += ResetCommand.TRIGGER + ": clear task list\n";

        output += UwuCommand.TRIGGER + ": UwU";
        output += OwoCommand.TRIGGER + ": OwO";

        output += TerminateCommand.TRIGGER + ": terminate program";

        return new CommandOutput(output, "/audio/wav/notification.wav");
    }
}

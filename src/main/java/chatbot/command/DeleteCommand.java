package chatbot.command;

import chatbot.sfx.Sfx;
import chatbot.task.Task;
import chatbot.task.TaskList;

public class DeleteCommand extends Command {
    public static final String TRIGGER = "delete";
    public static final String FORMAT = TRIGGER + " <index>\n" + "Index range: 1 to <size of list>";

    public DeleteCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.remove(index - 1);
            return new CommandOutput(String.format("Okay. I've removed this task:\n  %s", task),
                    Sfx.SFX_COMMAND_DELETE);
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\nCommand format: " + FORMAT, Sfx.SFX_ERROR_INVALID_INDEX);
        }
    }
}

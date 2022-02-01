package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class DeleteCommand extends Command {
    private static final String TRIGGER = "delete";
    private static final String FORMAT =
            "Command format: \"" + TRIGGER + " <index>\"\n" + "Index range: 1 to <size of list>";

    public DeleteCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.remove(index - 1);
            return new CommandOutput(String.format("Noted. I've removed this task:\n  %s", task), "/audio/ding.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + FORMAT, "/audio/ding.wav");
        }
    }
}

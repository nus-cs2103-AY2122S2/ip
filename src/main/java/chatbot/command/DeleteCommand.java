package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class DeleteCommand implements Command {
    public static final String KEYWORD = "delete";
    public static final String FORMAT =
            "Command format: \"" + KEYWORD + " <index>\"\n" + "Index range: 1 to <size of list>";

    @Override public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.remove(index - 1);
            return new CommandOutput(String.format("Noted. I've removed this task:\n  %s", task), "/audio/ding.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + FORMAT, "/audio/ding.wav");
        }
    }
}

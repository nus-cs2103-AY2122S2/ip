package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class MarkCommand implements Command {
    public static final String KEYWORD = "mark";
    public static final String FORMAT =
            "Command format: \"" + KEYWORD + " <index>\"\n" + "Index range: 1 to <size of list>";

    @Override public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(true);
            return new CommandOutput(String.format("Nice! I've marked this task as done:\n  %s", taskList),
                    "/audio/ding.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + FORMAT, "/audio/ding.wav");
        }
    }
}

package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class UnmarkCommand implements Command {
    public static final String TRIGGER = "unmark";
    public static final String FORMAT =
            "Command format: \"" + TRIGGER + " <index>\"\n" + "Index range: 1 to <size of list>";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(false);
            return new CommandOutput(String.format("OK, I've marked this task as not done yet:\n  %s", task),
                    "/audio/ding.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + FORMAT, "/audio/ding.wav");
        }
    }
}

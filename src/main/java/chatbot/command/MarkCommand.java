package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class MarkCommand extends Command {
    private static final String TRIGGER = "mark";
    private static final String FORMAT =
            "Command format: \"" + TRIGGER + " <index>\"\n" + "Index range: 1 to <size of list>";

    public MarkCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(true);
            return new CommandOutput(String.format("Nice! I've marked this task as done:\n  %s", task),
                    "/audio/ding.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + FORMAT, "/audio/ding.wav");
        }
    }
}

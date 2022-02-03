package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

public class UnmarkCommand extends Command {
    public static final String TRIGGER = "unmark";
    public static final String FORMAT =
            TRIGGER + " <index>\n" + "Index range: 1 to <size of list>";

    public UnmarkCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = taskList.get(index - 1);
            task.setDone(false);
            return new CommandOutput(String.format("OK, I've marked this task as not done yet:\n  %s", task),
                    "/audio/wav/notification.wav");
        } catch (Exception e) {
            return new CommandOutput("Error: Invalid index\n" + "Command format: " + FORMAT, "/audio/wav/notification.wav");
        }
    }
}

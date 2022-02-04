package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

import java.util.Arrays;

public class FindCommand extends Command {
    public static final String TRIGGER = "find";
    public static final String FORMAT = TRIGGER + " <keyword>";

    public FindCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length < 2) {
            return new CommandOutput("Error: Blank keyword\nCommand format: " + FORMAT, "/audio/wav/notification.wav");
        }

        String keyword = String.join(" ", Arrays.asList(input).subList(1, input.length));
        Task[] tasks = taskList.find(keyword);

        // No tasks found
        if (tasks.length == 0) {
            return new CommandOutput("Sorry! I couldn't find anything...", "/audio/wav/notification.wav");
        }

        // Tasks found
        StringBuilder output = new StringBuilder("Here's what I found:\n");
        for (int i = 0; i < tasks.length; ++i) {
            output.append(i + 1).append(". ").append(tasks[i].toString());
        }
        return new CommandOutput(output.toString(), "/audio/wav/notification.wav");
    }
}

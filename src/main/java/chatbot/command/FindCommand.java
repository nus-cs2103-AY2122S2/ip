package chatbot.command;

import chatbot.task.Task;
import chatbot.task.TaskList;

import java.util.Arrays;

public class FindCommand extends Command {
    private static final String TRIGGER = "find";
    private static final String FORMAT = "Command format: \"" + TRIGGER + " <keyword>\n";

    public FindCommand() {
        super(TRIGGER);
    }

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        if (input.length < 2) {
            return new CommandOutput("Error: Blank keyword\n" + FORMAT, "/audio/ding.wav");
        }

        String keyword = String.join(" ", Arrays.asList(input).subList(1, input.length));
        Task[] tasks = taskList.find(keyword);

        // No tasks found
        if (tasks.length == 0) {
            return new CommandOutput("No matching tasks found.", "/audio/ding.wav");
        }

        // Tasks found
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.length; ++i) {
            output.append(i + 1).append(". ").append(tasks[i].toString());
        }
        return new CommandOutput(output.toString(), "/audio/ding.wav");
    }
}

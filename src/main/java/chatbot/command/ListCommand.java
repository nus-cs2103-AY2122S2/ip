package chatbot.command;

import chatbot.task.TaskList;

public class ListCommand implements Command {
    public static final String KEYWORD = "list";

    @Override
    public CommandOutput execute(String[] input, TaskList taskList) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            output.append(
                    String.format("%d. %s%s", i + 1, taskList.get(i).toString(), i < taskList.size() - 1 ? "\n" : ""));
        }
        return new CommandOutput(output.toString(), "/audio/ding.wav");
    }
}
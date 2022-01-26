package chatbot.command;

import chatbot.task.TaskList;

public interface Command {
    CommandOutput execute(String[] input, TaskList taskList);
}
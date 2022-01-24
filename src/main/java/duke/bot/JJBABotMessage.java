package duke.bot;

import duke.task.Task;
import duke.task.TaskList;

public class JJBABotMessage extends BotMessage {
    @Override
    public String getListMessage(boolean isEmpty) {
        return isEmpty ? "There are no task available." : "Here are the task in your list:";
    }

    @Override
    public String getInvalidCommandMessage() {
        return "Invalid duke.command.Command!";
    }

    @Override
    public String getAddMessage(TaskList taskList, Task task) {
        return "duke.task.Task added: \n   " + task + "\n" + getTaskLeft(taskList);
    }

    @Override
    public String getDeleteMessage(TaskList taskList, Task task) {
        return "duke.task.Task removed!\n   " + task;
    }

    @Override
    public String getMarkMessage(Task task) {
        return "Good job on completing your task!\n   " + task.toString();
    }

    @Override
    public String getUnmarkMessage(Task task) {
        return "Done, remember to do your task.\n   " + task.toString();
    }

    @Override
    public String getExitMessage() {
        return "Goodbye! Thanks for using JJBA Bot!";
    }

    @Override
    public String getBotMessage() {
        return "What can I do for you?";
    }

    private String getTaskLeft(TaskList taskList) {
        return String.format("You have %s task%s in your list.",
                (taskList.size() > 0) ? taskList.size() : "no", (taskList.size() <= 1) ? "" : "s");
    }
}

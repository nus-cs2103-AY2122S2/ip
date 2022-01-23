public abstract class BotMessage {
    public abstract String getListMessage(boolean isEmpty);

    public abstract String getInvalidCommandMessage();

    public abstract String getAddMessage(TaskList taskList, Task task);

    public abstract String getDeleteMessage(TaskList taskList, Task task);

    public abstract String getMarkMessage(Task task);

    public abstract String getUnmarkMessage(Task task);

    public abstract String getExitMessage();

    public abstract String getBotMessage();
}


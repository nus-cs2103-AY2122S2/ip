package auxiliary;

import tasks.TaskList;

public abstract class TaskManagerChatbot extends Chatbot{
    protected TaskList taskList;
    protected TaskManagerChatbot(TaskList taskList) {
        this.taskList = taskList;
    }
}

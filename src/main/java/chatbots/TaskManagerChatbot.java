package chatbots;

import instructions.InstructionHandler;
import tasks.TaskList;

/**
 * This class is a specialised Chatbot that manages tasks.
 *
 * @author Ong Han Yang
 */
public abstract class TaskManagerChatbot extends Chatbot {
    /** The taskList that the chatbot is responsible for */
    protected TaskList taskList;

    /** The instruction handler object that this chatbot holds */
    protected InstructionHandler instHandler;

    /** Constructs a Task Manager Chatbot */
    protected TaskManagerChatbot(TaskList taskList) {
        this.taskList = taskList;
        instHandler = InstructionHandler.of(taskList);
    }

    /**
     * Sets the taskList to a custom one.
     *
     * @param taskList the taskList to use instead.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}

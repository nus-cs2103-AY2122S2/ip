package duke.chatbot.command;

import java.util.ArrayList;

import duke.data.TaskList;
import duke.task.Task;


/**
 * Command which updates the done status of
 * a Task, either marking it as done or undone.
 */
public class MarkTaskCommand extends TaskListCommand {
    /**
     * Enum to represent whether the command should
     * mark task as done or undone.
     */
    public enum MarkType {
        DONE,
        UNDONE
    }

    /** Mark type for command, either done or undone */
    private final MarkType markType;

    /**
     * Returns a command which marks a task as done or undone.
     *
     * @param name Name of command
     * @param args Index of task to mark.
     * @param markType MarkType of DONE or UNDONE to mark task.
     * @param taskList TaskList maintained by ChatBot.
     */
    public MarkTaskCommand(String name, String args, MarkType markType, TaskList taskList) {
        super(name, args, taskList);
        this.markType = markType;
    }

    /**
     * Marks a given task in taskList as done or undone,
     * using index passed by user.
     *
     * @return ArrayList containing description of task marked.
     * @throws IllegalArgumentException If the index is invalid.
     */
    @Override
    public ArrayList<String> execute() throws IllegalArgumentException {
        // Args for this command represents index of task to mark
        int taskIndex = getTaskIndex();

        TaskList taskList = this.getTaskList();
        String feedback;
        Task markedTask;
        switch (this.markType) {
        case DONE:
            markedTask = taskList.checkTask(taskIndex);
            feedback = "Congrats! This task was marked as done:";
            break;
        case UNDONE:
            markedTask = taskList.uncheckTask(taskIndex);
            feedback = "Okay. The following task was marked undone:";
            break;
        default:
            throw new IllegalArgumentException("Invalid arguments to mark task");
        }

        ArrayList<String> response = new ArrayList<>();
        response.add(feedback);
        response.add(markedTask.getDescription());
        return response;
    }

    /**
     * Reverses the marking of current task.
     */
    @Override
    public void undo() throws IllegalArgumentException {
        TaskList taskList = super.getTaskList();
        int taskIndex = this.getTaskIndex();

        if (this.markType == MarkType.DONE) {
            // Reverses previously marking of task as done
            taskList.uncheckTask(taskIndex);
        } else {
            taskList.checkTask(taskIndex);
        }
    }
}

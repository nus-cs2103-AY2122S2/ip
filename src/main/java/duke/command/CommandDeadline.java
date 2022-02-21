package duke.command;

import java.time.LocalDate;

import duke.Deadline;
import duke.Response;
import duke.TaskList;

/**
 * Encapsulates the Deadline command.
 */
public class CommandDeadline extends Command {
    private final String deadlineContent;
    private final LocalDate date;
    private final TaskList taskList;
    private String response;

    /**
     * Contructor for CommandDeadline.
     * @param taskList List of tasks to modify after this command.
     * @param deadlineContent Description of the task to be added.
     * @param date Due date of the task to be added.
     */
    public CommandDeadline(TaskList taskList, String deadlineContent, LocalDate date) {
        assert taskList != null;
        assert deadlineContent != null;
        assert date != null;
        this.deadlineContent = deadlineContent;
        this.date = date;
        this.taskList = taskList;
    }

    /**
     * Appends the Deadline task to the taskList and return message that it is successfully added.
     */
    @Override
    public void execute() {
        Deadline newTask = new Deadline(deadlineContent, date);
        taskList.addTask(newTask);
        response = Response.RESPONSE_ADDED + "\n" + newTask + "\n" + Response.taskNo(taskList.size());
    }

    @Override
    public String getResponse() {
        return response;
    }
}

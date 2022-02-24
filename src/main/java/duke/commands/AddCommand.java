package duke.commands;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import java.time.LocalDateTime;

/**
 * Command that adds a Task to the TaskList.
 */

public class AddCommand extends Command{

    private String taskType;
    private String content;
    private LocalDateTime datetime;

    /**
     * Constructor for AddCommand.
     *
     * @param taskType string representing subclass of Task to add.
     * @param content description of Task to add.
     * @param datetime date and time of task to add.
     */
    public AddCommand(String taskType, String content, LocalDateTime datetime) {
        this.taskType = taskType;
        this.content = content;
        this.datetime = datetime;
    }

    /**
     * Creates respective Task depending on taskType which is then added to the TaskList.
     *
     * @return Message for completing the command which is displayed to user.
     */
    @Override
    public String execute() {
        Task newTask = null;
        switch (taskType.toLowerCase()) {
        case "todo":
            newTask = new Todo(content);
            break;
        case "deadline":
            newTask = new Deadline(content, datetime);
            break;
        case "event":
            newTask = new Event(content, datetime);
            break;
        }
        taskList.addTask(newTask);
        return String.format("This task has been added as requested:\n" +
                "%s\n" + "You now have %d item(s) in your list", newTask, taskList.size());
    }
}
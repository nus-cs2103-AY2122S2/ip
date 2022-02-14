package duke.commands;

import java.io.IOException;

import duke.data.exception.IllegalValueException;
import duke.data.exception.InvalidParameterException;
import duke.data.task.DeadlineTask;
import duke.data.task.EventTask;
import duke.data.task.Task;

/**
 * Adds a task to the task list
 */
public class AddCommand extends Command {
    protected String description;
    protected String deadline;
    protected String tagName;
    protected CommandType type;

    /**
     * Constructs an add deadline or add event command.
     *
     * @param description description of the task to be created.
     * @param deadline deadline of the task to be created.
     * @param type type of the task to be created.
     */
    public AddCommand(String description, String deadline, String tagName, String type) {
        this.description = description;
        this.deadline = deadline;
        this.tagName = tagName;
        this.type = CommandType.valueOf(type);
    }

    /**
     * Constructs an add todo command.
     *
     * @param description description of the task to be created.
     */
    public AddCommand(String description, String tagName) {
        this.description = description;
        this.tagName = tagName;
        this.type = CommandType.TODO;
    }

    /**
     * Executes the add command.
     *
     * @return response from the addition of the task.
     * @throws InvalidParameterException if the parameter supplied is invalid.
     * @throws IOException if the task cannot be added to the storage.
     */
    public String execute() throws InvalidParameterException, IOException, IllegalValueException {
        Task task;
        String taskToString;

        if (type.getCommand().equals("todo")) {
            if (this.description == "") {
                throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
            }
            task = super.taskList.addTodoTask(description, tagName);
            taskToString = String.format("%s|T|0|%s|%s%n", task.getId(), task.getDescription(), task.getTagName());
        } else if (type.getCommand().equals("deadline")) {
            if (this.description == "" || this.deadline == "") {
                throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
            }
            task = super.taskList.addDeadlineTask(description, deadline, tagName);
            taskToString = String.format("%s|D|0|%s|%s|%s%n", task.getId(),
                    task.getDescription(), ((DeadlineTask) task).getDeadline(), task.getTagName());
        } else if (type.getCommand().equals("event")) {
            if (this.description == "" || this.deadline == "") {
                throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
            }
            task = super.taskList.addEventTask(description, deadline, tagName);
            taskToString = String.format("%s|E|0|%s|%s|%s%n", task.getId(),
                    task.getDescription(), ((EventTask) task).getDeadline(), task.getTagName());
        } else {
            throw new InvalidParameterException("Invalid parameter provided");
        }

        super.storage.appendToFile(taskToString);

        String additionResponse = String.format("Got it. I've added this task:%n%s%n"
                + "Now you have %d tasks in the list.%n", task, super.taskList.getSize());

        return additionResponse;
    }
}

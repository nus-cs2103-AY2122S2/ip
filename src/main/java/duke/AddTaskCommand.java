package main.java.duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    private String description;
    private String type;
    private String toDoBy;

    /**
     * Constructs an instance of Add Task Command.
     *
     * @param description Description of task.
     * @param type Type of task, either ToDo, Deadline or Event.
     */
    public AddTaskCommand(String description, String type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Constructs an instance of Add Task Command.
     *
     * @param description Description of task.
     * @param type Type of task, either ToDo, Deadline or Event.
     * @param toDoBy Deadline which task must be completed by.
     */
    public AddTaskCommand(String description, String type, String toDoBy) {
        this.description = description;
        this.type = type;
        this.toDoBy = toDoBy;
    }

    /**
     * Executes the instance of Add Task Command.
     *
     * @param tasks Contains the task list.
     * @param ui Deals with interaction with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        try {
            if (type.equals("todo")) {
                tasks.addTask(new ToDo(description));
            } else if (type.equals("deadline")) {
                tasks.addTask(new Deadline(description, toDoBy));
            } else {
                tasks.addTask(new Event(description, toDoBy));
            }
            ui.showSucessfulAdd(tasks.getTask(tasks.size() - 1), tasks.size());
        } catch (DateTimeParseException e) {
            ui.showError("DateTimeParseException");
        } try {
            storage.writeToFile(tasks.getTaskArr());
        } catch (IOException e) {
            ui.showError("IOException");
        }
    }

    /**
     * Checks whether this command is the terminating command to Duke.
     *
     * @return False.
     */
    @Override
    public boolean isEnd() {
        return false;
    }

}

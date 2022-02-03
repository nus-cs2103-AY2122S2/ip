package src.main.java.duke.command;

import src.main.java.duke.DukeException;
import src.main.java.duke.Storage;
import src.main.java.duke.Ui;
import src.main.java.duke.task.*;
import src.main.java.duke.TaskList;

/**
 * AddCommand is a Command that adds a task that is either a ToDo task, Deadline
 * task or Event task to the program.
 */

public class AddCommand extends Command {
    private String description;
    private String type;
    private Task task;

    /**
     * Constructor for AddCommand which takes in the type of task and description of
     * task that is to be added.
     * 
     * @param type        type of task to be added
     * @param description description of task to be added
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Adds the task to TaskList, updates the storage file and notifies the user
     * when it's done
     * 
     * @param tasks   task list local to user
     * @param ui      ui instance local to user
     * @param storage storage instance local to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description);
                break;
            case "E":
                task = new Event(description);
                break;
            default:
                break;
        }

        tasks.add(task);
        storage.updateAfterAdd(task);
        ui.addMessage(task, tasks.getNumberOfTasks());
    }

    /**
     * Checks if this is an exit command, and only returns true for an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

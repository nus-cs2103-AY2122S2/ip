package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class which handles saving of task to text in data
 */
public class ByeCommand extends Command {
    private TaskList tasks;

    /**
     * Method to get the modified task list after command execution
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * Method to see if command ends the main program loop
     *
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return true;
    }

    /**
     * Method to execute the bye command
     * Prints bye message and saves the tasks created from the program loop to a text file
     *
     * @param tasks   tasks list to be modified
     * @param ui      to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        try {
            storage.saveFile(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        return "Bye. Hope to see you again soon!";
    }
}

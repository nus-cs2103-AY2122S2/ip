package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command{
    private TaskList tasks;
    private int index;
    private String type;
    private String update;

    /**
     * Constructor for Update Command class
     * Creates correct Task based on user input and adds to task list
     * @param input detail to update and updated detail
     */
    public UpdateCommand(String input) {
        String[] processedInput = input.split("/");
        assert processedInput.length == 3;
        index = Integer.parseInt(processedInput[0])-1;
        type = processedInput[1];
        update = processedInput[2];
    }

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
        return false;
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
        Task toUpdate = tasks.get(index);
        switch (type) {
        case "detail":
            toUpdate.updateDetail(update);
            break;
        case "time":
            toUpdate.updateDate(update);
            break;
        }
        this.tasks.set(index, toUpdate);
        storage.saveFile(this.tasks);
        return ui.updateMessage();
    }
}

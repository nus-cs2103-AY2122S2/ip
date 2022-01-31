package duke;

public class FindCommand extends Command {

    private String taskToFind = null;

    /**
     * Constructor for the command.
     * @param taskToFind the task to find.
     */
    FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    /**
     *
     * @param taskList the taskList of the current user.
     * @param ui the user interface helper for the program.
     * @param storage the storage helper for the program.
     */
    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.find(taskToFind));
    }
}

/**
 * ListCommand class is a Command that contains instructions
 * to run when user wants to list out every task.
 */
public class ListCommand extends Command {

    /**
     * Passes the string to print to ui class
     * to print out every entry of the list if any.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showEmptyList();
        } else {
            StringBuilder taskStrings = new StringBuilder();
            for (int n = 0; n < tasks.size(); n++) {
                int temp = n + 1;
                String currTaskString = temp + "." + tasks.getString(n) + "\n";
                taskStrings.append(Ui.addPrefix(currTaskString));
            }
            ui.showList(taskStrings.toString());
        }
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

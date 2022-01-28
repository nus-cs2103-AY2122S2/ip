package duke;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Extends Command class
 * When executed, calls ui method to print output
 */
public class FindCommand extends Command {
    LocalDate inputDate;

    /**
     * Constructor
     * Takes in Java LocalDate as input
     * @param inputDate LocalDate
     */
    public FindCommand(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * Handles the finding of a corresponding task in tasklist that matches input date
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String output from ui
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        ArrayList<Task> all = tasklist.getAllTasks();
        ArrayList<Task> filtered = new ArrayList<Task>();
        for (Task t : all) {
            if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                if (deadline.getDate().equals(inputDate.toString())) {
                    filtered.add(t);
                }
            }
        }
        if (filtered.size() == 0) {
            return ui.printFilteredDeadline(0);
        } else {
            TaskList filteredTasklist = new TaskList(filtered);
            // do nothing to storage
            return ui.printFilteredDeadline(filteredTasklist);
        }
    }

}

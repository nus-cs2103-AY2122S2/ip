package duke;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Extends Command class
 * When executed, calls ui method to print output
 */
public class FindCommand extends Command {
    LocalDate input_date;

    /**
     * Constructor
     * Takes in Java LocalDate as input
     * @param input_date LocalDate
     */
    public FindCommand(LocalDate input_date) {
        this.input_date = input_date;
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
                if (deadline.getDate().equals(this.input_date.toString())) {
                    filtered.add(t);
                }
            }
        }
        if (filtered.size() == 0) {
            return ui.printFilteredDeadline(0);
        } else {
            TaskList filtered_tasklist = new TaskList(filtered);
            // do nothing to storage
            return ui.printFilteredDeadline(filtered_tasklist);
        }
    }

}

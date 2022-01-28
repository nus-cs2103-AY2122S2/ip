package duke;
import exceptions.DukeException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Extends Command class
 * When executed, calls ui method to print output
 */
public class FindCommand extends Command {

    String keyword;

    /**
     * Constructor
     * Takes in Java String as keyword input
     * @param keyword LocalDate
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Handles the finding of a corresponding task in tasklist that matches input date
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String output from ui
     * @throws DukeException
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> all = tasklist.getAllTasks();
        ArrayList<Task> filtered = new ArrayList<Task>();
        for (Task t : all) {
            if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                if (deadline.getDate().equals(keyword)) {
                    filtered.add(t);
                } else {
                    String deadlineName = deadline.getName();
                    String[] splitName = deadlineName.split(" ");
                    for (String s : splitName) {
                        if (s.equals(keyword)) {
                            filtered.add(t);
                        }
                    }
                }

            } else if (t instanceof ToDo) {
                ToDo todo = (ToDo) t;
                String todoName = todo.getName();
                String[] splitName = todoName.split(" ");
                for (String s : splitName) {
                    if (s.equals(keyword)) {
                        filtered.add(t);
                    }
                }
            } else if (t instanceof Event) {
                Event event = (Event) t;
                String venue = event.getVenue();
                String name = event.getName();
                String[] splitVenue = venue.split(" ");
                String[] splitName = name.split(" ");
                int length = splitName.length + splitVenue.length;
                String[] merged = new String[length];
                int pointer = 0;
                for (String s : splitVenue) {
                    merged[pointer] = s;
                    pointer++;
                }
                for (String s : splitName) {
                    merged[pointer] = s;
                    pointer++;
                }

                for (String s : merged) {
                    if (s.equals(keyword)) {
                        filtered.add(t);
                    }
                }


            } else {
                throw new DukeException("I'm not sure what happened! Please try again later or call 999...");
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

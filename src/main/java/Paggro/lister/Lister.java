package paggro.lister;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import paggro.notableDate.NotableDate;
import paggro.task.Task;

/**
 * This class implements a Lister object containing a list of tasks.
 */
public class Lister {
    /** The list of tasks in the Lister object */
    public ArrayList<Task> tasks;
    /** A HashMap mapping LocalDates with a NotableDate object */
    public HashMap<LocalDate, NotableDate> dateMap;

    /**
     * Default constructor of Lister object when Storage is empty.
     */
    public Lister() {
        tasks = new ArrayList<>();
        dateMap = new HashMap<>();
    }

    /**
     * Constructor of Lister when there are tasks to load from Storage.
     * @param tasks ArrayList of tasks from storage.
     * @param dateMap HashMap loaded from storage.
     */
    public Lister(ArrayList<Task> tasks, HashMap<LocalDate, NotableDate> dateMap) {
        this.tasks = tasks;
        this.dateMap = dateMap;
    }

    /**
     * Adds the given task to the tasks list.
     * @param task Task that is given as input.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at index i of the list as done.
     * @param i The index of the list to be marked.
     */
    public void mark(int i) {
        Task task = tasks.get(i - 1);
        task.setDone();
    }

    /**
     * Unmarks the task at index i of the list as done.
     * @param i The index of the list to be unmarked.
     */
    public void unmark(int i) {
        Task task = tasks.get(i - 1);
        task.setUndone();
    }

    /**
     * Deletes the task at index i of the list.
     * @param i The index of the list to be deleted.
     */
    public void delete(int i) {
        tasks.remove(i - 1);
    }

    /**
     * Checks if the given LocalDate has already been noted.
     * @param lDate The LocalDate in question.
     * @return The mapped NotableDate if it has already been stored, else a newly initialised NotableDate.
     */
    public NotableDate checkDate(LocalDate lDate) {
        NotableDate nDate;
        if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
            nDate = new NotableDate(lDate);
            dateMap.put(lDate, nDate);
        } else  {
            nDate = dateMap.get(lDate);
        }
        return nDate;
    }
}

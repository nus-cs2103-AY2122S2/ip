package johnny;

import java.time.LocalDate;
import java.util.ArrayList;

public class InputList {

    private ArrayList<Task> tasks;

    public InputList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Writes the current list of tasks to
     * the text file
     *
     * @param store  Storage object.
     */
    public void writeToFile(Storage store) {
        store.writeTasks(this.tasks);
    }

    /**
     * Prints the current list of tasks
     *
     */

    public String getList() {
        String output = "";
        for(int i = 0; i < tasks.size(); i++) {
            output += String.valueOf(i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Adds new task to the list
     *
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public String checkForClash(LocalDate date) {
        String out = "";
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i) instanceof Todo) {
                continue;
            } else {
                if(tasks.get(i) instanceof Deadline &&
                        ((Deadline) tasks.get(i)).getDueDate().equals(date)) {
                    out += String.valueOf(i + 1) + ". " +
                            tasks.get(i).toString() + "\n";
                } else if(tasks.get(i) instanceof Event
                        && ((Event) tasks.get(i)).getEventDate().equals(date)) {
                    out += String.valueOf(i + 1) + ". " +
                            tasks.get(i).toString() + "\n";
                }
            }
        }
        return out;
    }

    /**
     * Deletes task at a particular index from
     * the list
     *
     * @param index  Index of task to delete
     */
    public void delete(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Marks task at a particular index from
     * the list as done
     *
     * @param index  Index of task to mark as done
     */
    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Unmarks task at a particular index from
     * the list as not done yet
     *
     * @param index  Index of task to unmark
     */
    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
    }

    public String searchEvent(String searchString) {
        String out = "";
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(searchString)) {
                out += String.valueOf(i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return out;
    }

    /**
     * Returns number of tasks in list
     *
     * @return Number of tasks in list
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public int getCount() {
        return tasks.size();
    }
}

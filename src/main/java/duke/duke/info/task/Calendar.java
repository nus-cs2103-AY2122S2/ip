package duke.info.task;

import duke.utils.Text;

import java.util.ArrayList;

public class Calendar {

    private final ArrayList<Task> calendar;

    /**
     * Constructs a Calendar object with an empty ArrayList in the calendar
     */
    public Calendar() {
        this.calendar = new ArrayList<Task>();
    }

    /**
     * Constructs a Calendar object with the specified calendar in the input.
     * @param calendar - the ArrayList of tasks to construct the Calendar
     */
    public Calendar(ArrayList<Task> calendar) {
        this.calendar = calendar;
    }

    /**
     * Adds the task specified in taskToAdd to the ArrayList in calendar
     * @param taskToAdd - the Task to be added to the Calendar
     */
    public void add(Task taskToAdd) {
        calendar.add(taskToAdd);
    }

    /**
     * Removes the task at the index specified by indexToRemove from the ArrayList
     * in calendar. The index is converted from 0-indexing in the ArrayList to a
     * natural 1-indexing
     * @param indexToRemove - the index of the task to be removed
     */
    public void remove(int indexToRemove) {
        calendar.remove(indexToRemove - 1);
    }

    /**
     * Marks the task at the index specified by indexToMark as complete from the
     * ArrayList in calendar. The index is converted from 0-indexing in the ArrayList to
     * a natural 1-indexing
     * @param indexToMark - the index of the task to be marked as complete
     */
    public void markAsDone(int indexToMark) {
        calendar.get(indexToMark - 1).complete();
    }

    /**
     * Marks the task at the index specified by indexToMark as incomplete from the
     * ArrayList in calendar. The index is converted from 0-indexing in the ArrayList to
     * a natural 1-indexing
     * @param indexToMark - the index of the task to be marked as incomplete
     */
    public void markAsNotDone(int indexToMark) {
        calendar.get(indexToMark - 1).incomplete();
    }

    /**
     * Returns the number of tasks currently in the calendar
     * @return - the number of tasks currently in the calendar
     */
    public int numOfEntries() {
        return this.calendar.size();
    }

    /**
     * Returns the String representation of the task at the specified index
     * @param index - the index of the task to be retrieved
     * @return - the string representation of the task at index
     */
    public String taskStringAtIndex(int index) {
        return this.calendar.get(index - 1).toString();
    }

    /**
     * Returns a new Calendar with the tasks matching the specified keyword.
     * The method iterates through the calendar and adds all tasks matching the specified
     * keyword to a new Calendar. Each task is matched using the Task matchesKeyword method
     * @param keyword - the keyword to be matched
     * @return - a Calendar with all the tasks that match the given keyword
     */
    public Calendar tasksMatchingKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: this.calendar) {
            if (task.matchesKeyword(keyword)) {
                result.add(task);
            }
        }
        return new Calendar(result);
    }

    /**
     * Returns a save format representation of the task. The save format consists of a list
     * of the Calendar's tasks in the order that they are returned by an iterator. Adjacent
     * elements are separated by a line. Elements are converted to the appropriate save format
     * using the Task saveFormat method
     * @return - the save format representation of the calendar
     */
    public String saveFormat() {
        String product = "";
        for (int i = 0; i < this.calendar.size() - 1; i++) {
            product += this.calendar.get(i).saveFormat();
            product += "\n";
        }
        product += this.calendar.get(this.calendar.size() - 1).saveFormat();
        return product;
    }

    /**
     * Returns a string representation of this Calendar. The string representation consists
     * of a list of the Calendar's tasks in the order that they are returned by an iterator.
     * Adjacent elements are separated by a line and 1-indexed. Elements are converted to
     * strings using the Task toString method.
     * @return - the string representation of the calendar
     */
    @Override
    public String toString() {
        if (calendar.size() == 0) {
            return Text.TEXT_EMPTY_LIST;
        } else {
            String product = "";
            for (int i = 0; i < this.calendar.size() - 1; i++) {
                product += String.format("%s: %s\n", i + 1, this.calendar.get(i).toString());
            }
            product += String.format("%s: %s", this.calendar.size(), this.calendar.get(this.calendar.size() - 1).toString());
            return product;
        }
    }
}

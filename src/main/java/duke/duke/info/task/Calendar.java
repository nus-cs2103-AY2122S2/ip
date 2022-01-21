package duke.info.task;

import duke.utils.Text;

import java.util.ArrayList;

public class Calendar {

    private final ArrayList<Task> calendar;

    public Calendar() {
        this.calendar = new ArrayList<Task>();
    }

    public Calendar(ArrayList<Task> storage) {
        this.calendar = storage;
    }

    public void add(Task taskToAdd) {
        calendar.add(taskToAdd);
    }

    public void remove(int indexToRemove) {
        calendar.remove(indexToRemove - 1);
    }

    public void markAsDone(int indexToMark) {
        calendar.get(indexToMark - 1).complete();
    }

    public void markAsNotDone(int indexToMark) {
        calendar.get(indexToMark - 1).incomplete();
    }

    public int numOfEntries() {
        return this.calendar.size();
    }

    public String taskAtIndex(int index) {
        return this.calendar.get(index - 1).toString();
    }

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

package component;

import java.util.ArrayList;
import java.util.Iterator;

import tasks.Tasks;

/**
 * A class that belongs to DukeComponent Package.
 * This class encapsulates the logic of Task List in Duke.
 */
public class TaskList implements Iterable<Tasks> {
    private final ArrayList<Tasks> ls;

    /**
     * Constructor for TaskList.
     * @param ls Arraylist that contains the Tasks in Duke.
     */
    public TaskList(ArrayList<Tasks> ls) {
        this.ls = ls;
    }

    /**
     * Getter method for TaskList.
     * @param i 0-based index for users to get the task from the TaskList.
     * @return Task from the TaskList.
     */
    public Tasks get(int i) {
        return ls.get(i);
    }

    /**
     * Adds the Task into the TaskList.
     * @param t Task that is added to the TaskList.
     */
    public void add(Tasks t) {
        ls.add(t);
    }

    /**
     * Removes the Task at index i from the TaskList
     * @param i 0-based index indicating position of Task in the TaskList.
     * @return Task that is removed.
     */
    public Tasks remove(int i) {
        return ls.remove(i);
    }

    /**
     * Size of TaskList.
     * @return Integer representing size of TaskList.
     */
    public int size() {
        return ls.size();
    }

    /**
     * Prints out a list of Task in that TaskList that matches the description.
     * @param description  Description of the Task.
     */
    public void find(String description) {
        int count = 1;
        for (Tasks t : ls) {
            if (t.getTask().contains(description)) {
                System.out.println(count + "." + t);
                count++;
            }
        }
    }

    /**
     * Iterator method to allow for-each loop of TaskList.
     * @return An iterator for Tasks.
     */
    @Override
    public Iterator<Tasks> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;
            private final int currentSize = ls.size();

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && ls.get(currentIndex) != null;
            }

            @Override
            public Tasks next() {
                return ls.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }
}

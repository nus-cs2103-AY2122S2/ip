package component;

import java.util.ArrayList;
import java.util.Iterator;

import tasks.Tasks;

/**
 * A class that belongs to DukeComponent Package.
 * This class encapsulates the logic of Task List in Duke.
 */
public class TaskList implements Iterable<Tasks> {
    private final ArrayList<Tasks> list;

    /**
     * Constructs TaskList.
     * @param list Arraylist that contains the Tasks in Duke.
     */
    public TaskList(ArrayList<Tasks> list) {
        this.list = list;
    }

    /**
     * Gets TaskList.
     * @param index 0-based index for users to get the task from the TaskList.
     * @return Task from the TaskList.
     */
    public Tasks getTask(int index) {
        return list.get(index);
    }

    /**
     * Adds the task into the TaskList.
     * @param task task that is added to the TaskList.
     */
    public void addTask(Tasks task) {
        list.add(task);
    }

    /**
     * Removes the Task at index index from the TaskList
     * @param index 0-based index indicating position of Task in the TaskList.
     * @return Task that is removed.
     */
    public Tasks remove(int index) {
        return list.remove(index);
    }

    /**
     * Returns the size of TaskList.
     * @return Integer representing size of TaskList.
     */
    public int listSize() {
        return list.size();
    }

    /**
     * Prints out a list of Task in that TaskList that matches the description.
     * @param description  Description of the Task.
     */
    public String findDescription(String description) {
        int count = 1;
        StringBuilder dukeReply = createDukeReply(description, count);
        return dukeReply.toString();
    }

    /**
     * Creates Duke reply.
     * @param description Description of the task to find.
     * @param count The number of tasks that matches the description.
     * @return Duke reply to find command.
     */
    private StringBuilder createDukeReply(String description, int count) {
        StringBuilder dukeReply = new StringBuilder();
        for (Tasks task : list) {
            if (task.getTask().contains(description)) {
                dukeReply.append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        return dukeReply;
    }

    /**
     * Iterates TaskList.
     * @return An iterator for Tasks.
     */
    @Override
    public Iterator<Tasks> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;
            private final int currentSize = list.size();

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && list.get(currentIndex) != null;
            }

            @Override
            public Tasks next() {
                return list.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }
}

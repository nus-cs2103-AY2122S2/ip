package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a task list from pre-existing data.
     *
     * @param fileName A string containing the file path to the existing data.
     * @throws IOException if an I/O error occurs.
     */
    public TaskList(String fileName) throws IOException {
        this.list = TaskList.populateList(fileName);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Fetches the task at the given index.
     *
     * @param idx An integer representing the index of the task to get.
     * @return A Task at the given index of the task list.
     */
    public Task get(int idx) {
        return list.get(idx);
    }

    /**
     * Fetches the number of tasks in the list.
     *
     * @return An integer representing the number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes the task at the given index from the task list.
     *
     * @param taskNum An integer representing the index of the task to be removed.
     * @return the Task that is removed.
     */
    public Task remove(int taskNum) {
        assert taskNum < this.size() && taskNum > 0 : "Please provide a valid index";
        return list.remove(taskNum);
    }

    /**
     * Replaces a task at the given index with a new task.
     *
     * @param idx An integer representing the index of the task to be replaced.
     * @param task the Task to replace the existing Task with.
     * @return the Task that was replaced.
     */
    public Task set(int idx, Task task) {
        assert task != null : "A proper task should be set!";
        return list.set(idx, task);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param task the Task to be added to the task list.
     * @return true if the task has been added.
     */
    public boolean add(Task task) {
        assert task != null : "A proper task should be added!";
        return list.add(task);
    }

    /**
     * Fetches the whole task list.
     *
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Populates the task list with pre-existing data.
     *
     * @param fileName A string representing the file path to the saved tasks.
     * @return an ArrayList of the saved tasks.
     * @throws IOException if an I/O error occurs.
     */
    public static ArrayList<Task> populateList(String fileName) throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while (line != null) {
            assert line.length() >= 8 : "Please enter a valid file path";
            String taskType = String.valueOf(line.charAt(1));
            assert taskType.equals("T") || taskType.equals("E") || taskType.equals("D") :
                    "Please enter a valid file path";
            boolean isMarked = String.valueOf(line.charAt(4)).equals("X");
            if (taskType.equals("T")) {
                Task task = new Todo(line.substring(7));
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else if (taskType.equals("D")) {
                String tempDescription = line.split("by: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("by: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                Task task = new Event(description, LocalDate.parse(timeBy, formatter).toString());
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            } else {
                String tempDescription = line.split("at: ")[0].substring(7);
                int tempDescLength = tempDescription.length();
                String description = tempDescription.substring(0, tempDescLength - 2);
                String tempTimeBy = line.split("at: ")[1];
                int endIdx = tempTimeBy.lastIndexOf(")");
                String timeBy = tempTimeBy.substring(0, endIdx);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                Task task = new Event(description, LocalDate.parse(timeBy, formatter).toString());
                if (isMarked) {
                    task = task.mark();
                }
                list.add(task);
            }
            line = file.readLine();
        }
        file.close();
        return list;
    }
}

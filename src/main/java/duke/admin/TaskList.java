package duke.admin;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * TaskList class manages the task list and the actions that can be performed on
 * the task list.
 */
public class TaskList {
    private static String buffer = " xxx ";
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList that takes in data from the storage file and
     * constructs a task list from there.
     * @param tasksArr data read from the storage file
     * @throws DukeException exception when data is corrupted or task list cannot be
     *                       created as intended
     */
    public TaskList(String[] tasksArr) throws DukeException {
        try {
            tasks = new ArrayList<>();

            for (String task : tasksArr) {
                String[] taskDetails = task.split(buffer);

                String type = taskDetails[0];
                Boolean isMarked = (Integer.parseInt(taskDetails[1]) > 0);
                String description = taskDetails[2];

                switch (type) {
                case "T":
                    tasks.add(new ToDo(description, isMarked));
                    break;
                case "D":
                    tasks.add(new Deadline(description, isMarked));
                    break;
                case "E":
                    tasks.add(new Event(description, isMarked));
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * Constructor for TaskList when storage file data is unavailable.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the task specified to the task list.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task indexed by the index specified.
     * @param index the index of the task that is to be deleted
     * @return the task after it is deleted from the task list
     */
    public Task delete(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);

        return deletedTask;
    }

    /**
     * Prints out all the tasks in the task list and their index.
     * @return task list as a string
     */
    public String list() {
        String listString = "";
        
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String listElement = i + "." + task.toString();

            listString = listString + listElement + System.lineSeparator();
        }

        return listString;
    }
    
    /**
     * Prints out all the tasks in the task list that contains the keyword.
     * @param keyword keyword to be contained by the tasks
     * @return result as a string
     */
    public String find(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskAsString = task.toString();
            if (taskAsString.contains(keyword)) {
                searchResults.add(task);
            }
        }

        String searchResultString = "";

        for (int j = 1; j <= searchResults.size(); j++) {
            Task matchedTask = searchResults.get(j - 1);
            String result = j + ". " + matchedTask.toString();
            searchResultString = searchResultString + result + System.lineSeparator();
        }

        return searchResultString;
    }
    /**
     * Marks the task indexed by the index as done.
     * @param index the index of the task that is to be marked as done
     * @return the task after it is marked as done
     */
    public Task mark(int index) {
        Task indexedTask = tasks.get(index);
        indexedTask.mark();

        return indexedTask;
    }

    /**
     * Marks the task indexed by the index as not yet done.
     * @param index the index of the task that is to be marked as not yet done
     * @return the task after it is marked as not yet done
     */
    public Task unmark(int index) {
        Task indexedTask = tasks.get(index);
        indexedTask.unmark();

        return indexedTask;
    }

    /**
     * Returns the number of tasks in the task list.
     * @return number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
}

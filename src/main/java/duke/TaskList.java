package src.main.java.duke;

import java.util.ArrayList;

import src.main.java.duke.task.Deadline;
import src.main.java.duke.task.Event;
import src.main.java.duke.task.Task;
import src.main.java.duke.task.ToDo;

/**
 * TaskList class manages the task list and the actions that can be performed on
 * the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static String buffer = " xxx ";

    /**
     * Constructor for TaskList that takes in data from the storage file and
     * constructs a task list from there.
     * 
     * @param data data read from the storage file
     * @throws DukeException exception when data is corrupted or task list cannot be
     *                       created as intended
     */
    public TaskList(String[] data) throws DukeException {
        try {
            tasks = new ArrayList<>();

            for (String task : data) {
                String[] splitTask = task.split(buffer);

                String type = splitTask[0];
                Boolean marked = (Integer.parseInt(splitTask[1]) > 0);
                String description = splitTask[2];

                switch (type) {
                    case "T":
                        tasks.add(new ToDo(description, marked));
                        break;
                    case "D":
                        tasks.add(new Deadline(description, marked));
                        break;
                    case "E":
                        tasks.add(new Event(description, marked));
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
     * add method takes in a task as input and adds the task to the task list.
     * 
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * delete method takes in an index and deletes the task indexed by the index.
     * 
     * @param idx the index of the task that is to be deleted
     * @return the task after it is deleted from the task list
     */
    public Task delete(int idx) {
        Task temp = tasks.get(idx);
        tasks.remove(idx);

        return temp;
    }

    /**
     * list method prints out all the tasks in the task list and their index.
     */
    public void list() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    /**
     * mark method takes in an index and marks the task indexed by the index as
     * done.
     * 
     * @param idx the index of the task that is to be marked as done
     * @return the task after it is marked as done
     */
    public Task mark(int idx) {
        tasks.get(idx).mark();

        return tasks.get(idx);
    }

    /**
     * unmark method takes in an index and marks the task indexed by the index as
     * not yet done.
     * 
     * @param idx the index of the task that is to be marked as not yet done
     * @return the task after it is marked as not yet done
     */
    public Task unmark(int idx) {
        tasks.get(idx).unmark();

        return tasks.get(idx);
    }

    /**
     * length method returns the size of the task list.
     * 
     * @return number of tasks in the task list
     */
    public int length() {
        return tasks.size();
    }
}

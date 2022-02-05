package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An ArrayList container for {@code Task} object
 */
public class TaskList {
    private static Ui ui;
    private final DukeException dukeException;
    public ArrayList<Task> taskList;


    /**
     * Default Constructor for {@code TaskList}
     * This constructor will be used when there is no previous TaskList to load from.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        dukeException = new DukeException();
        ui = new Ui();
    }

    /**
     * Default Constructor for {@code TaskList}
     * This constructor will be used when there is an existing taskList to load from.
     *
     * @param taskList ArrayList of {@code Task}
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        dukeException = new DukeException();
        ui = new Ui();
    }

    /**
     * Returns ArrayList of current tasks.
     *
     * @return An ArrayList of task
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Get the size of current {@code taskList}
     *
     * @return Integer value of {@code taskList} size.
     */
    public int getSize() {
        return taskList.size();
    }


    /**
     * Add task to taskList.
     *
     * @param task Task to be added to the taskList
     */
    public void addTask(Task task) {
        taskList.add(task);
        ui.addTask(task);
        ui.displayTaskAmount(taskList);
    }

    /**
     * Given a Task Index, Set Task to done, and marks the Task.
     *
     * @param index Index of Task
     */
    public void mark(int index) {
        try {
            Task selectedTask = taskList.get(index - 1);
            selectedTask.setDone();
            ui.markTask(selectedTask);
        } catch (Exception e) {
            System.out.println("Task is invalid, please select a valid task number.");
        }
    }

    /**
     * Given a Task Index, Set Task to not done, and unchecks the Task.
     *
     * @param index Index of Task
     */
    public void unmark(int index) {
        try {
            Task selectedTask = taskList.get(index - 1);
            selectedTask.setUndone();
            ui.markTask(selectedTask);
        } catch (Exception e) {
            System.out.println("Task is invalid, please select a valid task number.");
        }
    }

    /**
     * Given a Task Index, Delete the specific Task.
     *
     * @param index Index of Task
     */
    public void delete(int index) {
        try {
            Task removedTask = taskList.remove(index - 1);
            ui.deleteTask(removedTask);
            ui.displayTaskAmount(taskList);
        } catch (NumberFormatException noTaskNumber) {
            dukeException.noTaskNumber();
        } catch (IndexOutOfBoundsException invalidTaskNumber) {
            dukeException.invalidTaskNumber();
        }
    }


    /**
     * Method is used to scan through current TaskList, searching by supplied keyword.
     *
     * @param matchingDescription Description of Task that User is finding
     */
    public void find(String matchingDescription) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<Integer> indexList = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String currentDescription = currentTask.getDescription().toLowerCase();
            matchingDescription = matchingDescription.toLowerCase();
            if (currentDescription.contains(matchingDescription)) {
                matchingTasks.add(currentTask);
                indexList.add(i+1);
            }
        }
        ui.displayFoundTasks(matchingTasks, indexList);
    }
}
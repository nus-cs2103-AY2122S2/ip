package duke;

import duke.controllers.Parser;
import duke.exception.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Hashtable;


public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Hashtable<String, ArrayList<Task>> keywordHt = new Hashtable<>();

    /**
     * Constructor for TodoList. Loads previous save state.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        for (Task task : tasks) {
            addTaskToHt(task);
        }
    }

    public TaskList() {}

    /**
     * Adds a Task to the List
     * @param task task that has been created
     */
    public void addTask(Task task) {
        tasks.add(task);
        addTaskToHt(task);
    }

    private void addTaskToHt(Task task) {
        String[] keywords = Parser.parseHashtable(task.getDescription());
        for (String keyword : keywords) {
            if (keywordHt.containsKey(keyword)) {
                ArrayList<Task> valueList = keywordHt.get(keyword);
                valueList.add(task);
            } else {
                ArrayList<Task> valueList = new ArrayList<>();
                valueList.add(task);
                keywordHt.put(keyword, valueList);
            }
        }
    }

    private void removeTaskFromHt(Task task) {
        String[] keywords = Parser.parseHashtable(task.getDescription());
        for (String keyword : keywords) {
            ArrayList<Task> valueList = keywordHt.get(keyword);
            valueList.remove(task);
        }
    }

    private void validateKeyword(String keyword) throws DukeException {
        if (!keywordHt.containsKey(keyword)) {
            throw new DukeException("No Search Results found");
        }
    }

    public String searchKeyword(String keyword) throws DukeException {
        validateKeyword(keyword);
        String response = "";
        for (Task task : keywordHt.get(keyword)) {
            response = response + task + "\n";
        }
        return response;
    }


    /**
     * Removes an task form the todosList
     * @param index Index of task in the list
     * @throws DukeException if removed task is out of index range
     * @return removed Task
     */
    public Task removeTask(int index) throws DukeException {
        validateIndex(index);
        Task removedTask = tasks.remove(index - 1);
        removeTaskFromHt(removedTask);
        return removedTask;
    }

    public void validateIndex(int index) throws DukeException {
        if (index - 1 > tasks.size() || index < 0) {
            throw new DukeException("Index out of range!!");
        }
    }

    /**
     * gets all tasks in the todosList
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    //TODO throw exception for out of index
    /**
     * Mark a task on the list as completed
     * @param index Index of Task to be unmarked as completed
     */
    public String mark(int index) {
        Task task = tasks.get(index - 1);
        String response;
        if (task.getIsDone()) {
            response = "Task already completed!";
        } else {
            task.done();
            response = ("Nice! I've marked this task as done:\n" + task);
        }
        return response;
    }

    //TODO throw exception for out of index
    /**
     * unmark a task on the list as completed
     * @param index Index of Task to be unmarked
     */
    public String unmark(int index) {
        Task task = tasks.get(index - 1);
        String response;
        if (!task.getIsDone()) {
            response = "Task not yet completed!";
        } else {
            task.notDone();
            response = ("Oh no! one more thing that needs to be done:\n" + task);
        }
        return response;
    }

}

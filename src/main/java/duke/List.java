package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import duke.exception.ListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

/**
 * Represents a list of the tasks added by the user.
 */
public class List {

    private ArrayList<Task> tasks;

    public List() {
        this.tasks = new ArrayList<>();
    }

    public List(ArrayList<Task> list) {
        this.tasks = list;
    }


    /**
     * This method returns the arraylist of Tasks.
     *
     * @return returns arraylist of Tasks.
     */
    public ArrayList<Task> getArrayList() {
        return this.tasks;
    }

    /**
     * Marks a task at a specific index in the list as done.
     *
     * @param index Index of the task to be marked done in the array list of list.
     */
    public void markDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
    }

    /**
     * Marks a task at a specific index in the list as not done.
     *
     * @param index Index of the task to be marked as not done in the array list of list.
     */
    public void unmarkDone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
    }

    /**
     * Adds a Task to the list, used for event task or todo task.
     *
     * @param taskType TaskType of task being added.
     * @param description Description of task.
     * @param timing Date/Time for event task.
     */
    //CODESTYLE.OFF: "MissingSwitchDefault"
    public void add(TaskType taskType, String description, LocalDateTime timing) {
        switch (taskType) {
        case TODO:
            ToDo toDo = new ToDo(description);
            tasks.add(toDo);
            break;
        case EVENT:
            Event event = new Event(description, timing);
            tasks.add(event);
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(description, timing);
            tasks.add(deadline);
            break;
        }
    }
    //CODESTYLE.OFF: "MissingSwitchDefault"
    /**
     * Deletes a task from the list and returns it.
     *
     * @param index Index of the task in the list.
     * @return Returns the task deleted.
     * @throws ListException If the index is out of bound.
     */
    public Task delete(int index) throws ListException {
        if (index < 1 || index > tasks.size()) {
            throw new ListException("Sorry. The task you indicated cannot be found.\n"
                    + "Please enter a new task.");
        } else {
            return tasks.remove(index - 1);
        }
    }

    public Task getLast() {
        return tasks.get(tasks.size() - 1);
    }
    /**
     * Finds a set of tasks that matches the description.
     *
     * @param findDescription Description of the tasks to find.
     * @return Returns a list of the tasks found.
     */
    public List findTask(String findDescription) {
        ArrayList<Task> findTaskList = new ArrayList<>();
        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.contains(findDescription)) {
                findTaskList.add(task);
            }
        }
        return new List(findTaskList);
    }
    /**
     * Sorts the tasks in the task list by their task type
     * and sorts deadline tasks and event tasks by their local date time.
     */
    public void sort() {
        Collections.sort(this.getArrayList());
    }
    /**
     * Returns a String representation of the List in the desired format.
     *
     * @return Returns a string which displays the list of tasks in order of them being added.
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            str = str + index + ". " + this.tasks.get(i).toString() + "\n";
        }
        return str;
    }
}

package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.ListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasks;
import duke.task.ToDo;

/**
 * Represents a list of the tasks added by the user.
 */
public class List {

    private ArrayList<Task> arrayList;

    public List() {
        this.arrayList = new ArrayList<>();
    }

    public List(ArrayList<Task> list) {
        this.arrayList = list;
    }


    /**
     * This method returns the arraylist of Tasks.
     *
     * @return returns arraylist of Tasks.
     */
    public ArrayList<Task> getArrayList() {
        return this.arrayList;
    }

    /**
     * Marks a task at a specific index in the list as done.
     *
     * @param index Index of the task to be marked done in the array list of list.
     */
    public void markDone(int index) {
        Task task = arrayList.get(index);
        task.markDone();
    }

    /**
     * Marks a task at a specific index in the list as not done.
     *
     * @param index Index of the task to be marked as not done in the array list of list.
     */
    public void unmarkDone(int index) {
        Task task = arrayList.get(index);
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
    public void add(Tasks taskType, String description, String timing) {
        switch (taskType) {
        case TODO:
            ToDo toDo = new ToDo(description);
            arrayList.add(toDo);
            break;
        case EVENT:
            Event event = new Event(description, timing);
            arrayList.add(event);
            break;
        }
    }
    //CODESTYLE.OFF: "MissingSwitchDefault"

    /**
     * Adds a Task to the list, used for deadline task.
     *
     * @param taskType TaskType of Task.
     * @param description Description of Task.
     * @param date Date of Task is due.
     */
    public void add(Tasks taskType, String description, LocalDateTime date) {
        Deadline deadline = new Deadline(description, date);
        arrayList.add(deadline);
    }

    /**
     * Deletes a task from the list and returns it.
     *
     * @param index Index of the task in the list.
     * @return Returns the task deleted
     * @throws ListException If the index is out of bound.
     */
    public Task delete(int index) throws ListException {
        if (index < 1 || index > arrayList.size()) {
            throw new ListException("Sorry. The task you indicated cannot be found.\n"
                    + "Please enter a new task.");
        } else {
            return arrayList.remove(index - 1);
        }
    }

    public Task getLast() {
        return arrayList.get(arrayList.size() - 1);
    }

    /**
     * Find a set of tasks that matches the description.
     *
     * @param findDescription Description of the tasks to find.
     * @return Returns a list of the tasks found.
     */
    public List findTask(String findDescription) {
        ArrayList<Task> findTaskList = new ArrayList<>();
        for (Task task : arrayList) {
            String description = task.getDescription();
            if (description.contains(findDescription)) {
                findTaskList.add(task);
            }
        }
        return new List(findTaskList);
    }

    /**
     * Returns a String representation of the List in the desired format.
     *
     * @return Returns a string which displays the list of tasks in order of them being added.
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.arrayList.size(); i++) {
            int index = i + 1;
            str = str + index + ". " + this.arrayList.get(i).toString() + "\n";
        }
        return str;
    }
}

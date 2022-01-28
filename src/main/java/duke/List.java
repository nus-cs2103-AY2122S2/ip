package duke;

import duke.exception.ListException;
import duke.task.*;

import java.time.LocalDateTime;
import java.util.ArrayList;



/**
 * This class encapsulates a list of Tasks input from the user which is stored in an Array List.
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
     * This method marks a task at a specific index in the list as done.
     *
     * @param index index of the task to be marked done in the array list of list
     */
    public void markDone(int index) {
        Task task = arrayList.get(index);
        task.markDone();
    }

    /**
     * This method marks a task at a specific index in the list as not done.
     *
     * @param index index of the task to be marked as not done in the array list of list.
     */
    public void unmarkDone(int index) {
        Task task = arrayList.get(index);
        task.unmarkDone();
    }

    /**
     * This method adds a todo task, deadline task or event task to the list.
     *
     * @param taskType    Enum Tasks
     * @param description Description of task
     * @param timing      date/time for Deadline and event task
     */
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

    public void add(Tasks taskType, String description, LocalDateTime date) {
        Deadline deadline = new Deadline(description, date);
        arrayList.add(deadline);
    }

    /**
     * This method deletes a task from the list.
     *
     * @param index index of the task in the list.
     * @return returns the task deleted
     * @throws ListException catches an exception when the index is out of bound.
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

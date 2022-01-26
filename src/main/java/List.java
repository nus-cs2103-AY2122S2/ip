import java.util.*;
import java.time.LocalDateTime;

/**
 * This class encapsulates a list of Tasks input from the user which is stored in an Array List.
 */
public class List {
    private ArrayList<Task> arrayList;//Array List to store the tasks.
    private final int maxSize;//the maximum number of tasks possible, defined by requirement.

    /**
     *
     * @param size: The maximum number of tasks possible which corresponds to maxSize.
     */
    public List(int size) {
        this.maxSize = size;
        this.arrayList = new ArrayList<Task>(maxSize);
    }

    /**
     * This method returns the arraylist of Tasks.
     * @return returns arraylist of Tasks.
     */
    public ArrayList<Task> getArrayList() {
        return this.arrayList;
    }

    /**
     * This method marks a task at a specific index in the list as done.
     * @param index index of the task to be marked done in the array list of list
     * @return return the task that is marked as done.
     */
    public Task markDone(int index) {
        Task task = arrayList.get(index);
        task.markDone();
        return task;
    }

    /**
     * This method marks a task at a specific index in the list as not done.
     * @param index index of the task to be marked as not done in the array list of list.
     * @return return the task that is marked as not done.
     */
    public Task unmarkDone(int index) {
        Task task = arrayList.get(index);
        task.unmarkDone();
        return task;
    }

    /**
     *This method adds a todo task, deadline task or event task to the list.
     * @param taskType Enum Tasks
     * @param description Description of task
     * @param timing date/time for Deadline and event task
     * @return return a todo task, deadline task or event task that is added to the list
     */
    public Task add(Tasks taskType, String description, String timing) throws DukeException {
        switch (taskType) {
            case TODO:
                ToDo toDo = new ToDo(description);
                arrayList.add(toDo);
                return toDo;
            case EVENT:
                Event event = new Event(description, timing);
                arrayList.add(event);
                return event;
        }
        return new ToDo(description);
    }

    public Task add(Tasks taskType, String description, LocalDateTime date) throws DukeException {
        Deadline deadline = new Deadline(description, date);
        arrayList.add(deadline);
        return deadline;
    }

    /**
     * This method deletes a task from the list.
     * @param index index of the task in the list.
     * @return returns the task deleted
     * @throws ListException catches an exception when the index is out of bound.
     */
    public Task delete(int index) throws ListException{
        if (index < 1 || index > arrayList.size()) {
            throw new ListException("Sorry. The task you indicated cannot be found.\n" +
                                    "Please enter a new task.");
        } else {
            return arrayList.remove(index - 1);
        }
    }


    /**
     *
     * @return Returns a string which displays the list of tasks in order of them being added.
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < arrayList.size(); i++) {
            int index = i + 1;
            str = str + index + ". " + arrayList.get(i).toString() + "\n";
        }
        return str;
    }
}

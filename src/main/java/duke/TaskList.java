package duke;

import java.util.ArrayList;
import java.util.List;

import duke.taskobjects.Task;

/**
 * TaskList object that is just a container for the task list
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Default constructor for TaskList
     *
     * Takes in no argument and creates an empty task list
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Alternative constructor for TaskList
     *
     * Takes in an {@code ArrayList<Task>} and sets it as a task list
     * @param taskList The task list that is this new instance wraps around
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code Task} to the task list
     * @param task Provided Task that is added to the task list
     * @return Returns true is addition is successful, else false
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Removes a {@code Task} from the task list based on the index
     * @param taskNo Index of the task to be removed
     * @return The {@code Task} that was removed
     */
    public Task remove(int taskNo) {
        return taskList.remove(taskNo);
    }

    /**
     * Get function to get a {@code Task} from the task list
     * @param taskNo Index of the task to be retrieved
     * @return The retrieved {@code Task}
     */
    public Task get(int taskNo) {
        return taskList.get(taskNo);
    }

    /**
     * Returns the contained task list. Terrible function (will remove soon)
     * @return The contained task list
     */
    public List<Task> getList() { // GET RID OF THIS IN THE FUTURE
        return taskList;
    }

    /**
     * Returns the number of elements in the task list
     * @return Number of elements in the task list in int
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a String which describes and lists all the elements in the task list
     * @return The String representation of all the items in the task list
     */
    public String listAll() {
        StringBuilder newString = new StringBuilder("Tasklist:\n");
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                newString.append("\n");
            }
            newString.append(i + 1);
            newString.append(". ");
            newString.append(taskList.get(i).getCurrentStatus());
        }
        return newString.toString();
    }

    public String search(String searchQuery) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getTaskName().contains(searchQuery)) {
                newString.append(i + 1).append(". ").append(currentTask.getCurrentStatus()).append("\n");
            }
        }
        return newString.toString();
    }

//    public boolean markAsDone(int taskNo) {
//        Task currentTask = taskList.get(taskNo);
//        currentTask.markAsDone();
//        return true;
//    }
//
//    public boolean markAsUndone(int taskNo) {
//        Task currentTask = taskList.get(taskNo);
//        currentTask.markAsUndone();
//        return true;
//    }
}

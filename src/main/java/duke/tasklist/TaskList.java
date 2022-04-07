package duke.tasklist;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.task.Task;

/**
 * Represents a List of Task.
 */
public class TaskList {

    private ArrayList<Task> taskStore;

    /**
     * Creates TaskList with
     * an ArrayList of Task stored.
     *
     * @param taskDatabase ArrayList of Task stored.
     */
    public TaskList(ArrayList<Task> taskDatabase) {
        this.taskStore = taskDatabase;
    }

    /**
     * Returns ArrayList of Task stored.
     *
     * @return ArrayList of Task stored within TaskList.
     */
    public ArrayList<Task> getTaskStore() {
        return taskStore;
    }


    /**
     * Mark task within the TaskList as done.
     *
     * @param taskIndex Position of task on the TaskList to be marked as done.
     * @return String statement when task is marked.
     * @throws DukeException If no task at taskIndex.
     */
    public String mark(int taskIndex) throws DukeException {
        checkTaskIndex(taskIndex);
        if (this.taskStore.get(taskIndex).getStatusIcon() == "X") {
            throw new DukeException("Task already Marked");
        }
        assert this.taskStore.get(taskIndex).getStatusIcon() != "X"
                : "No Task at this Index to unmark Assertion error";
        this.taskStore.get(taskIndex).markAsDone();
        return ("Nice! I've marked this task as done:\n" + taskStore.get(taskIndex).toString());
    }

    /**
     * Mark task within the TaskList as not done.
     *
     * @param taskIndex Position of task on the TaskList to be marked as not done.
     * @return String statement when task is unmarked.
     * @throws DukeException If no task at taskIndex.
     */
    public String unMark(int taskIndex) throws DukeException {
        checkTaskIndex(taskIndex);
        if (this.taskStore.get(taskIndex).getStatusIcon() == " ") {
            throw new DukeException("Task already unmarked");
        }
        assert this.taskStore.get(taskIndex).getStatusIcon() != " "
                : "No Task at this Index to unmark Assertion error";
        checkTaskIndex(taskIndex);
        this.taskStore.get(taskIndex).unMark();
        return ("OK, I've marked this task as not done yet:\n" + taskStore.get(taskIndex).toString());
    }

    /**
     * Delete task within the TaskList.
     *
     * @param taskIndex Position of task on the TaskList to delete.
     * @return a String including the deleted task and the number of task in the list now.
     * @throws DukeException If no task at taskIndex.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        checkTaskIndex(taskIndex);
        String deletedTask = this.taskStore.get(taskIndex).toString();
        this.taskStore.remove(taskIndex);
        return "Noted. I've removed this task:\n" + deletedTask
                + String.format("\nNow you have %d tasks in the list", this.getSizeOfTaskList());
    }


    /**
     * Checks if position of task given is valid.
     *
     * @param taskIndex Position of task on the TaskList to check.
     * @throws DukeException If no task at taskIndex.
     */
    private void checkTaskIndex(int taskIndex) throws DukeException {
        if (taskIndex >= this.getSizeOfTaskList() || taskIndex < 0) {
            throw new DukeException("No task at this number, please try again");
        }
    }


    /**
     * Return size of ArrayList within the TaskList.
     *
     * @return Integer representation of size of ArrayList inside TaskList.
     */
    public int getSizeOfTaskList() {
        return taskStore.size();
    }

    /**
     * Prints out all task of the ArrayList if not empty.
     *
     * @return a String representing the task in the list.
     * @throws DukeException If existing ArrayList in TaskList is empty.
     */
    public String printOutTaskList() throws DukeException {
        if (taskStore.size() == 0) {
            throw new EmptyListException("List is empty, please add task");
        }
        String taskListString = "";
        for (int i = 0; i < taskStore.size(); i++) {
            taskListString = taskListString + ((i + 1) + "." + taskStore.get(i).toString() + "\n");
        }
        return taskListString;
    }

    /**
     * Adds new task into ArrayList inside TaskList.
     *
     * @param newTask Task that will be added to the ArrayList of tasks.
     * @return a String of the added task as well as the number of tasks in the list.
     */
    public String addTask(Task newTask) {
        this.taskStore.add(newTask);
        return "Got it. I've added this task:\n  " + newTask.toString()
                + String.format("\nNow you have %d tasks in the list", this.getSizeOfTaskList());
    }

    /**
     * Prints list of tasks which contains matching words.
     *
     * @param searchWord Word to find within task.
     * @return a String representation of the list of tasks matching the searchWord.
     * @throws DukeException If searchWord is empty or tasks with matching words not found.
     */
    public String findTask(String searchWord) throws DukeException {
        ArrayList<Task> searchResult = new ArrayList<>();
        if (searchWord.trim().length() == 0) {
            throw new DukeException("Please input word to find");
        }

        ArrayList<Task> searchList = taskStore
                .stream()
                .filter(t -> t.toString().contains(searchWord))
                .collect(Collectors.toCollection(ArrayList::new));

        if (searchList.size() == 0) {
            throw new EmptyListException("No results found!");
        }

        TaskList searchTaskResult = new TaskList(searchList);
        return searchTaskResult.printOutTaskList();
    }

    /**
     * Updates the specified task in the list with a new description.
     *
     * @param taskIndex Position of task on the TaskList to update.
     * @param newDescription The new description of the task.
     * @return a String including the task with the updated description.
     * @throws DukeException If no task at taskIndex.
     */
    public String updateTask(int taskIndex, String newDescription) throws DukeException {
        checkTaskIndex(taskIndex);
        this.taskStore.get(taskIndex).updateDescription(newDescription);
        return "Got it. I've updated the description for this task:\n  " + this.taskStore.get(taskIndex).toString();
    }
}

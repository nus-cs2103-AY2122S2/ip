package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.task.Task;

import java.util.ArrayList;




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
    // read from file first then pass to Duke.TaskList.Duke.TaskList
    // to finalised in a sense and work with list from here
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
     *
     * @return String statement when task is marked.
     *
     * @throws DukeException If no task at taskIndex.
     */
    public String mark(int taskIndex) throws DukeException {
        checkTaskIndex(taskIndex);
        assert this.taskStore.get(taskIndex).getStatusIcon() != "X"
                : "No Task at this Index to unmark Assertion error";
        this.taskStore.get(taskIndex).markAsDone();
        // System.out.println("Nice! I've marked this task as done:");
        // System.out.println(taskStore.get(taskIndex).toString());
        return ("Nice! I've marked this task as done:\n" + taskStore.get(taskIndex).toString());
    }

    /**
     * Mark task within the TaskList as not done.
     *
     * @param taskIndex Position of task on the TaskList to be marked as not done.
     * @throws DukeException If no task at taskIndex.
     * @return String statement when task is unmarked.
     */
    public String unMark(int taskIndex) throws DukeException {
        // prevents user from unmarking already unmarked task;
        assert this.taskStore.get(taskIndex).getStatusIcon() != " "
                : "No Task at this Index to unmark Assertion error";
        checkTaskIndex(taskIndex);
        this.taskStore.get(taskIndex).unMark();
        return ("OK, I've marked this task as not done yet:\n" + taskStore.get(taskIndex).toString());
        // need to catch error when taskIndex is invalid;
    }

    /**
     * Delete task within the TaskList.
     *
     * @param taskIndex Position of task on the TaskList to delete.
     * @throws DukeException If no task at taskIndex.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        checkTaskIndex(taskIndex);
        taskStore.remove(taskIndex);
        return "Noted. I've removed this task:\n" + taskStore.get(taskIndex).toString()
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
     * Return size of A ja/rrayList within the TaskList.
     *
     * @return Integer representation of size of ArrayList inside TaskList.
     */
    public int getSizeOfTaskList() {
        return taskStore.size();
    }

    /**
     * Prints out all task of the ArrayList if not empty,
     *
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
     */
    public String addTask(Task newTask) {
        this.taskStore.add(newTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  " + newTask.toString());
//        System.out.println(String.format("Now you have %d tasks in the list", this.getSizeOfTaskList()));
        return "Got it. I've added this task:\n  " + newTask.toString()
                + String.format("\nNow you have %d tasks in the list", this.getSizeOfTaskList());
    }

    /**
     * Prints list of tasks which contains matching words.
     *
     * @param searchWord Word to find within task.
     * @throws DukeException If searchWord is empty or tasks with matching words not found.
     */
    public String findTask(String searchWord) throws DukeException {
        ArrayList<Task> searchResult = new ArrayList<>();
        if (searchWord.trim().length() == 0) {
            throw new DukeException("Please input word to find");
        }

        for (int i = 0; i < taskStore.size(); i++) {
            if (taskStore.get(i).toString().contains(searchWord)) {
                searchResult.add(taskStore.get(i));
            }
        }
        if (searchResult.size() == 0) {
            throw new EmptyListException("No results found!");
        }

        TaskList searchTaskResult = new TaskList(searchResult);
        return searchTaskResult.printOutTaskList();
    }
}

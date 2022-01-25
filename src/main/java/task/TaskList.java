package task;

import error.DukeException;
import error.ErrorString;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskLst;

    /**
     * Constructs TaskList object with the list of tasks specified by the user.
     *
     * @param tasks The list containing the user tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskLst = tasks;
    }

    /**
     * Constructs TaskList object.
     */
    public TaskList() {
        this.taskLst = new ArrayList<Task>();
    }

    /**
     *
     * @return List of tasks of the TaskList object.
     */
    public ArrayList<Task> getTaskLst() {
        return taskLst;
    }

    /**
     * Retrieves task based on the specified index i.
     *
     * @param i I index of the task to be retrieved from the list of tasks.
     * @return Retrieved task based on index i.
     * @throws DukeException if i <= 0 or i >= size of taskLst.
     */
    public Task retrieveTask(int i) throws DukeException {
        Task selectedTask = null;
        try {
            selectedTask = this.taskLst.get(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorString.ERROR_INDEX_OUT_OF_BOUND_TASK.toString());
        }

        return selectedTask;
    }

    public ArrayList<Task> findTask(String keyword) {
        int taskLstSize = this.taskLst.size();
        ArrayList<Task> foundTask = new ArrayList<>();
        for (int i=0; i < taskLstSize; i++) {

            Task currTemp = this.taskLst.get(i);
            if (currTemp.toString().contains(keyword)) {
                foundTask.add(currTemp);
            }
        }
        return foundTask;
    }

    /**
     * Adds task into the current list of tasks.
     *
     * @param task Task to be added into the list of tasks.
     */
    public void addToList(Task task) {
        this.taskLst.add(task);
    }

    /**
     * Deletes task based on the specified task index.
     *
     * @param index Index of the task to be deleted from the list of tasks.
     * @return Deleted task based on index i.
     */
    public Task deleteTask(int index) {
        Task toDel = retrieveTask(index);
        this.taskLst.remove(index -1);
        return toDel;
    }

    /**
     * Marks or unmarks task based on specified action and task index.
     *
     * @param action Action to distinguish between mark and unmark.
     * @param taskNum TaskNum the index of the task to be mark or unmark.
     * @return Marked or unmarked task.
     */
    public Task markOrUnmarked(String action, int taskNum) {
        Task selectedTask = retrieveTask(taskNum);

        if (action.equals("mark")) {
            selectedTask.markAsDone();

        } else {
            selectedTask.undoDone();
        }
        return selectedTask;
    }

}

package duke.functionality;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the Tasks of the Duke project. A <code> TaskList </code> object corresponds
 * to the actions available on a Task object.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Default constructor of TaskList class.
     */
    public TaskList() { }

    /**
     * Returns the specified Task that is going to be deleted.
     * @param taskNum an indicator to the index of taskList.
     * @return the specified task to be deleted.
     */
    public Task deleteTask(int taskNum) {
        int actualTaskNum = taskNum - 1;
        Task deletedTask = taskList.remove(actualTaskNum);
        return deletedTask;
    }

    /**
     * Returns the specified Task that is going to be marked as done.
     * @param taskNum an indicator to the index of taskList.
     * @return the specified task to be marked.
     */
    public Task markTask(int taskNum) {
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task markedTask = taskList.get(actualTaskNum); // get the task from the array
        markedTask.setTaskDone();
        return markedTask;
    }

    /**
     * Returns the specified Task that is going to be marked as not done.
     * @param taskNum an indicator to the index of taskList.
     * @return the specified task to be unmarked.
     */
    public Task unMarkTask(int taskNum) {
        int actualTaskNum = taskNum - 1;
        Task unMarkedtask = taskList.get(actualTaskNum); // get the task from the array
        unMarkedtask.setTaskNotDone();
        return unMarkedtask;
    }

    /**
     * Returns a string which is the collection of all the task in taskList.
     * @return accumulated tasks.
     */
    public String printList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < getListSize(); i++) {
            output += i + 1 + "." + taskList.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Returns nothing, but adds the task specified into the taskList.
     * @param task the task created in Parser class.
     */
    public void addToList(Task task) {
        taskList.add(task);
    }

    /**
     * Returns a new TaskList which contains all the Task that matches the specified word.
     * @param word keyword input from user
     * @return a new TaskList containing all Task that contains the specified word.
     */
    public TaskList findWord(String word) {
        TaskList newTaskList = new TaskList();

        for (int i = 0; i < getListSize(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(word)) {
                newTaskList.addToList(task);
            }
        }
        return newTaskList;
    }

    public int getListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}

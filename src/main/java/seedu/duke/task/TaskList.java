package seedu.duke.task;

import seedu.duke.chatbot.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.NoValidTaskIndexException;
import seedu.duke.exceptions.TaskAlreadyMarkedException;

import java.util.ArrayList;

/**
 * TaskList is used to track all tasks taken in by Duke.
 */
public class TaskList {
    /**
     *An array that contains all tasks that will be added.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList with no parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a {@link TaskList} with attribute tasks being specified as a given {@link ArrayList}.
     * @param newTaskList is an {@link ArrayList} of {@link Task}.
     */
    TaskList(ArrayList<Task> newTaskList) {
        this.tasks = newTaskList;
    }

    /**
     * returns the number of tasks in the {@link ArrayList} of {@link Task}.
     * @return an integer specifying the number of tasks
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Used to print all tasks when command "list" is called in Duke.
     */
    public void printTasks() {
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            System.out.println(String
                    .format("%d . %s", i + 1, tasks.get(i).toString()));
        }
    }

    /**
     * Used to preserve immutability.
     * @return {@link ArrayList}, a copy of this
     */
    ArrayList<Task> copyTaskList() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Marks a task as done.
     * @param index where task to be marked is at in TaskList
     * @return TaskList with updated {@link ArrayList}
     */
    public TaskList mark(int index) throws DukeException {
        Task taskToUpdate = this.tasks.get(index);
        if (taskToUpdate.isDone()) {
            throw new TaskAlreadyMarkedException();
        }
        Task newTask = taskToUpdate.changeTaskStatus(true);
        ArrayList<Task> newTasks = this.copyTaskList();
        newTasks.set(index, newTask);
        Ui.showMarkedResult(newTask);
        return new TaskList(newTasks);
    }

    /**
     * Marks a task as undone.
     * @param index where the task to be unmarked is at in TaskList
     * @return TaskList with updated {@link ArrayList}
     */
    public TaskList unmark(int index) throws DukeException {
        if (index > this.getNumberOfTasks() - 1 || index < 0) {
            throw new NoValidTaskIndexException();
        }
        Task taskToUpdate = this.tasks.get(index);
        if (!taskToUpdate.isDone()) { //if task is already unmarked
            throw new TaskAlreadyMarkedException();
        }
        Task newTask = taskToUpdate.changeTaskStatus(false);
        ArrayList<Task> newTasks = this.copyTaskList();
        newTasks.set(index, newTask);
        Ui.showUnmarkedResult(newTask);
        return new TaskList(newTasks);
    }

    /**
     * Adds tasks to TaskList.
     * @param task is the one to be added to TaskList
     * @return new TaskList with increased size to accomodate newTask
     */
    public TaskList add(Task task) {
        //increase size of taskList by 1
        ArrayList<Task> newTasks = this.copyTaskList();
        newTasks.add(task);
        return new TaskList(newTasks);
    }

    /**
     * Deletes task from TaskList.
     * @param index of task to be deleted from TaskList
     * @return new {@link TaskList} with specified task deleted
     */
    public TaskList delete(int index) throws DukeException {
        //increase size of taskList by 1
        if (index > this.getNumberOfTasks() - 1 || index < 0) {
            throw new NoValidTaskIndexException();
        }
        ArrayList<Task> updatedTasks = this.copyTaskList();
        updatedTasks.remove(index);
        return new TaskList(updatedTasks);
    }

    /**
     * Used to search the TaskList to find {@link Task} that contain the search keyword.
     * @param search that contains the search keyword
     * @return a new {@link TaskList} with only {@link Task} that contain the search keyword
     */
    public TaskList find(String search) {
        TaskList newTaskList = new TaskList();
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task currentTask = this.tasks.get(i);
            String taskString = currentTask.toString();
            if (taskString.contains(search)) {
                newTaskList = newTaskList.add(currentTask);
            }
        }
        return newTaskList;
    }
}

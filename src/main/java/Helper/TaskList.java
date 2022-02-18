package helper;

import java.time.LocalDate;
import java.util.ArrayList;
import tasks.Task;

/**
 * <h1>TaskList</h1>
 * <p>
 * TaskList is responsible for maintaining the list of user tasks.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class TaskList {

    // stores the list of tasks.
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * returns an instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * adds the task to the list of user tasks.
     *
     * @param task the task to be added.
     * @return true if added successfully.
     */
    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    /**
     * returns the task at index.
     *
     * @param index the index number of the task.
     * @return Task at index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * removes the task number num.
     *
     * @param num the task number.
     * @return the task that was removed.
     */
    public Task remove(int num) {
        return this.tasks.remove(num);
    }

    /**
     * gets the total number of tasks.
     *
     * @return the number tasks the user has.
     */
    public int numOfTasks() {
        return this.tasks.size();
    }

    /**
     * returns the string representation of tasks without numbering.
     *
     * @return String representation of each task in a line.
     */
    public String getTasks() {
        String content = "";
        for (Task task : this.tasks) {
            content += task.toString() + "\n";
        }
        return content;
    }

    /**
     * checks the tasks that are due on date.
     *
     * @param date the due date.
     * @return a TaskList instance representing the same.
     */
    public TaskList getTasksDueOn(String date) {
        DateHandler.checkValidDate(date);
        TaskList dueTasks = new TaskList();
        for (int i = 0; i < this.numOfTasks(); i++) {
            Task task = this.get(i);
            if (task.isOnDate(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    /**
     * checks the tasks that are due on date.
     *
     * @param date the due date.
     * @return a TaskList instance representing the same.
     */
    public TaskList getTasksDueOn(LocalDate date) {
        TaskList dueTasks = new TaskList();
        for (int i = 0; i < this.numOfTasks(); i++) {
            Task task = this.get(i);
            if (task.isOnDate(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    /**
     * checks the tasks that are due before date.
     *
     * @param date the due date.
     * @return a TaskList instance representing the same.
     */
    public TaskList getTasksDueBefore(String date) {
        DateHandler.checkValidDate(date);
        TaskList dueTasks = new TaskList();
        for (int i = 0; i < this.numOfTasks(); i++) {
            Task task = this.get(i);
            if (task.isBefore(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    /**
     * checks for tasks that contain the word given by user.
     *
     * @param word the word to look out for.
     * @return a TaskList containing those tasks that have word.
     */
    public TaskList getTasksContaining(String word) {
        TaskList tasksContainingWord = new TaskList();
        for (int i = 0; i < this.numOfTasks(); i++) {
            Task task = this.get(i);
            if (task.contains(word)) {
                tasksContainingWord.add(task);
            }
        }
        return tasksContainingWord;
    }

    /**
     * gets the string representation of the class.
     *
     * @return the string representation of the instance.
     */
    @Override
    public String toString() {

        String str = "";
        int taskNum = 1;
        String separator = ".";
        for (Task task: this.tasks) {
            str += (taskNum + separator + task + "\n");
            taskNum += 1;
        }
        return str;
    }

}

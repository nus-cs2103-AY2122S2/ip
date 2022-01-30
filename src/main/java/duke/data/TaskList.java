package duke.data;

import java.util.ArrayList;

import duke.data.task.DeadlineTask;
import duke.data.task.EventTask;
import duke.data.task.Task;
import duke.data.task.TodoTask;

/**
 * Contains a list of tasks, and offers operations on the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs the task list from storage.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a TodoTask to the task list.
     *
     * @param input the content of a todo task.
     * @return the added todo task.
     */
    public Task addTodoTask(String input) {
        TodoTask newTask = new TodoTask(input);
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds a DeadlineTask to the task list.
     *
     * @param input the content of a deadline task.
     * @param deadline the deadline of a deadline task.
     * @return the added deadline task.
     */
    public Task addDeadlineTask(String input, String deadline) {
        DeadlineTask newTask = new DeadlineTask(input, deadline);
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds an EventTask to the task list.
     *
     * @param input the content of an event task.
     * @param deadline the deadline of an event task.
     * @return the added event task.
     */
    public Task addEventTask(String input, String deadline) {
        EventTask newTask = new EventTask(input, deadline);
        taskList.add(newTask);
        return newTask;
    }

    /**
     * Complete a Task.
     *
     * @param taskIndex the index of a task to complete.
     * @return the completed task.
     */
    public Task completeTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markDone();
        return foundTask;
    }

    /**
     * Undo the completion of a task with the given index.
     *
     * @param taskIndex the index of a task to undo completion.
     * @return the task that was undone.
     */
    public Task undoTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markUndone();
        return foundTask;
    }

    /**
     * List all the tasks in the task list.
     *
     * @return the list of tasks as a string.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            String str = String.format("%d. %s%n", i, taskList.get(i - 1));
            sb.append(str);
        }
        return String.valueOf(sb);
    }

    /**
     * Find all tasks matching keyword in the task list.
     *
     * @return the list of all matched tasks as a string.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1).getDescription().contains(keyword)) {
                String str = String.format("%d. %s%n", i, taskList.get(i - 1));
                sb.append(str);
            }
        }
        return String.valueOf(sb);
    }

    /**
     * Deletes a task from the task list with the given index.
     *
     * @param index index of the task to be deleted.
     * @return the deleted task.
     */
    public Task deleteTask(int index) {
        Task foundTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        return foundTask;
    }
}

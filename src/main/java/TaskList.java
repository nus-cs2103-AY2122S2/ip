import java.io.IOException;
import java.util.ArrayList;

/**
 * A list of tasks
 */
public class TaskList {
    private static ArrayList<Task> taskList;
    private Storage storage;
    private static int id;
    public static int size;

    /**
     * Constructs a task list from storage
     */
    public TaskList() {
        this.id = this.size;

        try {
            this.storage = new Storage();
            this.taskList = this.storage.retrieveTasks();
            this.size = taskList.size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints out the response when successfully adding a task.
     *
     * @param task a new task that was added to the task list.
     */
    public void printAddTaskSuccess(Task task) {
        System.out.println("____________________________________________________________");
        System.out.printf("Got it. I've added this task:%n%s%n", task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a TodoTask to the task list.
     *
     * @param input the content of a todo task.
     */
    public void addTodoTask(String input) {
        TodoTask newTask = new TodoTask(input);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);

        String s = String.format("%d|T|0|%s%n", id, input);
        id++;
        try {
            storage.appendToFile(String.valueOf(s));
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    /**
     * Adds a DeadlineTask to the task list.
     *
     * @param input the content of a deadline task.
     * @param deadline the deadline of a deadline task.
     */
    public void addDeadlineTask(String input, String deadline) {
        DeadlineTask newTask = new DeadlineTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);

        String s = String.format("%d|D|0|%s|%s%n", id, input, deadline);
        id++;
        try {
            storage.appendToFile(String.valueOf(s));
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    /**
     * Adds an EventTask to the task list.
     *
     * @param input the content of an event task.
     * @param deadline the deadline of an event task.
     */
    public void addEventTask(String input, String deadline) {
        EventTask newTask = new EventTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);

        String s = String.format("%d|E|0|%s|%s%n", id, input, deadline);
        id++;
        try {
            storage.appendToFile(String.valueOf(s));
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
    }

    /**
     * Complete a Task.
     *
     * @param taskIndex the index of a task to complete.
     */
    public void completeTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markDone();
        try {
            storage.updateTask(taskIndex - 1, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Undo the completion of a task with the given index.
     *
     * @param taskIndex the index of a task to undo completion.
     */
    public void undoTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markUndone();
        try {
            storage.updateTask(taskIndex - 1, false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * List all the tasks in the task list.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("%d. %s%n", i, taskList.get(i - 1));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes a task from the task list with the given index.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task foundTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(foundTask);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");

        try {
            storage.removeTask(index - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

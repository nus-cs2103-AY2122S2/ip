package duke.functionality;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


/**
 * TaskList is a functionality class which enables a tracking feature
 * to allow users to track their tasks.
 */
public class TaskList {
    private static final String LIST = "Here are the tasks in your list:";
    private static final String MARK = "Nice! I've marked this task as done:\n  ";
    private static final String UNMARK = "OK, I've marked this task as not done yet:\n  ";
    private static final String ADDED = "Got it. I've added this task:\n  ";
    private static final String REMOVED = "Noted. I've removed this task:\n";
    private static final String ALREADY_MARKED = "Tasked has already been marked...";
    private static final String ALREADY_UNMARKED = "Task has already been unmarked...";
    private static final String ALREADY_DELETED = "Task has already been deleted...";
    private static final String MATCH = "Here are the matching tasks in your list:";

    private final ArrayList<Task> taskList;

    /**
     * Creates a TaskList object, constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the task at a specified index.
     *
     * @param index An integer representing the index.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the current length of the tasklist.
     *
     * @return The length of the tasklist.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Prints out the list of tasks in the tasklist.
     */
    public void listItems() {
        System.out.println(LIST);
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i));
        }
    }

    /**
     * Prints a list of items that matches to the search.
     */
    public void listItemsMatch() {
        System.out.println(MATCH);
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + "." + this.taskList.get(i));
        }
    }

    /**
     * Marks the specific task as done.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @throws DukeException If task has been marked.
     */
    public void markTask(String taskNumber) throws DukeException {
        int index = Integer.parseInt(taskNumber) - 1;
        Task taskToMark = this.taskList.get(index);
        if (!taskToMark.getStatusIcon().equals("X")) {
            taskToMark.markAsDone();
            this.taskList.set(index, taskToMark);
        } else {
            throw new DukeException(ALREADY_MARKED);
        }
        System.out.println(MARK + taskToMark);
    }

    /**
     * Unmarks a task.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @throws DukeException If task has been unmarked.
     */
    public void unmarkTask(String taskNumber) throws DukeException {
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToUnmark = this.taskList.get(num);
        if (taskToUnmark.getStatusIcon().equals("X")) {
            taskToUnmark.markAsUndone();
            this.taskList.set(num, taskToUnmark);
        } else {
            throw new DukeException(ALREADY_UNMARKED);
        }
        System.out.println(UNMARK + taskToUnmark);
    }

    /**
     * Adds a Todo task to the tasklist.
     *
     * @param description A description of the Todo task.
     */
    public void addToDoTask(String description) {
        Todo todoItem = new Todo(description);
        this.taskList.add(todoItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + todoItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    /**
     * Adds a Deadline task to the tasklist.
     *
     * @param arr A String array containing the components of the user input.
     * @throws DukeException If conversion of the date is wrong in format.
     */
    public void addDeadlineTask(String[] arr) throws DukeException {
        Deadline deadlineItem = new Deadline(arr[0], Parser.convertDate(arr[1]));
        this.taskList.add(deadlineItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + deadlineItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    /**
     * Adds an Event task to the tasklist.
     *
     * @param arr A String array containing the components of the user input.
     * @throws DukeException If conversion of the date is wrong in format.
     */
    public void addEventTask(String[] arr) throws DukeException {
        Event eventItem = new Event(arr[0], Parser.convertDate(arr[1]));
        this.taskList.add(eventItem);
        int numOfItems = this.taskList.size();
        System.out.println(ADDED + eventItem);
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @throws DukeException If the task to be deleted does not exist.
     */
    public void deleteTask(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task itemToDelete = this.taskList.get(index);
            this.taskList.remove(index);
            int numOfItems = this.taskList.size();
            System.out.println(REMOVED + itemToDelete);
            System.out.println("Now you have " + numOfItems + " tasks in the list.");
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ALREADY_DELETED);
        }
    }

    /**
     * Adds a task into the tasklist, regardless of type of task.
     *
     * @param task The task to be added.
     */
    public void addGeneralTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Finds the task based on search key word.
     *
     * @param taskList The tasklist containing all the tasks.
     * @param userInput The String user input containing the key word to search.
     */
    public void findTask(TaskList taskList, String userInput) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            String stringToSearch = taskList.get(i).getDescription();
            if (stringToSearch.contains(userInput)) {
                foundTasks.addGeneralTask(taskList.get(i));
            }
        }
        foundTasks.listItemsMatch();
    }

}

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
    private static final String LIST = "Here are the tasks in your list:\n";
    private static final String MARK = "Nice! I've marked this task as done:\n  ";
    private static final String UNMARK = "OK, I've marked this task as not done yet:\n  ";
    private static final String ADDED = "Got it. I've added this task:\n  ";
    private static final String REMOVED = "Noted. I've removed this task:\n";
    private static final String ALREADY_MARKED = "Tasked has already been marked...";
    private static final String ALREADY_UNMARKED = "Task has already been unmarked...";
    private static final String ALREADY_DELETED = "There is no such task to be deleted...";
    private static final String MATCH = "Here are the matching tasks in your list:\n";
    private static final String DUPLICATE_MESSAGE = "This is a duplicate task... try again";
    private static final String EMPTY_LIST = "The list is empty... Try adding some tasks!";
    private static final String TASK_NOT_FOUND = "There is no matching tasks in the list... try something else.";

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
     * @return A String containing all the items in the list.
     */
    public String listItems() {
        String output = LIST;
        for (int i = 0; i < this.taskList.size(); i++) {
            output = output + (i + 1 + "." + this.taskList.get(i)) + "\n";
        }

        if (this.taskList.size() == 0) {
            output = EMPTY_LIST;
        }

        return output;
    }

    /**
     * @return A String containing the items that match the search query.
     */
    public String listItemsMatch() {
        String output = MATCH;
        for (int i = 0; i < this.taskList.size(); i++) {
            output = output + (i + 1 + "." + this.taskList.get(i)) + "\n";
        }
        return output;
    }

    /**
     * Marks the specific task as done.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @return A String message saying that the task has been marked.
     * @throws DukeException If task has been marked.
     */
    public String markTask(String taskNumber) throws DukeException {
        String output = MARK;
        int index = Integer.parseInt(taskNumber) - 1;
        Task taskToMark = this.taskList.get(index);
        if (!taskToMark.getStatusIcon().equals("X")) {
            taskToMark.markAsDone();
            this.taskList.set(index, taskToMark);
        } else {
            throw new DukeException(ALREADY_MARKED);
        }
        output = output + taskToMark;
        return output;
    }

    /**
     * Unmarks a task.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @return A String message saying that the task has been unmarked.
     * @throws DukeException If task has been unmarked.
     */
    public String unmarkTask(String taskNumber) throws DukeException {
        String output = UNMARK;
        int num = Integer.parseInt(taskNumber) - 1;
        Task taskToUnmark = this.taskList.get(num);
        if (taskToUnmark.getStatusIcon().equals("X")) {
            taskToUnmark.markAsUndone();
            this.taskList.set(num, taskToUnmark);
        } else {
            throw new DukeException(ALREADY_UNMARKED);
        }
        output = output + taskToUnmark;
        return output;
    }

    /**
     * Adds a Todo task to the tasklist if it does not already exists.
     *
     * @param description A description of the Todo task.
     * @return A String message saying that the Todo task has been added.
     */
    public String addToDoTask(String description) {
        String output = ADDED;
        int lastSize = this.taskList.size();
        Todo todoTask = new Todo(description);
        boolean isThereDuplicates = false;

        for (int i = 0; i < this.taskList.size(); i++) {
            Task taskInList = this.taskList.get(i);
            if (taskInList instanceof Todo
                    && ((Todo) taskInList).equalsTo(todoTask)) {
                isThereDuplicates = true;
                break;
            }
        }

        if (!isThereDuplicates) {
            this.taskList.add(todoTask);
            int numOfItems = this.taskList.size();
            assert numOfItems - lastSize == 1 : "item not added successfully";
            output = output + todoTask + "\n";
            output = output + "Now you have " + numOfItems + " tasks in the list.";
        } else {
            output = DUPLICATE_MESSAGE;
        }
        return output;
    }

    /**
     * Adds a Deadline task to the tasklist if it does not already exist.
     *
     * @param arr A String array containing the components of the user input.
     * @return A String message saying that the Deadline task has been added.
     * @throws DukeException If conversion of the date is wrong in format.
     */
    public String addDeadlineTask(String[] arr) throws DukeException {
        String output = ADDED;
        int lastSize = this.taskList.size();
        Deadline deadlineTask = new Deadline(arr[0], Parser.convertDate(arr[1]));
        boolean isThereDuplicates = false;

        for (int i = 0; i < this.taskList.size(); i++) {
            Task taskInList = this.taskList.get(i);
            if (taskInList instanceof Deadline
                    && ((Deadline) taskInList).equalsTo(deadlineTask)) {
                isThereDuplicates = true;
                break;
            }
        }

        if (!isThereDuplicates) {
            this.taskList.add(deadlineTask);
            int numOfItems = this.taskList.size();
            assert numOfItems - lastSize == 1 : "item not added successfully";
            output = output + deadlineTask + "\n";
            output = output + "Now you have " + numOfItems + " tasks in the list.";
        } else {
            output = DUPLICATE_MESSAGE;
        }
        return output;
    }

    /**
     * Adds an Event task to the tasklist if it does not already exist.
     *
     * @param arr A String array containing the components of the user input.
     * @return A String message saying that the Event task has been added.
     * @throws DukeException If conversion of the date is wrong in format.
     */
    public String addEventTask(String[] arr) throws DukeException {
        String output = ADDED;
        int lastSize = this.taskList.size();
        Event eventTask = new Event(arr[0], Parser.convertDate(arr[1]));
        boolean isThereDuplicates = false;

        for (int i = 0; i < this.taskList.size(); i++) {
            Task taskInList = this.taskList.get(i);
            if (taskInList instanceof Event
                    && ((Event) taskInList).equalsTo(eventTask)) {
                isThereDuplicates = true;
                break;
            }
        }

        if (!isThereDuplicates) {
            this.taskList.add(eventTask);
            int numOfItems = this.taskList.size();
            assert numOfItems - lastSize == 1 : "item not added successfully";
            output = output + eventTask + "\n";
            output = output + "Now you have " + numOfItems + " tasks in the list.";
        } else {
            output = DUPLICATE_MESSAGE;
        }
        return output;
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param taskNumber A String to be parsed into an integer, represents task number.
     * @return A String message saying that the task has been deleted.
     * @throws DukeException If the task to be deleted does not exist.
     */
    public String deleteTask(String taskNumber) throws DukeException {
        try {
            String output = REMOVED;
            int lastSize = this.taskList.size();
            int index = Integer.parseInt(taskNumber) - 1;
            Task itemToDelete = this.taskList.get(index);
            this.taskList.remove(index);
            int numOfItems = this.taskList.size();
            assert lastSize - numOfItems == 1 : "item not deleted successfully";
            output = output + itemToDelete + "\n";
            output = output + "Now you have " + numOfItems + " tasks in the list.";
            return output;
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
     * @return A String message with all the relevant tasks found.
     */
    public String findTask(TaskList taskList, String userInput) {
        String output = "";
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            String stringToSearch = taskList.get(i).getDescription();
            if (stringToSearch.contains(userInput)) {
                foundTasks.addGeneralTask(taskList.get(i));
            }
        }
        if (foundTasks.size() != 0) {
            output = output + foundTasks.listItemsMatch();
        } else {
            output = output + TASK_NOT_FOUND;
        }
        return output;
    }
}

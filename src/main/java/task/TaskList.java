package task;

import java.util.ArrayList;

import exception.DukeException;

/**
 * Interface that stores list of tasks and contains methods that manipulate it.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates new tasks based on if a todo, deadline or event is entered by the user.
     * @param taskType indicates if a task is a todo, deadline or event.
     * @param stringsToAdd contains user input string array.
     * @param containsBy indicates if a deadline by date has been entered.
     * @param containsOn indicated if an event on date has been entered.
     * @param returnString contains the description of the task entered by the user.
     * @throws DukeException when user has not entered a valid date for the task.
     */
    public String createNewTask(String taskType, String[] stringsToAdd, Boolean containsBy, Boolean containsOn,
                                String returnString) throws DukeException {
        switch (taskType) {
        case "todo":
            if (containsBy || containsOn) {
                throw new DukeException("Todo cannot have a due date. Create an deadline or event instead!");
            } else {
                return returnAfterAdding(new Todo(returnString), returnString);
            }
        case "deadline":
            if (!containsBy || stringsToAdd[stringsToAdd.length - 1].equals("/by")) {
                throw new DukeException("A deadline needs a due date. Create a todo/event instead!");
            } else {
                return returnAfterAdding(new Deadline(returnString, stringsToAdd[stringsToAdd.length - 1]), returnString);
            }
        default:
            if (!containsOn || stringsToAdd[stringsToAdd.length - 1].equals("/on")) {
                throw new DukeException("An event needs a date. Create a todo/deadline instead!");
            } else {
                return returnAfterAdding(new Event(returnString, stringsToAdd[stringsToAdd.length - 1]), returnString);
            }
        }
    }

    /**
     * Adds task to task list and informs user that it has been added.
     * @param task task to be added to the task list.
     * @param returnString description of the task to be added.
     * @return String to tell the user that the task has been added to the task list.
     */
    public String returnAfterAdding(Task task, String returnString) {
        tasks.add(task);
        return "Got it! I've added this task:\n" + " [" + task.symbol() + "][] "
                + returnString + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints all tasks in the task list.
     * @returns String containing all tasks in the list.
     */
    public String displayList() {
        String returnString = "";
        for (int i = 0; i < tasks.size(); i++) {
            returnString = returnString + (i + 1) + ". [" + tasks.get(i).symbol() + "]["
                    + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).displayTime() + "\n";
        }
        if (returnString.equals("")) {
            return "Seems like you haven't added any tasks to your list yet...";
        } else {
            return returnString;
        }
    }

    /**
     * Adds a new task to the task list.
     * @param stringsToAdd array that contains user command.
     * @throws DukeException If input string is not a todo, deadline or event.
     */
    public String addToList(String[] stringsToAdd) throws DukeException {
        String taskType;
        try {
            taskType = stringsToAdd[2];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Err what type of task do you want me to create for you?");
        }
        switch (taskType) {
        case "todo":
        case "deadline":
        case "event":
            String returnString = "";
            boolean containsBy = false;
            boolean containsOn = false;
            for (int i = 3; i < stringsToAdd.length; i++) {
                if (stringsToAdd[i].equals("/by")) {
                    containsBy = true;
                    break;
                } else if (stringsToAdd[i].equals("/on")) {
                    containsOn = true;
                    break;
                } else {
                    returnString = returnString + stringsToAdd[i] + " ";
                }
            }
            if (returnString.equals("")) {
                throw new DukeException("Task description cannot be empty...");
            } else {
                return createNewTask(taskType, stringsToAdd, containsBy, containsOn, returnString);
            }
        default:
            throw new DukeException("A task must be a todo, event or deadline...");
        }
    }

    /**
     * Marks a specific task in the task list as completed.
     * @param inputStringsArray String number that specifies task number in task list to mark as completed.
     * @throws DukeException when user has not entered a valid list index.
     */
    public String mark(String[] inputStringsArray) throws DukeException {
        try {
            int num = Integer.parseInt(inputStringsArray[2]);
            tasks.get(num - 1).setAsDone();
            Task temp = tasks.get(num - 1);
            return "Nice! I've marked this task as done:\n [" + temp.symbol() + "]["
                    + temp.getStatusIcon() + "] " + temp;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Try entering an valid index number?");
        }
    }

    /**
     * Marks a specific task in the task list as not completed.
     * @param inputStringsArray String number that specifies task number in task list to mark as not completed.
     * @throws DukeException when user has not entered a valid list index.
     */
    public String unmark(String[] inputStringsArray) throws DukeException {
        try {
            int num = Integer.parseInt(inputStringsArray[2]);
            tasks.get(num - 1).setAsNotDone();
            Task temp = tasks.get(num - 1);
            return "OK , I've marked this task as not done yet:\n [" + temp.symbol() + "]["
                    + temp.getStatusIcon() + "] " + temp;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Try entering an valid index number?");
        }
    }

    /**
     * Deletes all or specific tasks from the task list based on string input.
     * @param inputStringsArray string that specifies if a specific task number or all tasks are to be deleted.
     * @throws DukeException when user has not entered a valid list index.
     */
    public String delete(String[] inputStringsArray) throws DukeException {
        try {
            String number = inputStringsArray[2];
            if (number.equals("all")) {
                this.tasks = new ArrayList<>();
                return "All right! I have deleted all tasks in your list.";
            } else {
                int num = Integer.parseInt(number);
                Task temp = tasks.get(num - 1);
                String tempString = "Noted. I've removed this task:\n [" + temp.symbol() + "]["
                        + temp.getStatusIcon() + "] " + temp
                        + "\nNow you have " + (tasks.size() - 1) + " tasks left in this list";
                tasks.remove(num - 1);
                return tempString;
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Try entering an index number or 'all' for the tasks you want to delete");
        }
    }

    /**
     * Prints tasks in the task list that contain a specified keyword.
     * @param inputStringsArray keyword used to filer tasks in task list.
     * @throws DukeException when user has not entered a keyword.
     */
    public String find(String[] inputStringsArray) throws DukeException {
        try {
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (int i = 0; i < this.tasks.size(); i++) {
                String details = this.tasks.get(i).details;
                String[] detailsArray = details.split(" ");
                for (int j = 0; j < detailsArray.length; j++) {
                    if (detailsArray[j].equals(inputStringsArray[2])) {
                        foundTasks.add(this.tasks.get(i));
                        break;
                    }
                }
            }
            String returnString = "Here are the tasks I could find in your list that match the keyword:\n";
            for (int i = 0; i < foundTasks.size(); i++) {
                returnString = returnString + (i + 1) + ". [" + foundTasks.get(i).symbol() + "]["
                        + foundTasks.get(i).getStatusIcon() + "] " + foundTasks.get(i).displayTime() + "\n";
            }
            return returnString;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Enter a keyword to filter tasks by!");
        }
    }

    /**
     * Getter for tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

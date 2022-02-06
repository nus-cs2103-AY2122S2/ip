package task;

import exception.DukeException;
import java.util.ArrayList;

/**
 * Interface that stores list of tasks and contains methods that manipulate it.
 */
public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all tasks in the task list.
     */
    public String displayList() {
        String returnString = "";
        for (int i = 0; i < tasks.size(); i++) {
            returnString = returnString + (i + 1) + ". [" + tasks.get(i).symbol() + "][" + tasks.get(i).getStatusIcon()
                    + "] " + tasks.get(i).displayTime() + "\n";
        }
        return returnString;
    }

    /**
     * Adds a new task to the task list.
     * @param stringsToAdd array that contains details of the task user wants to add to the task list.
     * @throws DukeException If input string does not comply with todo, deadline or event formats.
     */
    public String addToList(String[] stringsToAdd) throws DukeException {
        if (stringsToAdd.length < 2) {
            throw new DukeException("OOPS!! The description of a " + stringsToAdd[0] + " cannot be empty.");
        } else {
            Task task;
            String returnString = "";
            boolean containsBy = false;
            for (int i = 1; i < stringsToAdd.length; i++) {
                if (stringsToAdd[i].equals("by")) {
                    containsBy = true;
                    break;
                } else {
                    returnString = returnString + stringsToAdd[i] + " ";
                }
            }
            if (stringsToAdd[0].equals("todo")) {
                if (containsBy) {
                    throw new DukeException("Todo cannot have a due date. Create an deadline or event instead :)");
                } else {
                    task = new Todo(returnString);
                }
            } else if (stringsToAdd[0].equals("deadline")) {
                if (!containsBy) {
                    throw new DukeException("A deadline needs a due date. Create a todo instead.");
                } else {
                    task = new Deadline(returnString, stringsToAdd[stringsToAdd.length - 1]);
                }
            } else {
                if (!containsBy) {
                    throw new DukeException("An event needs a due date. Create a todo instead.");
                } else {
                    task = new Event(returnString, stringsToAdd[stringsToAdd.length - 1]);
                }
            }
            tasks.add(task);
            return "Got it!! :D I've added this task:\n" + " [" + task.symbol() + "][] " + returnString +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Marks a specific task in the task list as completed.
     * @param number String number that specifies task number in task list to mark as completed.
     */
    public String mark(String number) {
        int num = Integer.parseInt(number);
        tasks.get(num - 1).setAsDone();
        Task temp = tasks.get(num - 1);
        return "Nice! :P I've marked this task as done:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp;
    }

    /**
     * Marks a specific task in the task list as not completed.
     * @param number String number that specifies task number in task list to mark as not completed.
     */
    public String unmark(String number) {
        int num = Integer.parseInt(number);
        tasks.get(num - 1).setAsNotDone();
        Task temp = tasks.get(num - 1);
        return "OK ._. , I've marked this task as not done yet:\n [" + temp.symbol() + "][" +
                temp.getStatusIcon() + "] " + temp;
    }

    /**
     * Deletes all or specific tasks from the task list based on string input.
     * @param number string that specifies if a specific task number or all tasks are to be deleted.
     */
    public String delete(String number) {
        if (number.equals("all")) {
            this.tasks =  new ArrayList<>();
            return "All right! I have deleted all tasks in your list.";
        } else {
            int num = Integer.parseInt(number);
            Task temp = tasks.get(num - 1);
            String tempString = "Noted. I've removed this task:\n [" + temp.symbol() + "][" +
                    temp.getStatusIcon() + "] " + temp +
                    "\nNow you have " + (tasks.size() - 1) + " tasks left in this list";
            tasks.remove(num - 1);
            return tempString;
        }
    }

    /**
     * Prints tasks in the task list that contain a specified keyword.
     * @param inputStringsArray keyword used to filer tasks in task list.
     */
    public String find(String inputStringsArray) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            String details = this.tasks.get(i).details;
            String[] detailsArray = details.split(" ");
            for (int j = 0; j < detailsArray.length; j++) {
                if (detailsArray[j].equals(inputStringsArray)) {
                    foundTasks.add(this.tasks.get(i));
                    break;
                }
            }
        }
        String returnString = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            returnString = returnString + (i + 1) + ". [" + foundTasks.get(i).symbol() + "]["
                    + foundTasks.get(i).getStatusIcon() + "] " + foundTasks.get(i).displayTime() + "\n";
        }
        return returnString;
    }
}

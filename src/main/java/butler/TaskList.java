package butler;

import java.util.ArrayList;

import butler.task.Deadline;
import butler.task.Event;
import butler.task.Task;
import butler.task.ToDo;

public class TaskList {

    /** A list of Tasks */
    protected ArrayList<Task> tasks;

    /** Default constructor */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the task from the Task ArrayList at the desired index.
     *
     * @param index Index of the Task of the ArrayList.
     * @return Task at the desired index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The size of the Task ArrayList, representing the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds all the tasks with descriptions that contain sub-strings that match the searchString.
     *
     * @param searchString String to match description sub-string.
     * @return Butler response that contains all tasks with descriptions that contain sub-strings that
     * match the searchString.
     */
    public String find(String searchString) {
        assert searchString != null;
        int i = 0;
        String output = "Here are the matching tasks in your list, Master: \n";
        while (i < tasks.size()) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(searchString)) {
                output += String.valueOf(i + 1) + ". " + task.toString() + "\n";
            }
            i++;
        }
        return output;
    }

    /**
     * Updates the description of a Task in the TaskList, given its index.
     *
     * @param index The index of the Task in the TaskList to be updated.
     * @param newDescription Updated description of the Task.
     * @return Butler response that confirms success status of adding the Deadline to the TaskList, and reiterates the
     * new state of the Task object.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String updateTaskDescription(int index, String newDescription) throws ButlerInputException {
        if (newDescription.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, "
                    + "but the new description of the task cannot be empty.\n");
        }
        if (index >= tasks.size() || index < 0) {
            throw new ButlerInputException("Error. Master, list item number "
                    + String.valueOf(index + 1) + " does not exist.\n");
        }
        Task task = tasks.get(index);
        task.setDescription(newDescription);
        return "Noted Master. I'll update this task in your list. It is now: \n" + "   " + task.toString() + "\n";
    }

    /**
     * Add a Deadline containing a description and a date and time to complete the task by a certain deadline.
     *
     * @param description description of the Task, which is a Deadline.
     * @param dateTime String representing the date and time to complete the Deadline by.
     * @return Butler response that confirms success status of adding the Deadline to the TaskList, and reiterates
     * the task being added and the number of tasks currently in the list.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String addDeadlineToList(String description, String dateTime) throws ButlerInputException {
        if (description.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, but the description of a Deadline cannot be empty.\n");
        }
        if (dateTime.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, but the date/time of a Deadline cannot be empty.\n");
        }
        Task task = new Deadline(description, dateTime);
        assert task != null;
        assert tasks != null;
        tasks.add(task);
        return "Noted Master. I'll add this task to your list.\n" + "   " + task.toString()
                + "\nThere are now " + tasks.size() + " tasks in your list.\n";
    }

    /**
     * Add a ToDo containing a description of the task
     *
     * @param description description of the Task, which is a ToDo.
     * @return Butler response that confirms success status of adding the ToDo to the TaskList, and reiterates
     * the task being added and the number of tasks currently in the list.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String addToDoToList(String description) throws ButlerInputException {
        if (description.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, but the description of a ToDo cannot be empty.\n");
        }
        Task task = new ToDo(description);
        assert task != null;
        assert tasks != null;
        tasks.add(task);
        return "Noted Master. I'll add this task to your list.\n" + "   " + task.toString()
                + "\nThere are now " + tasks.size() + " tasks in your list.\n";
    }

    /**
     * Add an Event containing a description and a date and time to complete the task at a certain time.
     *
     * @param description description of the Task, which is an Event.
     * @param dateTime String representing the date and time to complete the Event at.
     * @return Butler response that confirms success status of adding the Event to the TaskList, and reiterates
     * the task being added and the number of tasks currently in the list.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String addEventToList(String description, String dateTime) throws ButlerInputException {
        if (description.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, but the description of an Event cannot be empty.\n");
        }
        if (dateTime.equals("")) {
            throw new ButlerInputException("Error. Sorry Master, but the date/time of an Event cannot be empty.\n");
        }
        Task task = new Event(description, dateTime);
        assert task != null;
        assert tasks != null;
        tasks.add(task);
        return "Noted Master. I'll add this task to your list.\n" + "   " + task.toString()
                + "\nThere are now " + tasks.size() + " tasks in your list.\n";
    }

    /**
     * Deletes a task from the taskList at a certain index, with the first index starting from 1.
     *
     * @param index The index of the desired task to be deleted.
     * @return Butler response that confirms the success status of deleting a Task from the TaskList, and reiterates
     * the task being deleted, and the number of tasks currently in the list.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String deleteTaskFromList(int index) throws ButlerInputException {
        if (index >= tasks.size() || index < 0) {
            throw new ButlerInputException("Error. Master, list item number "
                    + String.valueOf(index + 1) + " does not exist. \n");
        }
        assert index < tasks.size();
        Task task = tasks.remove(index);
        assert task != null;
        return "Noted Master. I'll remove this task from your list.\n" + "   " + task.toString()
                + "\n There are now " + tasks.size() + " tasks in your list.\n";
    }

    /**
     * Marks a task in the taskList as done, given it's index, with the first index starting from 1.
     *
     * @param index The index of the desired task to be marked.
     * @return Butler response that confirms the success status of marking the Task in the TaskList and reiterates
     * the task being marked.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String markAsDone(int index) throws ButlerInputException {
        if (index >= tasks.size() || index < 0) {
            throw new ButlerInputException("Error. Master, list item number "
                    + String.valueOf(index + 1) + " does not exist.\n");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        assert task.getStatusIcon().equals("X");
        String string = "Congratulations Master, I've marked this task as done: \n";
        return string + "   [" + task.getStatusIcon() + "] " + task.getDescription() + "\n";
    }

    /**
     * Marks a task in the taskList as not done, given it's index, with the first index starting from 1.
     *
     * @param index The index of the desired task to be marked.
     * @return Butler response that confirms the success status of marking the Task in the TaskList and reiterates
     * the task being marked.
     * @throws ButlerInputException Throws a ButlerInputException if user input is not satisfactory.
     */
    public String markAsUndone(int index) throws ButlerInputException {
        if (index >= tasks.size() || index < 0) {
            throw new ButlerInputException("Error. Master, list item number "
                    + String.valueOf(index + 1) + " does not exist.\n");
        }
        assert index < tasks.size();
        Task task = tasks.get(index);
        assert task != null;
        task.markAsUndone();
        assert task.getStatusIcon().equals(" ");
        String string = "Very well Master, I've marked this task as not done yet: \n";
        return string + "   [" + task.getStatusIcon() + "] " + task.getDescription() + "\n";
    }

    /**
     * Views the list in its entirety
     *
     * @return Butler response that confirms the success status of viewing the TaskList, and lists all tasks in the list
     * with a String representation.
     */
    public String viewList() {
        int i = 0;
        String output = "Here are the tasks in your list Master: \n";
        while (i < tasks.size()) {
            Task task = tasks.get(i);
            assert task != null;
            output += String.valueOf(i + 1) + ". " + task.toString();
            i++;
        }
        return output;
    }
}

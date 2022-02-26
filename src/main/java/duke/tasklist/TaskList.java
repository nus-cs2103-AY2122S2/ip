package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Handles the execution of tasks.
 */
public class TaskList {

    private static ArrayList<Task> list = null;

    /**
     * Creates a TaskList object that initializes a new list if list was not null.
     */
    public TaskList() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to be added to the list.
     */
    public String add(Task task) {
        assert task != null : "Task to be added cannot be null.";
        list.add(task);

        return "Got it, I have added " + task.getUserInput() + " to the list!\n\n"
                + task
                + "\nNow you have "
                + list.size()
                + " tasks in the list.";
    }

    /**
     * Checks if the task at hand is a duplicate, return true, false otherwise.
     *
     * @param task Task to be added to the list.
     * @return True if task is a duplicate, false otherwise.
     */
    public boolean isTaskDuplicate(Task task) {
        for (Task tasks: list) {
            String taskDescription = tasks.getUserInput();

            if (taskDescription.equals(task.getUserInput())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a specific task in the list.
     *
     * @param input Input given by the user.
     * @return A string reply that indicates the removal of task from the list.
     */
    public String delete(String input) {
        assert input != null : "Input from user to delete a task cannot be null.";
        assert input.length() > 0 : "Input from user to delete a task  cannot be empty.";

        try {
            int i = Integer.parseInt(input.strip()) - 1;

            String description = list.get(i).toString();
            list.remove(i);
            return "Noted. I've removed this task:\n" + description + "\nNow you have "
                    + list.size() + " tasks in the list.";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Marks the task to be completed in the list.
     *
     * @param input Input given by the user.
     * @return A string reply that notifies the user that the task is marked.
     */
    public String markTask(String input) {
        assert input != null : "Input from user to mark a task cannot be null.";
        assert input.length() > 0 : "Input from user to mark a task cannot be empty.";

        try {
            int i = Integer.parseInt(input.strip()) - 1;

            if (i < 0 || i >= list.size()) {
                throw new DukeException("Duke has noticed that the number you provided does not "
                        + "match the number of task you have.\nPlease enter a valid task number!");
            }
            return list.get(i).markTask(true, true);
        } catch (NumberFormatException e) {
            return "OOPS!!! Please enter a valid task number!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Unmarks the task in the list.
     *
     * @param input Input given by the user.
     * @return A string reply that notifies the user that the task is unmarked.
     */
    public String unMarkTask(String input) {
        assert input != null : "Input from user to unmark a task cannot be null.";
        assert input.length() > 0 : "Input from user to unmark a task cannot be empty.";

        try {
            int i = Integer.parseInt(input.strip()) - 1;

            if (i < 0 || i >= list.size()) {
                throw new DukeException("Duke has noticed that the number you provided does not "
                        + "match the number of task you have.\nPlease enter a valid task number!");
            }

            return list.get(i).markTask(false, true);
        } catch (NumberFormatException e) {
            return "OOPS!!! Please enter a valid task number!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a list of tasks in detail in the order which it was added.
     *
     * @return A string reply consisting of all the tasks in the list.
     */
    public String readList() {
        StringBuilder listBuilder = new StringBuilder();
        listBuilder.append("Here are the task in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            listBuilder.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }

        return String.valueOf(listBuilder);
    }

    /**
     * Populate the list with tasks saved in the text file.
     *
     * @param data Tasks from the text file to be populated into the list.
     */
    public void fetchData(ArrayList<Task> data) {
        assert data != null : "Data from text file cannot be null.";

        list.clear();
        list.addAll(data);
    }

    /**
     * Finds a task by searching for a keyword.
     *
     * @param input Keyword specified by the user.
     * @return A string reply consisting of all the tasks that corresponds to the keyword in the list.
     */
    public String find(String input) {
        assert input != null : "Input from user to find a task cannot be null.";

        int i = 0;
        StringBuilder listBuilder = new StringBuilder();

        try {
            if (input.equals("")) {
                throw new DukeException("Find command cannot have an empty body!");
            }
            if (list.size() != 0) {
                listBuilder.append("Here are the matching tasks in your list:\n");

                for (Task task: list) {
                    LocalDate date = task.getDate();

                    if (task.getUserInput().contains(input)) {
                        listBuilder.append(++i).append(". ").append(task).append("\n");
                        continue;
                    }

                    if (date != null) {
                        if (date.toString().contains(input)
                                || date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).contains(input)) {
                            listBuilder.append(++i).append(". ").append(task).append("\n");
                        }
                    }
                }
            }
            if (i == 0) {
                return "Duke searched high and low but could not find the task that you want!";
            }
        } catch (DukeException e) {
            return "OOPS!!! The description of the find command cannot be empty!";
        }
        return String.valueOf(listBuilder);
    }
}

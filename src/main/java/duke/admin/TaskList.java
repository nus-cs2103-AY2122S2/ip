package duke.admin;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Trigger;

/**
 * TaskList class manages the task list and the actions that can be performed on
 * the task list.
 */
public class TaskList {
    private static final String BUFFER = " xxx ";
    private static final Trigger noChangeTrigger = new Trigger("n0 cH4Ng3$ d#t3CtEd");
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList that takes in data from the storage file and
     * constructs a task list from there.
     * @param tasksArr data read from the storage file
     * @throws DukeException exception when data is corrupted or task list cannot be
     *                       created as intended
     */
    public TaskList(String[] tasksArr) throws DukeException {
        try {
            tasks = new ArrayList<>();

            for (String task : tasksArr) {
                String[] taskDetails = task.split(BUFFER);

                String type = taskDetails[0];
                Boolean isMarked = (Integer.parseInt(taskDetails[1]) > 0);
                String description = taskDetails[2];

                switch (type) {
                case "T":
                    tasks.add(new ToDo(description, isMarked));
                    break;
                case "D":
                    tasks.add(new Deadline(description, isMarked));
                    break;
                case "E":
                    tasks.add(new Event(description, isMarked));
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    /**
     * Constructor for TaskList when storage file data is unavailable.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the task specified to the task list.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task indexed by the index specified.
     * @param index the index of the task that is to be deleted
     * @return the task after it is deleted from the task list
     */
    public Task delete(int index) {
        assert index <= tasks.size();
        Task deletedTask = tasks.get(index);
        tasks.remove(index);

        return deletedTask;
    }

    /**
     * Prints out all the tasks in the task list and their index.
     * @return task list as a string
     */
    public String list() {
        String listString = "";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String listElement = i + ". " + task.toString();

            listString = listString + listElement + System.lineSeparator();
        }

        return listString;
    }

    /**
     * Prints out all the tasks in the task list that contains the keyword.
     * @param keyword keyword to be contained by the tasks
     * @return result as a string
     */
    public String find(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskAsString = task.toString().toLowerCase();
            if (taskAsString.contains(keyword.toLowerCase())) {
                searchResults.add(task);
            }
        }

        String searchResultString = "";

        for (int j = 1; j <= searchResults.size(); j++) {
            Task matchedTask = searchResults.get(j - 1);
            String result = j + ". " + matchedTask.toString();
            searchResultString = searchResultString + result + System.lineSeparator();
        }

        if (searchResultString.equals("")) {
            return "There's nothing that contains the keyword!";
        } else {
            return "These are the matching tasks:\n" + searchResultString;
        }
    }

    /**
     * Changes the mark of the task if the command requests for a change in mark and returns the task that has been
     * changed. If not, return a trigger task to trigger the system to inform the user that the command does not
     * change the task.
     * @param index index of task to be marked
     * @param toMark if the command wishes the indexed task to be marked or not
     * @return the changed task or a trigger task
     */
    public Task changeMark(int index, boolean toMark) {
        assert index <= tasks.size();

        Task taskToChange = tasks.get(index);
        boolean isMarked = taskToChange.isDone();
        boolean isChangingMark = isMarked ^ toMark;

        if (isChangingMark) {
            taskToChange.toggleMark();
            return taskToChange;
        } else {
            return noChangeTrigger;
        }

    }

    /**
     * Returns the number of tasks in the task list.
     * @return number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Updates the details of the indexed task and returns the resulting task.
     * @param type details to be updated: description, date or time
     * @param index index of task to be updated
     * @param updateValue value to be updated to
     * @return task that has been updated
     * @throws DukeException when there is an error trying to update the date or time value
     */
    public Task update(String type, int index, String updateValue) throws DukeException {
        Task task = tasks.get(index);

        switch (type) {
        case "description":
            task.updateDescription(updateValue);
            break;
        case "date":
            task.updateDate(updateValue);
            break;
        case "time":
            task.updateTime(updateValue);
            break;
        default:
            throw new DukeException(DukeException.INVALID_FORMAT);
        }

        return task;
    }

    /**
     * Clones the indexed task and adds to the the end of the task list. Returns the cloned task at the end.
     * @param index index of the task to be cloned
     * @return the task that is cloned
     */
    public Task clone(int index) {
        Task taskToBeCloned = tasks.get(index);
        tasks.add(taskToBeCloned);

        return taskToBeCloned;
    }
}

package funbox.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import funbox.exception.FunBoxExceptions;
import funbox.task.Task;
import funbox.task.Deadline;
import funbox.task.Event;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Deletes an item on the list.
     *
     * @param index The item to be deleted from the list.
     * @param ui Interface which interact with users.
     * @return Returns a string to be displayed to the user.
     */
    public String delete(int index, Ui ui) {
        Task temp = taskList.get(index);
        this.taskList.remove(index);
        String result = ui.printNotice() + "\n";
        result += ui.printDeletedTask(temp) + "\n";
        result += ui.printRemainingTasks(this);
        return result;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Sets the task as done on the list.
     *
     * @param index The position of the task on the list.
     */
    public void setTaskDone(int index) {
        this.taskList.get(index).setDone();
    }

    /**
     * Sets the task as done on the list during startup.
     *
     * @param index The position of the task on the list.
     */
    public void setPreTaskDone(int index) {
        this.taskList.get(index).presetDone();
    }

    /**
     * Gets the task on the list.
     *
     * @param ui Interface which interact with users.
     * @param index The position of the task on the list.
     * @return Returns a string to be displayed to the user.
     */
    public String getTask(Ui ui, int index) {
        return ui.printTask(index + 1, this.taskList.get(index));
    }

    /**
     * Prints the task on the list.
     *
     * @param ui Interface which interact with users.
     * @return Returns a string to be displayed to the user.
     */
    public String printTasks(Ui ui) {
        String result = "";
        if (this.getSize() >= 1) {
            result = ui.printListHeader() + "\n";
            for (int i = 0; i < this.taskList.size(); i++) {
                result += ui.printTask(i + 1, this.taskList.get(i)) + "\n";
            }
        } else {
            result = ui.emptyList();
        }
        return result;
    }

    /**
     * Sets the task as not done.
     *
     * @param index The position of the task on the list.
     */
    public void setTaskUndone(int index) {
        this.taskList.get(index).setUndone();
    }

    /**
     * Gets the size of the list.
     *
     * @return Returns the size of the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return Returns a tasks of list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Filter the list based on the date provided by the user.
     *
     * @param filterDate The date used to filter the list.
     * @param taskList The list containing the task.
     * @throws FunBoxExceptions If date is not formatted in `yyyy-mm-dd`.
     * @return Returns a string to be displayed to the user.
     */

    public String filterTasks(String filterDate, TaskList taskList, Ui ui) throws FunBoxExceptions {
        LocalDate date;
        String result = "";
        try {
            date = LocalDate.parse(filterDate);
            ArrayList<Task> eventList = new ArrayList<>(taskList.getTaskList());
            ArrayList<Task> deadlineList = new ArrayList<>(taskList.getTaskList());
            eventList.removeIf(task -> (task.type.contains("todo") || task.type.contains("deadline")));
            deadlineList.removeIf(task -> (task.type.contains("todo") || task.type.contains("event")));

            int counter = 0;
            int eventSize = eventList.size();
            int deadlineSize = deadlineList.size();


            for (int i = 0; i < eventSize; i++) {
                Event temp = (Event) eventList.get(i);
                if (temp.date.equals(date)) {
                    counter++;
                    result += ui.printTask(counter, temp) + "\n";
                }
            }

            for (int i = 0; i < deadlineSize; i++) {
                Deadline temp = (Deadline) deadlineList.get(i);
                if (temp.date.equals(date)) {
                    counter++;
                    result += ui.printTask(counter, temp) + "\n";
                }
            }

            if (counter == 0) {
                result = ui.printNoTaskFound();
            }

        } catch (DateTimeParseException e) {
            throw new FunBoxExceptions("ERROR! Please ensure date is in the correct format: yyyy-mm-dd");
        }

        return result;
    }

    /**
     * Finds a filtered task on the list.
     *
     * @param filter The filter used to filter the list.
     * @param ui Interface which interact with users.
     * @param taskList The tasklist use for filtering.
     * @return Returns a string to be displayed to the user.
     */
    public String findTasks(String filter, Ui ui, TaskList taskList) {
        String result = "";
        ArrayList<Task> tempTaskList = new ArrayList<>(taskList.getTaskList());
        String filterLowerCase = filter.toLowerCase();
        tempTaskList.removeIf(task -> !task.description.toLowerCase().contains(filterLowerCase));

        int counter = 1;
        for (Task task : tempTaskList) {
            result += ui.printTask(counter, task) + "\n";
            counter++;
        }
        return result;
    }
}

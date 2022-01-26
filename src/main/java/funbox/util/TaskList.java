package funbox.util;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     * Delete task from the task list.
     *
     * @param index The position of the task on the list.
     * @param ui Use for outputting messages for interaction with the users.
     */
    public void delete(int index, Ui ui) {
        Task temp = taskList.get(index);
        this.taskList.remove(index);
        ui.printNotice();
        ui.printDeletedTask(temp);
        ui.printRemainingTasks(this);
    }

    /**
     * Add task into the task list.
     *
     * @param task The tasks to be added into the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Set the task as done.
     *
     * @param index The position of the task on the list to be marked as done.
     */
    public void setTaskDone(int index) {
        this.taskList.get(index).setDone();
    }

    /**
     * Set the task as done, used for setting up tasks before the main program starts.
     *
     * @param index The position of the task on the list to be marked as done.
     */
    public void setPreTaskDone(int index) {
        this.taskList.get(index).presetDone();
    }

    /**
     * Get the task from the list.
     *
     * @param ui Use for outputting messages for interaction with the users.
     * @param index The tasks to be added into the list.
     */
    public void getTask(Ui ui, int index) {
        ui.printTask(index, this.taskList.get(index));
    }

    /**
     * Print all the tasks on the task list.
     *
     * @param ui Use for outputting messages for interaction with the users.
     */
    public void printTasks(Ui ui) {

        if (this.getSize() >= 1) {
            ui.printListHeader();
            for (int i = 0; i < this.taskList.size(); i++) {
                ui.printTask(i + 1, this.taskList.get(i));
            }
        } else {
            ui.emptyList();
        }

    }

    /**
     * Set the task from the list as undone.
     *
     * @param index The position of the task on the list to be marked as not done.
     */
    public void setTaskUndone(int index) {
        this.taskList.get(index).setUndone();
    }

    /**
     * Return the size of the task list.
     *
     * @return Return the size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Return the task list.
     *
     * @return Return the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Filter tasks based on the date.
     *
     * @param filterDate The date used to filter the task list.
     * @param taskList The tasklist used for filtering.
     * @param ui Use for outputting messages for interaction with the users.
     * @throws FunBoxExceptions If date is not in format yyyy-mm-dd, or if array index is out of bound.
     */
    public void filterTasks(String filterDate, TaskList taskList, Ui ui) throws FunBoxExceptions {
        LocalDate date;
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
                    System.out.println(counter + "." + temp);
                }
            }

            for (int i = 0; i < deadlineSize; i++) {
                Deadline temp = (Deadline) deadlineList.get(i);
                if (temp.date.equals(date)) {
                    counter++;
                    System.out.println(counter + "." + temp);
                }
            }

            if (counter == 0) {
                ui.printNoTasksFound();
            }

        } catch (DateTimeParseException e) {
            throw new FunBoxExceptions("ERROR! Please ensure date is in the correct format: yyyy-mm-dd");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FunBoxExceptions("ERROR! Date not found in command `filter`!");
        }
    }

    /**
     * Find the tasks based on the filter keyword.
     *
     * @param filter Keyword used to filter the task list.
     * @param ui Use for outputting messages for interaction with the users.
     */
    public void findTasks(String filter, Ui ui) {
        ArrayList<Task> filteredList = new ArrayList<>(this.taskList);
        filteredList.removeIf(task -> !task.description.toLowerCase().contains(filter.toLowerCase()));
        int counter = 1;
        for (Task task : filteredList) {
            ui.printTask(counter, task);
            counter++;
        }
    }
}

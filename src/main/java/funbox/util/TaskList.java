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

    public void delete(int index, Ui ui) {
        Task temp = taskList.get(index);
        this.taskList.remove(index);
        ui.printNotice();
        ui.printDeletedTask(temp);
        ui.printRemainingTasks(this);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void setTaskDone(int index) {
        this.taskList.get(index).setDone();
    }

    public void setPreTaskDone(int index) {
        this.taskList.get(index).presetDone();
    }

    public void getTask(Ui ui, int index) {
        ui.printTask(index, this.taskList.get(index));
    }

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

    public void setTaskUndone(int index) {
        this.taskList.get(index).setUndone();
    }

    public int getSize() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Filter the list based on the date provided by the user.
     *
     * @param filterDate The date used to filter the list.
     * @param taskList The list containing the task.
     * @throws FunBoxExceptions If date is not formatted in `yyyy-mm-dd`.
     */
    public void filterTasks(String filterDate, TaskList taskList) throws FunBoxExceptions {
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
                System.out.println("No tasks found on this date! You are free!");
            }

        } catch (DateTimeParseException e) {
            throw new FunBoxExceptions("ERROR! Please ensure date is in the correct format: yyyy-mm-dd");
        }
    }
}

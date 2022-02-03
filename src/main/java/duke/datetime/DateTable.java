package duke.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import duke.exception.BotException;
import duke.task.Task;
import duke.util.Ui;

/**
 * Stores collection of task and time and performs operation to it
 */
public class DateTable {
    private HashMap<LocalDate, ArrayList<Task>> dateMap = new HashMap<>();
    private final BotException exception = new BotException();
    private final Ui ui;

    public DateTable(Ui ui) {
        this.ui = ui;
    }

    /**
     * Prints out the list of event on a specific date
     *
     * @param dateString The string version of the date
     */
    public void getEventOnDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString,
                DateTimeFormatter.ofPattern("d/M/yyyy"));

        if (dateMap.containsKey(date)) {
            ArrayList<Task> eventList = dateMap.get(date);
            ui.showDate(eventList);
        } else {
            exception.printDateNotFoundError();
        }
    }

    /**
     * Adds date with task to the collection
     *
     * @param task The task need to be added to the collection
     */
    public void addDate(Task task) {
        LocalDate localDate = task.getTime();

        if (dateMap.containsKey(localDate)) {
            ArrayList<Task> eventList = dateMap.get(localDate);
            eventList.add(task);
        } else {
            ArrayList<Task> eventList = new ArrayList<>();
            eventList.add(task);
            dateMap.put(localDate, eventList);
        }
    }

    /**
     * Removes the task from the collection
     *
     * @param task The task need to be removed
     */
    public void deleteTaskOnDate(Task task) {
        if (!task.getType().equals("T")) {
            ArrayList<Task> taskArrayList = dateMap.get(task.getTime());
            taskArrayList.remove(task);
        }
    }
}

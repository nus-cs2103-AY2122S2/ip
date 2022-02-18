package mcbot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import mcbot.task.Event;
import mcbot.task.Task;

/**
 * TaskList class to store the list of tasks. 
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for Tasklist. 
     */
    public TaskList() {
    }

    /**
     * Constructor for TaskList if a list of tasks is available. 
     * 
     * @param arrayList is the list of tasks. 
     */
    public TaskList(ArrayList<Task> arrayList) {
        taskList = arrayList;
    }

    /**
     * Method to get the task list. 
     * 
     * @return the list of tasks. 
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Method to get the size of the task list. 
     * 
     * @return the size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Method to get a specific task at index i. 
     * 
     * @param i is the index of the task to retrieve. 
     * @return the task found at index i. 
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Method to add task to the task list. 
     * 
     * @param t is the task to be added. 
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Method to remove task at index i. 
     * 
     * @param i is the index of the task to be removed. 
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Method to find any matching task name.
     * 
     * @param taskName name to be searched for.
     * @return the task if any task matches found.
     */
    public String find(String taskName) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        boolean anyMatch = false;
        for (Task t : taskList) {
            boolean isTaskFound = t.getTaskName().contains(taskName);
            if (isTaskFound && isFirst) {
                sb.append("I'ave found this: \n");
                isFirst = false;
                anyMatch = true;
            } else if (isTaskFound) {
                sb.append(t + "\n");
            }
        }
        if (!anyMatch) {
            return "No matching task found, sorry mate";
        } else {
            return sb.toString();
        }
    }

    /**
     * Method to check if there is clashing of date and time.
     * @param t the event to be checked against.
     * @return true if there is a clash and false otherwise.
     */
    public boolean hasAnomaly(Event t) {
        LocalDate currDate = t.getEventDate();
        LocalTime currTime = t.getEventTime();
        if (currTime == null) {
            return false;
        }
        for (Task prevTask: taskList) {
            if (prevTask instanceof Event) {
                Event prevEvent = (Event) prevTask;
                LocalDate prevDate = prevEvent.getEventDate();
                LocalTime prevTime = prevEvent.getEventTime();
                if (prevTime == null) {
                    return false;
                }
                boolean hasSameDate = currDate.equals(prevDate);
                boolean hasSameTime = currTime.equals(prevTime);
                if (hasSameDate && hasSameTime) {
                    return true;
                }
            }
        }
        return false;
    }
}

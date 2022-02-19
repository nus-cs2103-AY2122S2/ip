package holobot.misc;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import holobot.exception.InvalidDateTime;
import holobot.task.Deadline;
import holobot.task.Event;
import holobot.task.Task;
import holobot.task.Todo;

/**
 * Acts as a storage for all the tasks that the user has entered.
 *
 * @author Terng Yan Long
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;
    private int numberOfTasks;

    /**
     * Instantiates a new instance of list of tasks, according to the size specified.
     *
     * @param size Size of list of tasks.
     */
    public TaskList(int size) {
        this.listOfTasks = new ArrayList<>(size);
        this.numberOfTasks = 0;
    }

    /**
     * Retrieves the current amount of tasks in the list.
     *
     * @return the total number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Retrieves the array list of task.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    /**
     * Adds the task into the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTasks(Task task) {
        listOfTasks.add(task);
        numberOfTasks++;
    }

    /**
     * Iterates through the list and prints out each task that is on the list.
     */
    public String display() {
        String result = "";
        result += "Here are the tasks in your list:\n";
        for (int i = 1; i <= numberOfTasks; i++) {
            Task currTask = listOfTasks.get(i - 1);
            String output = i + ". " + currTask + "\n";
            result += output;
        }
        return result;
    }

    /**
     * Marks task by changing its status to "done".
     *
     * @param taskId The taskID that corresponds to the task that is to be marked.
     */
    public String mark(int taskId) {
        Task currTask = listOfTasks.get(taskId - 1);
        currTask.setStatus(true);
        writeToFile();
        return "Nice! I've marked this task as done:\n" + currTask;
    }

    /**
     * Unmarks task by changing its status to "not done".
     *
     * @param taskId The taskID that corresponds to the task that is to be unmarked.
     */
    public String unmark(int taskId) {
        Task currTask = listOfTasks.get(taskId - 1);
        currTask.setStatus(false);
        writeToFile();
        return "OK, I've marked this task as not done yet:\n" + currTask;
    }

    /**
     * Adds the to-do task into the list.
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public String todo(String userInput) {
        numberOfTasks++;

        Task currentTask = new Todo(userInput);
        listOfTasks.add(currentTask);
        writeToFile();

        return "Got it. I've added this task:\n" + currentTask + "\n"
                + "Now you have " + numberOfTasks + " task/s in the list.";
    }

    /**
     * Adds the task (which has a deadline) into the list.
     *
     * @param userInput Command entered by the user that describes the task.
     * @param dueDate Specifies the date that this task has to be done by.
     * @param dueTime Specifies the time that this task has to be done by.
     */
    public String deadline(String userInput, LocalDate dueDate, LocalTime dueTime) {
        numberOfTasks++;

        Task currentTask = new Deadline(userInput, dueDate, dueTime);
        listOfTasks.add(currentTask);
        writeToFile();

        return "Got it. I've added this task:\n" + currentTask + "\n"
                + "Now you have " + numberOfTasks + " task/s in the list.";
    }

    /**
     * Adds the event task into the list.
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public String event(String userInput, LocalDate eventDate,
                      LocalTime eventStartTime, LocalTime eventEndTime) {
        numberOfTasks++;

        Task currentTask = new Event(userInput, eventDate, eventStartTime, eventEndTime);
        listOfTasks.add(currentTask);
        writeToFile();

        return "Got it. I've added this task:\n" + currentTask + "\n"
                + "Now you have " + numberOfTasks + " task/s in the list.";
    }

    /**
     * Deletes task that corresponds to the specified task ID.
     *
     * @param taskId The taskID that corresponds to the task that is to be deleted.
     */
    public String delete(int taskId) {
        Task currTask = listOfTasks.get(taskId - 1);
        listOfTasks.remove(taskId - 1);
        numberOfTasks--;
        writeToFile();

        return "Noted. I've removed this task:\n" + currTask;
    }

    /**
     * Searches for and prints all tasks that contains the keyword specified by the user.
     *
     * @param searchTarget Keyword specified by the user.
     */
    public String find(String searchTarget) {
        StringBuilder res = new StringBuilder();
        int count = 1;
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currTask = listOfTasks.get(i);
            String currTaskDesc = currTask.getDescription().toString();
            if (currTaskDesc.contains(searchTarget)) {
                String currString = count + ". " + currTask;
                res.append(currString).append("\n");
                count++;
            }
        }
        if (count == 1) {
            return "No results found :(";
        } else {
            return "Here are the matching tasks in your list:\n" + res;
        }
    }

    /**
     * Converts the TaskList into a single string file, to be stored in data.txt on the hard disk.
     *
     * @return All tasks in the form of a String.
     */
    private String parseList() {
        StringBuilder res = new StringBuilder();
        for (Task listOfTask : listOfTasks) {
            String currTask = listOfTask.toString();
            res.append(currTask).append("\n");
        }
        return res.toString();
    }

    /**
     * Saves the tasks in the hard disk automatically whenever the task list changes.
     */
    private void writeToFile() {
        String filePath = Storage.DATA_PATH;
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            fw.write(parseList());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred when writing to data file!\n"
                    + "Please try again later.");
            e.printStackTrace();
        }
    }

    /**
     * Searches for and prints all tasks that happens on the specified date.
     *
     * @param date Date specified by the user.
     */
    public String schedule(String date) {
        LocalDate convertedDate = convertDate(date);

        StringBuilder listOfEvent = new StringBuilder();
        listOfEvent.append("You have the following events on ").append(date).append(" :\n");
        int eventCount = 1;

        StringBuilder listOfDeadline = new StringBuilder();
        listOfDeadline.append("Here are the list of tasks that are due on ").append(date).append(" :\n");
        int deadlineCount = 1;

        for (Task currTask : listOfTasks) {
            if (currTask instanceof Event) {
                eventCount = addEventToList(listOfEvent, (Event) currTask, convertedDate, eventCount);
            } else if (currTask instanceof Deadline) {
                deadlineCount = addDeadlineToList(listOfDeadline, (Deadline) currTask, convertedDate, deadlineCount);
            }
        }
        return printSchedule(eventCount, deadlineCount, listOfEvent, listOfDeadline);
    }

    /**
     * Converts the date String into a LocalDate variable if the input string is of the right format.
     *
     * @param date Date string input by user.
     * @return LocalDate variable.
     */
    private LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter date in this format: <yyyy-MM-dd>");
        }
        return convertedDate;
    }

    /**
     * Checks if the currTask is on the same date as convertedDate.
     * If the dates match, the currTask will be appended to the StringBuilder.
     *
     * @param listOfEvent Current list of Events in the form of a StringBuilder.
     * @param currTask Current task that we are checking.
     * @param convertedDate LocalDate that is specified by the user.
     * @param eventCount Current number of tasks that matches the date specified by the user.
     * @return The updated eventCount.
     */
    private int addEventToList(StringBuilder listOfEvent, Event currTask, LocalDate convertedDate, int eventCount) {
        if (currTask.getDate().isEqual(convertedDate)) {
            String currString = eventCount + ". " + currTask;
            listOfEvent.append(currString).append("\n");
            return eventCount + 1;
        } else {
            return eventCount;
        }
    }

    /**
     * Checks if the currTask is on the same date as convertedDate.
     * If the dates match, the currTask will be appended to the StringBuilder.
     *
     * @param listOfDeadline Current list of Deadline in the form of a StringBuilder.
     * @param currTask Current task that we are checking.
     * @param convertedDate LocalDate that is specified by the user.
     * @param deadlineCount Current number of tasks that matches the date specified by the user.
     * @return The updated eventCount.
     */
    private int addDeadlineToList(StringBuilder listOfDeadline, Deadline currTask,
                                  LocalDate convertedDate, int deadlineCount) {
        if (currTask.getDate().isEqual(convertedDate)) {
            String currString = deadlineCount + ". " + currTask;
            listOfDeadline.append(currString).append("\n");
            return deadlineCount + 1;
        } else {
            return deadlineCount;
        }
    }

    /**
     * Prints the lists of tasks that corresponds to the user specified date.
     *
     * @param eventCount Number of Event Tasks that matched the user specified date.
     * @param deadlineCount Number of Deadline Tasks that matched the user specified date.
     * @param listOfEvent List of Event Tasks that matched the user specified date.
     * @param listOfDeadline List of Deadline Tasks that matched the user specified date.
     * @return A String to be printed onto the GUI and displayed to the user.
     */
    private String printSchedule(int eventCount, int deadlineCount,
                                 StringBuilder listOfEvent, StringBuilder listOfDeadline) {
        if (eventCount == 1 && deadlineCount == 1) {
            return "No results found :(";
        } else if (eventCount == 1) {
            return listOfDeadline.toString();
        } else if (deadlineCount == 1) {
            return listOfEvent.toString();
        } else {
            return listOfEvent.toString() + "\n" + listOfDeadline.toString();
        }
    }
}

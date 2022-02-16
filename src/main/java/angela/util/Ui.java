package angela.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import angela.datetime.DateTable;
import angela.task.Task;

/**
 * Represents a helper program to read from and write to interface
 */
public class Ui {
    private static final String DATE_FORMAT = "MMM d yyyy";

    /**
     * Initialize an Ui interface
     */
    public Ui() {
    }

    /**
     * Prints out the welcoming text when user initialize the bot
     */
    public ArrayList<String> startChat() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(String.valueOf(TypicalString.HELLO));
        wordArray.add(" What can I do for you?");
        return wordArray;
    }

    /**
     * Prints out the goodbye text when user end the bot
     */
    public ArrayList<String> endChat() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(TypicalString.GOODBYE.toString());
        return wordArray;
    }

    /**
     * Prints out all the task in the current list
     *
     * @param storingList Current task list
     */
    public ArrayList<String> showTaskList(ArrayList<Task> storingList) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Here are the tasks in your list:");

        for (int i = 1; i <= storingList.size(); i++) {
            String taskString = " " + i + "." + storingList.get(i - 1);
            wordArray.add(taskString);
        }
        return wordArray;
    }

    /**
     * Prints out the response based on changing state of the task
     *
     * @param task   Task that changing state
     * @param isDone State of the task
     */
    public ArrayList<String> showTaskMark(Task task, boolean isDone) {
        return task.changeTaskStatus(isDone);
    }

    /**
     * Prints out the task and total number of tasks after adding
     *
     * @param task    Task that just been added in
     * @param numTask Number of tasks after adding
     */
    public ArrayList<String> showAllTask(Task task, int numTask) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(String.valueOf(TypicalString.ADDED_TASK));
        wordArray.add("  " + task);
        wordArray.add(" Now you have " + numTask + " tasks in the list.");
        return wordArray;
    }

    /**
     * Prints out the task that been deleted and the number of tasks afterwards
     *
     * @param task    Task that just been deleted
     * @param numTask Number of tasks left
     */
    public ArrayList<String> showDeleteTask(Task task, int numTask) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Noted. I've removed this task: ");
        wordArray.add("  " + task);
        wordArray.add(" Now you have " + numTask + " tasks in the list.");
        return wordArray;
    }

    /**
     * Prints out the list of tasks in a specific date
     *
     * @param eventList Collection of tasks on the date
     */
    public ArrayList<String> showDate(ArrayList<Task> eventList) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add("You have " + eventList.size() + " deadlines/events in the day:");

        for (int i = 1; i <= eventList.size(); i++) {
            String dateString = i + "." + eventList.get(i - 1);
            wordArray.add(dateString);
        }
        return wordArray;
    }

    /**
     * Prints results that match with the keyword
     *
     * @param numIndex        Current number of task that match with the keyword
     * @param task            Task that been matched with keyword
     */
    public String showSearchResult(int numIndex, Task task) {
        String reply = "";
        if (numIndex == 0) {
            reply += TypicalString.MATCH_TASK;
        } else {
            reply += " " + numIndex + "." + task;
        }
        return reply;
    }

    /**
     * Returns string represent list of task in order of time
     *
     * @param dateTable Time and task map
     * @return The string represent the list of task
     */
    public ArrayList<String> showUpcomingDeadlines(DateTable dateTable) {
        ArrayList<String> wordArray = new ArrayList<>();
        TreeMap<LocalDate, ArrayList<Task>> dateMap = dateTable.getDateMap();
        if (dateMap.size() == 0) {
            wordArray.add(" Great, you don't have any upcoming deadlines");
            return wordArray;
        }
        wordArray.add(" Here are your upcoming deadlines:");
        for (Map.Entry<LocalDate, ArrayList<Task>> entry : dateMap.entrySet()) {
            LocalDate dateKey = entry.getKey();
            ArrayList<Task> taskValue = entry.getValue();
            for (Task task : taskValue) {
                String deadlineString = " " + dateKey.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + ": " + task.getDescription();
                wordArray.add(deadlineString);
            }
        }
        return wordArray;
    }

    /**
     * Returns string represent the nearest deadlines date and its task
     * @param dateTable Time and task map
     * @return The string represent the nearest deadlines and tasks
     */
    public ArrayList<String> showNearestDeadlines(DateTable dateTable) {
        ArrayList<String> wordArray = new ArrayList<>();
        TreeMap<LocalDate, ArrayList<Task>> dateMap = dateTable.getDateMap();
        if (dateMap.size() == 0) {
            wordArray.add(" You have 0 nearest deadline");
            return wordArray;
        }
        Map.Entry<LocalDate, ArrayList<Task>> nearestDate = dateMap.firstEntry();
        LocalDate dateKey = nearestDate.getKey();
        ArrayList<Task> taskValue = nearestDate.getValue();
        String initialWord = " You have " + taskValue.size() + " nearest deadlines on "
                + dateKey.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ": ";
        wordArray.add(initialWord);
        int count = 1;
        for (Task task : taskValue) {
            String taskString = " " + count + ". " + task.getDescription();
            wordArray.add(taskString);
            count += 1;
        }
        return wordArray;
    }

    /**
     * Prints out the error message when loading the bot
     */
    public ArrayList<String> showLoadingError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry, some problem have occurred during initialization: ");
        return wordArray;
    }

    public enum TypicalString {
        LONG_LINE {
            public String toString() {
                return "____________________________________________________________";
            }
        },
        ADDED_TASK {
            public String toString() {
                return " Got it. I've added this task:";
            }
        },
        HELLO {
            public String toString() {
                return " Hello! I'm Angela, your personal task manager assistant";
            }
        },
        GOODBYE {
            public String toString() {
                return " Bye. Hope to see you again soon!";
            }
        },
        MATCH_TASK {
            public String toString() {
                return " Here are the matching tasks in your list:" + "\n";
            }
        }
    }
}

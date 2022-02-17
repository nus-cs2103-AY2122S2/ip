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
     * Initializes an Ui interface
     */
    public Ui() {
    }

    /**
     * Prints out the welcoming text when user initialize the bot
     *
     * @return The array list represent the String context
     */
    public ArrayList<String> startChat(boolean isNewUser) {
        ArrayList<String> wordArray = new ArrayList<>();
        if (isNewUser) {
            wordArray.add(String.valueOf(TypicalString.HELLO_NEW_USER));
            wordArray.add(" I noticed you are a new user, maybe you can look through the guide below: ");
        } else {
            wordArray.add(String.valueOf(TypicalString.HELLO_OLD_USER));
            wordArray.add(" Let's go through your upcoming deadlines, shall we?");
        }
        return wordArray;
    }

    /**
     * Prints out the goodbye text when user end the bot
     *
     * @return The array list represent the String context
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
     * @return The array list represent the String context
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
     * @return The array list represent the String context
     */
    public ArrayList<String> showTaskMark(Task task, boolean isDone) {
        return task.changeTaskStatus(isDone);
    }

    /**
     * Prints out the task and total number of tasks after adding
     *
     * @param task    Task that just been added in
     * @param numTask Number of tasks after adding
     * @return The array list represent the String context
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
     * @return The array list represent the String context
     */
    public ArrayList<String> showDeleteTask(Task task, int numTask) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" No problem. I will removed this task: ");
        wordArray.add("  " + task);
        wordArray.add(" Now you have " + numTask + " tasks.");
        return wordArray;
    }

    /**
     * Prints out the list of tasks in a specific date
     *
     * @param eventList Collection of tasks on the date
     * @return The array list represent the String context
     */
    public ArrayList<String> showDate(ArrayList<Task> eventList) {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" I found" + eventList.size() + " deadlines and events in that day:");

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
     * @return The array list represent the String context
     */
    public String showSearchResult(int numIndex, Task... task) {
        if (numIndex == 0) {
            return String.valueOf(TypicalString.MATCH_TASK);
        } else {
            String reply = "";
            reply += " " + numIndex + "." + task[0];
            return reply;
        }
    }

    /**
     * Returns string represent list of task in order of time
     *
     * @param dateTable Time and task map
     * @return The string array represent the list of task
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
     *
     * @param dateTable Time and task map
     * @return The string array represent the nearest deadlines and tasks
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
     *
     * @return The array list represent the String context
     */
    public ArrayList<String> showLoadingError() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add(" Sorry, some problem have occurred during initialization: ");
        return wordArray;
    }

    /**
     * Returns all the commands with their description
     *
     * @return The array string contains all the commands with their descriptions
     */
    public ArrayList<String> displayAllCommand() {
        ArrayList<String> wordArray = new ArrayList<>();
        wordArray.add("Command syntax and its meaning:");
        wordArray.add("list: Display all the task that you have accumulated so far");
        wordArray.add("mark + numberId: Mark a task with numberId as finished");
        wordArray.add("unmark + numberId: Mark a task with numberId as unfinished");
        wordArray.add("todo + description: Create a todo task with description");
        wordArray.add("deadline + description + time: Create a deadline task with "
                + "description and time, with time use this format '/at D/M/YYYY'");
        wordArray.add("event + description + time: Create a event task with "
                + "description and time, with time use this format '/at D/M/YYYY'");
        wordArray.add("delete + numberId: Delete a task with numberId");
        wordArray.add("date + time: Find all deadlines and events in time, with time use this format D/M/YYYY");
        wordArray.add("bye: End the conversation. Don't worry, your tasks will be stored");
        wordArray.add("find + keyword: Find a task that contains the keyword in description");
        wordArray.add("And don't worry if you can't remember all these syntax, "
                + "just type help and this guide will appear again");
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
                return " Okay. I've noted down this task:";
            }
        },
        HELLO_OLD_USER {
            @Override
            public String toString() {
                return " It's a pleasure to have you back user";
            }
        },
        HELLO_NEW_USER {
            public String toString() {
                return " Welcome! I'm Angela, your personal task manager assistant";
            }
        },
        GOODBYE {
            public String toString() {
                return " Goodbye. See you next time user.";
            }
        },
        MATCH_TASK {
            public String toString() {
                return " I found these matching tasks with your keyword:";
            }
        }
    }
}

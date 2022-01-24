package sonautil;

import task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Ui {

    public static final String LINE = "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static final String logo = "  *    ,---.    ,-----.  ,--.  ,--.   ,---.      *\n"
            + "      '   .-'  '  .-.  ' |  ,'.|  |  /  O  \\    *\n"
            + " *    `.  `-.  |  | |  | |  |' '  | |  .-.  |\n"
            + "    * .-'    | '  '-'  ' |  | `   | |  | |  |      *\n"
            + "*     `-----'   `-----'  `--'  `--' `--' `--'   *";

    /**
     * Prints when the list is empty
     */
    public static void emptyListMessage() {
        System.out.println(LINE + "\nOops... your list is empty! :(\n" + LINE);
    }

    /**
     * Prints out the line when Duke is responding
     */
    public static void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out when Duke is list
     */
    public static void showListMessage() {
        System.out.println(LINE + "\nHere is your task list!");
    }

    /**
     * Prints out right after Sona is opened
     */
    public static void welcomeMessage() {
        System.out.println(logo);
        System.out.println("\nHi! You can call me Sona ^^~\nHow can I help you?");
    }

    /**
     * Prints out after user types "bye"
     */
    public static void goodbyeMessage() {
        System.out.println(LINE + "\nBye-bye :')  Hope to see you soon!\n" + LINE);
    }

    /**
     * Prints out when file is not found
     */
    public static void fileNotFoundMessage() {
        System.out.println("Oh no... I couldn't find the task list in your hard disk...\n" +
                "Don't worry! I will create one for you now :)\n");
    }

    /**
     * Prints out when file is invalid
     */
    public static void invalidFileMessage() {
        System.out.println(LINE
                + "Oops! I can't load your file due to some errors...\nDon't worry! I will create a new one for you."
                + LINE);
    }

    /**
     * Prints out when task description is not entered by user
     *
     * @return message to user
     */
    public static String emptyTaskMessage() {
        return LINE + "\nOh no..did you forget to put what you need to do? :(\n" + LINE;
    }

    /**
     * Prints out when format of date/time is invalid
     *
     * @return message to user
     */
    public static String dateTimeErrorMessage() {
        return LINE
                + "\nOh no... the format or the date/time you entered is wrong! "
                + "The correct format should be YYYY-MM-DD HHMM\n"
                + "It is not necessary to put in time!\n"
                + LINE;
    }

    /**
     * Prints out when user's command cannot be understood by Sona
     *
     * @param s user's input message
     * @return message to user
     */
    public static String userUnknownInputMessage(String s) {
        return LINE + "\n" + s + "? Sorry, I don't understand what that means.. :(\n" + LINE;
    }

    /**
     * Prints out when the list of a particular day is empty
     *
     * @return message to user
     */
    public static String scheduleEmptyMessage() {
        return LINE + "\nThere is nothing happening on this day! Hooray~\n" + LINE;
    }

    /**
     * Prints out the list of tasks on a particular date
     *
     * @param date date that the user wants to check
     */
    public static void showSchedule(String date) {
        System.out.println(LINE
                + "\nThese are the events/deadlines happening on " + date + ":");
    }

    /**
     * Prints out when user did not enter a date to check schedule
     *
     * @return message to user
     */
    public static String scheduleNoDateErrorMessage() {
        return LINE + "\nWhich day would you like to check? Input format: schedule YYYY-MM-DD\n" + LINE;
    }

    /**
     * Prints out when a task is removed from the list
     *
     * @param task task to be removed
     */
    public static void taskRemovedMessage(Task task) {
        System.out.println(LINE + "\nDone! I've removed this task:\n"
                + task.toString() + "\n" + LINE);
    }

    /**
     * Prints out when user did not specify which task to remove
     *
     * @return message to user
     */
    public static String taskRemoveEmptyMessage() {
        return LINE
                + "\nWhich task do you want to delete? Add the number in the end to tell me~\n"
                + LINE;
    }

    /**
     * Prints out when task list is empty
     *
     * @return message to user
     */
    public static String listEmptyMessage() {
        return LINE + "\nOops... your list is empty! :(\n" + LINE;
    }

    /**
     * Prints out when an event is entered successfully
     *
     * @param task task to be entered
     * @param timeEntered inform user that the time is set to 00:00 since the user did not enter a time for the event
     * @param tasksAdded_index total number of tasks in the list
     */
    public static void eventEnterSuccessMessage(Task task, String timeEntered, int tasksAdded_index) {
        if (timeEntered.equals("true")) {
            System.out.println((LINE + "\nWokay! I've added this task:\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAdded_index) + " task(s) in the list\n" + LINE));
        } else {
            System.out.println((LINE
                    + "\nSince you did not enter what time is this event happening, I will help you set it at 00:00!\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAdded_index) + " task(s) in the list\n" + LINE));
        }
    }

    /**
     * Prints out when date of event is not entered
     *
     * @return message to user
     */
    public static String eventDateEmptyMessage() {
        return LINE + "\nOops! You forgot to add the event date after '/'.. try again? :)\n" + LINE;
    }

    /**
     * Prints out when "/" is not entered in user's command
     *
     * @return message to user
     */
    public static String eventNoSlashMessage() {
        return LINE
                + "\nWhen is the event happening? Add '/' and the date after the event to make me record ;)\n"
                + LINE;
    }

    /**
     * Prints out when deadline is entered successfully
     *
     * @param task task to be entered
     * @param timeEntered inform user that the time is set to 23:59 since the user did not enter a time for the event
     * @param tasksAdded_index total number of tasks in the list
     */
    public static void deadlineEnterSuccessMessage(Task task, String timeEntered, int tasksAdded_index) {
        if (timeEntered.equals("true")) {
            System.out.println((LINE + "\nWokay! I've added this task:\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAdded_index) + " task(s) in the list\n" + LINE));
        } else {
            System.out.println((LINE + "\nSince you did not enter the due time for this task, I will help you set it at 23:59!\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAdded_index) + " task(s) in the list\n" + LINE));
        }
    }

    /**
     * Prints out when date of deadline is not entered by user
     *
     * @return message to user
     */
    public static String deadlineDateEmptyMessage() {
        return LINE + "\nOops! You forgot to add the due date after '/'.. try again? :)\n" + LINE;
    }

    /**
     * Prints out when "/" is not entered in user's command
     *
     * @return message to user
     */
    public static String deadlineNoSlashMessage() {
        return LINE
                + "\nWhen is the deadline? Add '/' and the date after your task to make me record ;)\n"
                + LINE;
    }

    /**
     * Prints out when to-do task is entered successfully
     *
     * @param task task to be added
     * @param taskAdded_index total number of tasks in the list
     */
    public static void todoEnteredSuccessMessage(Task task, int taskAdded_index) {
        System.out.println(LINE + "\nWokay! I've added this task:\n"
                + task.toString()
                + "\nNow you have " + (taskAdded_index) + " task(s) in the list\n" + LINE);
    }

    /**
     * Prints out when a task is successfully unmarked
     *
     * @param task task to be unmarked
     */
    public static void unmarkSuccessMessage(Task task) {
        System.out.println(LINE + "\nAw man..I've marked this task as not done yet:\n"
                + task.toString() + "\n" + LINE);
    }

    /**
     * Prints out when user repeatedly unmark a task
     *
     * @return message to user
     */
    public static String unmarkRepeatMessage() {
        return LINE + "\nYou have already unmarked this task!\n" + LINE;
    }

    public static String taskDontExistMessage(int taskIndex) {
        return LINE + "\nThere is no number " + (taskIndex + 1) + " in your task list!\n" + LINE;
    }

    /**
     * Prints out when no number is entered after "unmark"
     *
     * @return message to user
     */
    public static String unmarkNoNumberMessage() {
        return LINE + "\nWhich task do you want to unmark? Add the number in the end to tell me~\n" + LINE;
    }

    /**
     * Prints out when a task is successfully marked
     *
     * @param task task to be marked
     */
    public static void markSuccessMessage(Task task) {
        System.out.println(LINE + "\nYay! I've marked this task as done:\n"
                + task.toString() + "\n" + LINE);
    }

    /**
     * Prints out when user repeatedly mark a task
     *
     * @return message to user
     */
    public static String markRepeatMessage() {
        return LINE + "\nYou have already finished this task! :D\n" + LINE;
    }

    /**
     * Prints out when no number is entered after "mark"
     *
     * @return message to user
     */
    public static String markNoNumberMessage() {
        return LINE + "\nWhich task do you want to mark? Add the number in the end to tell me~\n" + LINE;
    }


}

package duke.sonautil;

import duke.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Ui {

    public static final String LOGO = "  *    ,---.    ,-----.  ,--.  ,--.   ,---.      *\n"
            + "      '   .-'  '  .-.  ' |  ,'.|  |  /  O  \\    *\n"
            + " *    `.  `-.  |  | |  | |  |' '  | |  .-.  |\n"
            + "    * .-'    | '  '-'  ' |  | `   | |  | |  |      *\n"
            + "*     `-----'   `-----'  `--'  `--' `--' `--'   *";

    /**
     * Prints when the list is empty
     *
     * @return message to user
     */
    public static String emptyListMessage() {
        return "Oops... your list is empty! :(";
    }

    /**
     * Prints out when Duke.Duke is list
     *
     * @return message to user
     */
    public static String showListMessage() {
        return "Here is your list!";
    }

    /**
     * returns string right after Sona is opened
     *
     * @return message to user
     */
    public static String welcomeMessage() {
        return "Hi! You can call me Sona, your AMAZING task manager ><! How can I help you?";
    }

    /**
     *  returns string after user types "bye"
     *
     * @return message to user
     */
    public static String goodbyeMessage() {
        return "Bye-bye :')  Hope to see you soon!";
    }

    /**
     * Prints out when file is invalid
     *
     * @returns message to user
     */
    public static String invalidFileMessage() {
        return "Oops! I can't load your file due to some errors..."
                + "\nDon't worry! I will create a new one for you.";
    }

    /**
     * Prints out when Duke.task description is not entered by user
     *
     * @return message to user
     */
    public static String emptyTaskMessage() {
        return "Oh no..did you forget to put what you need to do? :(";
    }

    /**
     * Prints out when format of date/time is invalid
     *
     * @return message to user
     */
    public static String dateTimeErrorMessage() {
        return "\nOh no... the format or the date/time you entered is wrong! "
                + "The correct format should be YYYY-MM-DD HHMM\n"
                + "It is not necessary to put in time!\n";
    }

    /**
     * Prints out when user's command cannot be understood by Sona
     *
     * @param s user's input message
     * @return message to user
     */
    public static String userUnknownInputMessage(String s) {
        return s + "? Sorry, I don't understand what that means.. :(";
    }

    /**
     * Prints out when the list of a particular day is empty
     *
     * @return message to user
     */
    public static String scheduleEmptyMessage() {
        return "There is nothing happening on this day! Hooray~";
    }

    /**
     * Prints out the list of tasks on a particular date
     *
     * @param date date that the user wants to check
     * @return message to user
     */
    public static String showSchedule(String date) {
        return "These are the events/deadlines happening on " + date + ":\n";
    }

    /**
     * Prints out when user did not enter a date to check schedule
     *
     * @return message to user
     */
    public static String scheduleNoDateErrorMessage() {
        return "Which day would you like to check? Input format: schedule YYYY-MM-DD";
    }

    /**
     * Prints out when a Duke.task is removed from the list
     *
     * @param task Duke.task to be removed
     * @return message to user
     */
    public static String taskRemovedMessage(Task task) {
        return "Done! I've removed this task:\n" + task.toString();
    }

    /**
     * Prints out when user did not specify which Duke.task to remove
     *
     * @return message to user
     */
    public static String taskRemoveEmptyMessage() {
        return "Which task do you want to delete? Add the number in the end to tell me~";
    }

    /**
     * Prints out when Duke.task list is empty
     *
     * @return message to user
     */
    public static String listEmptyMessage() {
        return "Oops... your list is empty! :(";
    }

    /**
     * Prints out when an event is entered successfully
     *
     * @param task Duke.task to be entered
     * @param timeEntered inform user that the time is set to 00:00 since the user did not enter a time for the event
     * @param tasksAddedIndex total number of tasks in the list
     * @return message to user
     */
    public static String eventEnterSuccessMessage(Task task, String timeEntered, int tasksAddedIndex) {
        if (timeEntered.equals("true")) {
            return "Wokay! I've added this task:\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAddedIndex) + " task(s) in the list";
        } else {
            return "Since you did not enter what time is this event happening, I will help you set it at 00:00!\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAddedIndex) + " task(s) in the list";
        }
    }

    /**
     * Prints out when date of event is not entered
     *
     * @return message to user
     */
    public static String eventDateEmptyMessage() {
        return "Oops! You forgot to add the event date after '/'.. try again? :)";
    }

    /**
     * Prints out when "/" is not entered in user's command
     *
     * @return message to user
     */
    public static String eventNoSlashMessage() {
        return "When is the event happening? Add '/' and the date after the event to make me record ;)";
    }

    /**
     * Prints out when deadline is entered successfully
     *
     * @param task Duke.task to be entered
     * @param timeEntered inform user that the time is set to 23:59 since the user did not enter a time for the event
     * @param tasksAddedIndex total number of tasks in the list
     * @return message to user
     */
    public static String deadlineEnterSuccessMessage(Task task, String timeEntered, int tasksAddedIndex) {
        if (timeEntered.equals("true")) {
            return "Wokay! I've added this task:\n" + task.toString()
                    + "\nNow you have " + (tasksAddedIndex) + " task(s) in the list";
        } else {
            return "Since you did not enter the due time for this task, "
                    + "I will help you set it at 23:59!\n"
                    + task.toString()
                    + "\nNow you have " + (tasksAddedIndex) + " task(s) in the list";
        }
    }

    /**
     * Prints out when date of deadline is not entered by user
     *
     * @return message to user
     */
    public static String deadlineDateEmptyMessage() {
        return "Oops! You forgot to add the due date after '/'.. try again? :)";
    }

    /**
     * Prints out when "/" is not entered in user's command
     *
     * @return message to user
     */
    public static String deadlineNoSlashMessage() {
        return "When is the deadline? Add '/' and the date after your task to make me record ;)";
    }

    /**
     * Prints out when to-do Duke.task is entered successfully
     *
     * @param task Duke.task to be added
     * @param tasksAddedIndex total number of tasks in the list
     * @return message to user
     */
    public static String todoEnteredSuccessMessage(Task task, int tasksAddedIndex) {
        return "Wokay! I've added this task:\n"
                + task.toString()
                + "\nNow you have " + (tasksAddedIndex) + " task(s) in the list";
    }

    /**
     * Prints out when a Duke.task is successfully unmarked
     *
     * @param task Duke.task to be unmarked
     * @return message to user
     */
    public static String unmarkSuccessMessage(Task task) {
        return "Aw man..I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Prints out when user repeatedly unmark a Duke.task
     *
     * @return message to user
     */
    public static String unmarkRepeatMessage() {
        return "You have already unmarked this task!";
    }

    public static String taskDontExistMessage(int taskIndex) {
        return "There is no number " + (taskIndex + 1) + " in your task list!";
    }

    /**
     * Prints out when no number is entered after "unmark"
     *
     * @return message to user
     */
    public static String unmarkNoNumberMessage() {
        return "Which task do you want to unmark? Add the number in the end to tell me~";
    }

    /**
     * Prints out when a Duke.task is successfully marked
     *
     * @param task Duke.task to be marked
     * @return message to user
     */
    public static String markSuccessMessage(Task task) {
        return "Yay! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Prints out when user repeatedly mark a Duke.task
     *
     * @return message to user
     */
    public static String markRepeatMessage() {
        return "You have already finished this task! :D";
    }

    /**
     * Prints out when no number is entered after "mark"
     *
     * @return message to user
     */
    public static String markNoNumberMessage() {
        return "Which task do you want to mark? Add the number in the end to tell me~";
    }

    /**
     * Prints out when keyword is not entered after "find"
     *
     * @return message to user
     */
    public static String findNoKeywordError() {
        return "Enter your keyword behind 'find' to let me search for you! :)";
    }

    /**
     * Prints out when there is no match to user's search
     *
     * @return message to user
     */
    public static String findNoMatchError() {
        return "I couldn't find any task that matches your search! :(";
    }

    /**
     * Prints out when Sona is showing search result to user
     *
     * @return message to user
     */
    public static String findShowResult() {
        return "Here are the tasks that match your search:\n";
    }


}

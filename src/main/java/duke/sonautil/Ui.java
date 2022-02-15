package duke.sonautil;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Ui {

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
        return "Here is your list!\n";
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
        return "Oh no... the format or the date/time you entered is wrong! "
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
        return s + "? Sorry, I don't understand what that means.. :(\n" +
                "Send 'help' if you need any, and ILL BE THERE FOR YOU~";
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
        assert(date != null);
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

    /**
     * Prints out when message after "update" is empty
     *
     * @return message to user
     */
    public static String updateEmptyBodyError() {
        return "Oops! Do you need help with the update feature? Type 'update guide' to check out how to use it :D";
    }

    /**
     * Returns message when update command has wrong format
     *
     * @return message to user
     */
    public static String updateWrongFormat() {
        return "Oh no... The format of your update command is wrong :(. " +
                "Type 'update guide' to check out how to use it :D";
    }

    /**
     * Returns message when user want to change to a invalid task type
     *
     * @return message to user
     */
    public static String updateInvalidTaskType() {
        return "The task types you can change is todo, deadline or event. Try again? :)\n" +
                "P.S. note that a todo task cannot be changed to a deadline/event";
    }

    /**
     * Returns message when user want to add date to a todo task
     *
     * @return message to user
     */
    public static String updateTodoDateError() {
        return "You can't add a date to a Todo Task. Try add a new one! :)";
    }

    /**
     * Returns message when user want to change a task type to the same type
     *
     * @return message to user
     */
    public static String updateSameTypeError(String type) {
        return "Task already has type " + type + "~";
    }

    /**
     * Returns message when user want to change from todo to a deadline or event
     *
     * @return message to user
     */
    public static String updateTypeErrorMessage() {
        return "You can't change a Todo task to a Deadline or Event since it needs a date. Try adding a new task! :)";
    }

    /**
     * Returns message when user want to change a task description to the same description
     *
     * @return message to user
     */
    public static String updateSameDescriptionError() {
        return "Task has same description!";
    }

    /**
     * Returns message when user successfully changed the type of task
     *
     * @param newTask the updated task
     * @return message to user
     */
    public static String updateTypeSuccessMessage(Task newTask) {
        if (newTask instanceof Todo) {
            return "Done! Your task is now modified and updated~ You can't set date (and time) for a todo task, " +
                    "therefore it is removed: \n" + newTask.toString();
        } else {
            return "Done~ Your task is now modified and updated:\n" + newTask.toString();
        }
    }

    /**
     * Returns message when user successfully changed the task description
     *
     * @param newTask the updated task
     * @return message to user
     */
    public static String updateDateSuccessMessage(Task newTask, String timeEntered) {
        if (timeEntered.equals("false") && newTask instanceof Deadline) {
            return "Done~ Your task is now modified and updated!" +
                    "Since you did not enter the due time for this task, I will help you set it at 23:59!\n";
        } else if (timeEntered.equals("false") && newTask instanceof Event) {
            return "Done~ Your task is now modified and updated!" +
                    "Since you did not enter the due time for this task, I will help you set it at 00:00!\n";
        } else {
            return "Done~ Your task is now modified and updated:\n    " + newTask.toString();
        }

    }

    /**
     * Returns message when user want to change a task description to the same description
     *
     * @param newTask the updated task
     * @return message to user
     */
    public static String updateDescriptionSuccessMessage(Task newTask) {
        return "Done! Your task is now modified and updated~\n    " + newTask.toString();
    }

    /**
     * Shows the guide for update command
     *
     * @return message to user
     */
    public static String showUpdateCommandGuide() {
        return "*~*~*~*~*~*~*~*~*~*~*~*~*\n" +
                "update [task #] [type/name/date]: [your change]\n" +
                "*~*~*~*~*~*~*~*~*~*~*~*~* \n" +
                "\n- put 'type' to change the task to another category (i.e. todo, deadline, event)\n" +
                "- put 'name' to change the description of the task\n" +
                "- put 'date' to change the date (and time) of the task\n";
    }

    /**
     * Shows the guide for Sona
     *
     * @return message to user
     */
    public static String showHelpMessage() {
        return  "These are what you can make me do for you: \n\n" +
                "*~*~*~*~*~*~*~*~*~*~*~*~*\n\n" +
                "There are 3 ways to add tasks:\n" +
                "    1. todo [your task]\n    2. deadline [your task]/[due date & time]\n    " +
                "3. event [your task]/[due date & time]\n" +
                "Simply replace those in brackets to whatever you need, please don't include '[]'.\n" +
                "* Note that the dates must be in the format of YYYY-MM-DD HHMM, and time is optional!\n\n" +
                "*~*~*~*~*~*~*~*~*~*~*~*~*\n\n" +
                "Other commands:\n" +
                "    1. list --> I will list out all the tasks you have added\n" +
                "    2. mark [task #] -> I will mark this task as done\n" +
                "    3. unmark [task #] -> I will mark this task as not done\n" +
                "    4. delete [task #] -> I will delete this task for you\n" +
                "    5. schedule [YYYY-MM-DD] -> I will list out all your tasks on this day\n" +
                "    6. find [keyword] -> I will list all the tasks that has this keyword\n" +
                "    7. clear -> I will clear all the tasks in your list\n" +
                "    8. bye -> I will save all your tasks and say byebye to you~!\n\n" +
                "*~*~*~*~*~*~*~*~*~*~*~*~*\n\n" +
                "Special command: update -> Send 'update guide' to find out more!";

    }

    /**
     * Shows success message for clearing
     *
     * @return message to user
     */
    public static String clearSuccessMessage() {
        return "Done~ your list is now empty (and there is no turning back)!";
    }
}

package dazz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import dazz.exception.ErrorType;
import dazz.exception.InvalidDateFormatException;
import dazz.task.Task;

/**
 * Represents the user interface of Dazz (chatbot)
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private static final String LOGO = "\t  ____       _       _____   _____\n"
            + "\t |  _\"\\  U  /\"\\  u  |\"_  / u |\"_  / u\n"
            + "\t/| | | |  \\/ _ \\/   U / / /  U / / /\n"
            + "\tU| |_| |\\ / ___ \\   \\/ /_   \\/ /_\n"
            + "\t |____/ u/_/   \\_\\  /____|  /____|\n"
            + "\t  |||_    \\\\    >>  _//<<,- _//<<,-\n"
            + "\t (__)_)  (__)  (__)(__) (_/(__) (_/";
    private final Scanner scanner;

    /**
     * Constructs the user interface.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome page when Dazz is first run.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE + "\n\tHello from\n" + LOGO + "\n\n"
                + "\tGood day!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    /**
     * Returns the user input in the form of String
     * @return String user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the line to separate Dazz's messages after every user input.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the exit message when user exits the program.
     */
    public void showExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints the task that has been added and the total number of tasks in the list.
     * @param task Task task (i.e Todo, Deadline or Event)
     * @param taskList Task list
     */
    public void showAdd(Task task, TaskList taskList) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Prints the error messages whenever an error has occurred.
     * @param errorMessage The error message to be printed
     */
    public void showError(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }

    /**
     * Shows the task that has been marked
     * @param task Task (i.e Todo, Deadline or Event) to be marked
     */
    public void showMark(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
    }

    /**
     * Shows the task that has been unmarked
     * @param task Task (i.e Todo, Deadline or Event) to be unmarked (i.e Todo, Deadline or Event)
     */
    public void showUnmark(Task task) {
        System.out.println("\tOK, I've unmarked this task as not done yet:");
        System.out.println("\t  " + task);
    }

    /**
     * Shows the task that has been deleted
     * @param task Task (i.e Todo, Deadline or Event) to be deleted
     */
    public void showDelete(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
    }

    /**
     * Prints the task in the task list.
     * User will be informed if there is no task.
     * @param taskList Task List that contains the tasks.
     */
    public void showList(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list:");
        if (taskList.getSize() == 0) {
            System.out.println("\tYou have no task in your list.");
        } else {
            taskList.list();
        }
    }

    /**
     * Prints the default message.
     */
    public void showSearches(TaskList taskList, String search) {
        String finalSearch = search.toLowerCase(Locale.ROOT);
        System.out.println("\tHere are the matching tasks in your list:");
        List<Task> tasks = taskList.getTaskList();
        tasks.forEach(x -> {
            if (x.getDescription().toLowerCase(Locale.ROOT).contains(finalSearch)) {
                System.out.println("\t" + x);
            }
        });
    }

    public void showDefault() {
        System.out.println("\t" + ErrorType.INVALID_COMMAND.getErrorMessage());
    }

    /**
     * Converts date that has been input in String to a DateTime format.
     * @param date Date of the task due or happen.
     * @return DateTime object.
     * @throws InvalidDateFormatException if the given String is not convertable.
     */
    public static LocalDateTime toLocalDateTime(String date) throws InvalidDateFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy HHmm]"
                    + "[dd/MM/yyyy HHmm]"
                    + "[dd MMM yyyy, hh:mma]");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}

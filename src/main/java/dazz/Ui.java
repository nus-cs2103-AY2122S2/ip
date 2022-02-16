package dazz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dazz.exception.ErrorType;
import dazz.exception.InvalidDateFormatException;
import dazz.task.Task;

/**
 * Represents the user interface of Dazz.
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
     * Prints the welcome message when Dazz is first run.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE + "\n\tHello from\n" + LOGO + "\n\n"
                + "\tGood day!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE);
    }

    /**
     * Returns the user input in the form of String.
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints line to separate Dazz's messages after every user input.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns the message when user exits.
     * @return Exit message.
     */
    public String messageForExit() {
        return "Bye. Hope to see you again soon!\n"
                + "Click on 'X' to close the window.";
    }

    /**
     * Returns the message when user adds a <code>Task</code>.
     * @param task The <code>Task</code> to be added.
     * @param taskList The <code>TaskList</code> the <code>Task</code> is added to.
     * @return Message to prompt successful addition of <code>Task</code>.
     */
    public String messageForAdd(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
    }

    /**
     * Returns the message when an error has occurred.
     * @param errorMessage The error message that has occurred.
     * @return The error message.
     */
    public String messageForError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the error messages when an error has occurred.
     * @param errorMessage The error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println("\t" + messageForError(errorMessage));
    }

    /**
     * Returns the message when a <code>Task</code> is marked.
     * @param task The task marked.
     * @return Message to prompt successful marking.
     */
    public String messageForMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns the message when a <code>Task</code> is unmarked.
     * @param task The task unmarked.
     * @return Message to prompt successful unmarking.
     */
    public String messageForUnmark(Task task) {
        return "OK, I've unmarked this task as not done yet:\n" + task;
    }

    /**
     * Returns the message when a <code>Task</code> is deleted.
     * @param task The task deleted.
     * @return Message to prompt successful deleting.
     */
    public String messageForDelete(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Returns the message of the list of <code>Task</code>
     * on the <code>TaskList</code> to be printed.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code>.
     * @return The details of the <code>Task</code> on the <code>TaskList</code>.
     */
    public String messageForList(TaskList taskList) {
        String showListMessage = "Here are the tasks in your list:\n";
        if (taskList.getSize() == 0) {
            showListMessage = showListMessage + "\nYou have no task in your list.";
        } else {
            showListMessage = showListMessage + taskList.list();
        }
        return showListMessage;
    }

    /**
     * Returns the message of the list of <code>Task</code> found.
     * @param taskList The <code>TaskList</code> that contains the <code>Task</code>.
     * @param search The search description.
     * @return The message of the list of <code>Task</code> found.
     */
    public String messageForSearches(TaskList taskList, String search) {
        String toLowerCaseSearch = search.toLowerCase(Locale.ROOT);
        String searchesMessage = "Here are the matching tasks in your list:\n";
        String noTask = "No matches found.";
        String searchResult = taskList.search(toLowerCaseSearch);
        boolean isEmptyString = searchResult.equals("");
        return !isEmptyString ? searchesMessage + searchResult : searchesMessage + noTask;
    }

    /**
     * Returns the message of the list of commands for this chatbot.
     * @return The application guide.
     */
    public String messageForHelp() {
        String helpMessage = "Here are the list of commands available:\n"
                + "Note that items inside <> are to be specified by you!\n\n";
        String instructions = Stream.of(Instruction.values())
                .map(Instruction::get)
                .collect(Collectors.joining("\n\n"));
        return helpMessage + instructions;
    }

    /**
     * Returns the message when an alias is added.
     * @param hasUpdated Whether the <code>commandDictionary</code> is updated.
     * @param alias The alias.
     * @param command The command.
     * @return The message of whether an alias has been added.
     */
    public String messageForMapping(boolean hasUpdated, String alias, String command) {
        String successMapping = "Nice! Your alias for " + command + " is " + alias;
        String failureMapping = "OOPS!! Your alias does not work!";
        return hasUpdated ? successMapping : failureMapping;
    }

    /**
     * Returns the default message.
     * @return The default message.
     */
    public String messageForDefault() {
        return ErrorType.INVALID_COMMAND.getErrorMessage();
    }

    /**
     * Converts date that has been input as <code>String</code> to a <code>DateTime</code> format.
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

package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Locale;

/**
 * This is the Parser class that obtains a sentence as input that
 * can be deciphered to perform commands in the Duke application
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-1
 */

public class Parser {
    protected String userInput;
    protected TaskList tasks;
    protected String[] listOfUserInputs;
    protected Storage storage;

    //Must be in SNAKE_CASE
    enum Commands {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, FIND, SORT, BYE
    }

    /**
     * Sets up the Parser instance.
     * @param list is the TaskList instance
     * @param storage is the Storage instance
     */
    public Parser(TaskList list, Storage storage) {
        tasks = list;
        this.storage = storage;
    }

    /**
     * First split of user input to obtain Command from other user inputs
     */
    public void splitUserInput() {
        listOfUserInputs = userInput.split(" ");
    }

    /**
     * Obtains the command from user input
     */
    public String obtainCommandWord() {
        return listOfUserInputs[0];
    }

    /**
     * Obtains description of Tasks. Applicable for creating Todo, Deadline and Event
     */
    public String obtainTitleOfTask() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        String firstWordInTitle;
        try {
            //Used to check if there is a description of the Task provided
            firstWordInTitle = listOfUserInputs[1];
        } catch (Exception e) {
            errorMessage.append("\nOOPS!!! The description of a Todo/Deadline/Event cannot be empty.\n");
            throw new DukeException(errorMessage.toString());
        }
        StringBuilder title = new StringBuilder();
        for (int i = 1; i < listOfUserInputs.length; i ++) {
            String currentWord = listOfUserInputs[i];
            if (currentWord.equals("/by") || currentWord.equals("/at")) {
                break;
            } else {
                title.append(listOfUserInputs[i]).append(" ");
            }
        }
        if (title.toString().equals("")) { //when there is no description provided to a Task
            errorMessage.append("\nOOPS!!! The description cannot be empty.\n");
            throw new DukeException(errorMessage.toString());
        }
        return title.toString().trim();
    }

    /**
     * Obtains date part of user input. Applicable for Deadline, Event.
     */
    public LocalDate obtainDate() {
        boolean hasClue = false; //such as '/by' or '/at'
        StringBuilder stringDate = new StringBuilder();
        for (int i = 1; i < listOfUserInputs.length; i ++) {
            String currentWord = listOfUserInputs[i];
            if (hasClue) {
                stringDate.append(listOfUserInputs[i]);
            }
            if (currentWord.equals("/by") || currentWord.equals("/at")) {
                hasClue = true;
            }
        }
        if (!hasClue) {
            throw new IllegalArgumentException("\nPlease provide a '/by' or '/at' seperator in between the title and date" +
                    "\nof your Deadline/Event task respectively! Please try running the command again!\n");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
            return LocalDate.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("\nThe date input provided does not comply to any correct standards\n" +
                    "Please follow this format instead: yyyy/MMMM/dd (eg. 2022/May/19)\n" +
                    "Please try running the command again!\n", userInput, 0);
        }
    }

    /**
     * Obtains TaskNumber from user input. Applicable for mark, unmark and delete
     */
    public int obtainTaskNumber() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        try {
            return Integer.valueOf(listOfUserInputs[1]);
        } catch (Exception e) {
            errorMessage.append("\nPlease provide the Task number to mark/unmark/delete!\n");
            throw new DukeException(errorMessage.toString());
        }
    }

    /**
     * Based on supplied command by user input, run the command
     * @param input by the user for Duke to process
     */
    public String parseInput(String input) throws DukeException, IOException {
        userInput = input;
        splitUserInput();
        Commands action;
        try {
            action = Commands.valueOf(obtainCommandWord().toUpperCase());
        } catch (IllegalArgumentException e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("\nOOPS!!! I'm sorry, but I don't know what that means :-(\n")
                    .append("Try another command!\n");
            throw new IllegalArgumentException(errorMessage.toString());
        }
        switch (action) {
            case TODO:
                return createToDoTask();
            case DEADLINE:
                return createDeadlineTask();
            case EVENT:
                return createEventTask();
            case LIST:
                return listOutTasks();
            case MARK:
                return markTaskAsDone();
            case UNMARK:
                return unmarkTask();
            case DELETE:
                return deleteTask();
            case FIND:
                return findTask();
            case SORT:
                return sortTasks();
            case BYE:
                terminateAndSaveProgram(storage);
                System.exit(0);
            default:
        }
        return "";
    }

    /**
     * Prints out Tasks present in the Duke application currently.
     */
    public String listOutTasks() {
        StringBuilder successMessage = new StringBuilder();
        successMessage.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getTaskListSize(); i ++) {
            successMessage.append(String.format("%o. ", i + 1));
            successMessage.append(tasks.getParticularTask(i).toString());
        }
        return successMessage.toString();
    }

    /**
     * Checking of the validity of TaskNumber provided by user.
     */
    public void checkTaskNumber() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        int taskNumber = obtainTaskNumber();
        if (taskNumber > tasks.getTaskListSize() || taskNumber < 1) {
            errorMessage.append("\nPlease provide the correct Task number! ");
            errorMessage.append("Maybe review the list of tasks first,\nAnd then execute the command for mark/unmark/delete!");
            throw new DukeException(errorMessage.toString());
        }
    }

    /**
     * Makes a call on TaskList's mark() to mark specified Task as done.
     */
    public String markTaskAsDone() {
        Task task;
        StringBuilder successMessage = new StringBuilder();
        successMessage.append("\nNice! I've marked this task as done: \n");
        try {
            checkTaskNumber();
            task = tasks.markTask(obtainTaskNumber());
            successMessage.append(task.toString());
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Makes a call on TaskList's unmark() to unmark specified Task as not done.
     */
    public String unmarkTask() {
        Task task;
        StringBuilder successMessage = new StringBuilder();
        successMessage.append("\nNice! I've unmarked this task: \n");
        try {
            checkTaskNumber();
            task = tasks.unmarkTask(obtainTaskNumber());
            successMessage.append(task.toString());
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Makes a call on TaskList's delete() to delete specified Task.
     */
    public String deleteTask() {
        StringBuilder successMessage = new StringBuilder();
        successMessage.append("Noted. I've removed this task: \n");
        try {
            checkTaskNumber();
            String deletedTask = tasks.deleteTask(obtainTaskNumber()).toString();
            successMessage.append(deletedTask);
            successMessage.append("Now you have ").append(tasks.getTaskListSize());
            successMessage.append(" tasks in the list.\n");
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if user input is valid, then
     * creates a Todo Task and adds into the Tasklist
     */
    public String createToDoTask() {
        StringBuilder successMessage = new StringBuilder();
        String title;
        try {
            title = obtainTitleOfTask();
        } catch (DukeException e) {
            return e.getMessage();
        }
        Task task = new ToDo(title, 0);
        tasks.addTask(task);
        successMessage.append("Added Todo: ").append(task.toString());
        return successMessage.toString();
    }

    /**
     * Checks if user input is valid, then
     * creates a Deadline Task and adds to the Tasklist
     */
    public String createDeadlineTask() {
        StringBuilder successMessage = new StringBuilder();
        String title = "";
        try {
            title = obtainTitleOfTask();
        } catch (DukeException e) {
            return e.getMessage();
        }
        try {
            Task task = new Deadline(title, 0, obtainDate());
            tasks.addTask(task);
            successMessage.append("Added Deadline: \n").append(task.toString());
            return successMessage.toString();
        } catch (IllegalArgumentException i) {
            return i.getMessage();
        }
        catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if user input is valid, then
     * creates an Event Task and adds to the Tasklist
     */
    public String createEventTask() {
        StringBuilder successMessage = new StringBuilder();
        String title = "";
        try {
            title = obtainTitleOfTask();
        } catch (DukeException e) {
            return e.getMessage();
        }
        try {
            Task task = new Event(title, 0, obtainDate());
            tasks.addTask(task);
            successMessage.append("Added Event: ").append(task.toString());
            return successMessage.toString();
        }  catch (IllegalArgumentException i) {
            return i.getMessage();
        } catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds Task with given clue word
     */
    public String findTask() {
        StringBuilder successMessage = new StringBuilder();
        StringBuilder noMatchMessage = new StringBuilder();
        String wordsProvided;
        try {
            successMessage.append("Here are the matching tasks in your list:\n");
            wordsProvided = obtainTitleOfTask();
            boolean hasMatch = false;
            for (int i = 0; i < tasks.getTaskListSize(); i ++) {
                Task task = tasks.getParticularTask(i);
                if (task.getName().toLowerCase().contains(wordsProvided.toLowerCase())) {
                    hasMatch = true;
                    successMessage.append(task.toString());
                }
            }
            if (hasMatch) {
                return successMessage.toString();
            } else {
                noMatchMessage.append("\nThere are no Tasks that match the string inputted!\n");
                return noMatchMessage.toString();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Sorts Tasks based on title of Task
     */
    private String sortTasks() {
        StringBuilder successMessage = new StringBuilder();
        Collections.sort(tasks.getTasks());
        successMessage.append("\nThe Tasks have been sorted successfully!\n");
        successMessage.append(listOutTasks());
        return successMessage.toString();
    }

    /**
     * Saves all content into file and stops the program
     */
    public String terminateAndSaveProgram(Storage storage) {
        StringBuilder successMessage = new StringBuilder();
        String byeMessage = "Bye. Hope to see you again soon!\n";
        successMessage.append(byeMessage);
        try {
            storage.saveAllTasks(tasks);
            storage.closeWriteFile();
            return successMessage.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

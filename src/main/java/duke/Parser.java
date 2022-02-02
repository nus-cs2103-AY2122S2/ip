package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * This is an Action class that obtains a sentence as input that
 * can be deciphered to create tasks in the duke.Duke system
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Parser {
    protected String userInput;
    protected TaskList tasks;
    protected String[] listOfUserInputs;
    protected Storage storage;
    String line = "\n____________________________________________________________\n";

    //Must be in SNAKE_CASE
    enum Commands {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, FIND, BYE
    }

    public Parser(TaskList list, Storage storage) {
        tasks = list;
        this.storage = storage;
    }

    /**
     * First split of user input to obtain Action word from other user input words
     */
    public void splitUserInput() {
        listOfUserInputs = userInput.split(" ");
    }

    /**
     * Obtains the Action word from user input
     */
    public String obtainCommandWord() {
        return listOfUserInputs[0];
    }

    /**
     * Obtains description of Tasks. Applicable for creating Todo, duke.Deadline and duke.Event
     */
    public String obtainTitleOfTask() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        String firstWordInTitle;
        try {
            firstWordInTitle = listOfUserInputs[1];
        } catch (Exception e) {
            errorMessage.append(line).append("☹ OOPS!!! The description of a Todo/Deadline/Event cannot be empty.");
            errorMessage.append(line);
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
            errorMessage.append(line).append("☹ OOPS!!! The description cannot be empty.").append(line);
            throw new DukeException(errorMessage.toString());
        }
        return title.toString().trim();
    }

    /**
     * Obtains date. Applicable for duke.Deadline, duke.Event
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
            throw new DateTimeParseException("The date input provided does not comply to any correct standards\n" +
                    "Please follow this format instead: yyyy/MMMM/dd (eg. 2022/May/19)\n" +
                    "Please try running the command again!\n", userInput, 0);
        }
    }

    /**
     * Obtains integer. Applicable for mark, unmark and delete
     */
    public int obtainTaskNumber() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        try {
            return Integer.valueOf(listOfUserInputs[1]);
        } catch (Exception e) {
            errorMessage.append(line).append("Please provide the Task number to mark/unmark/delete!").append(line);
            throw new DukeException(errorMessage.toString());
        }
    }

    /**
     * Based on supplied Action word, run the action
     */
    public String parseInput(String input) throws DukeException, IOException {
        userInput = input;
        splitUserInput();
        Commands action;
        try {
            action = Commands.valueOf(obtainCommandWord().toUpperCase());
        } catch (IllegalArgumentException e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(line).append("☹ OOPS!!! I'm sorry, but I don't know what that means :-(")
                    .append("\nTry another command!\n").append(line);
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
            case BYE:
                return terminateAndSaveProgram(storage);
            default:
        }
        return "";
    }

    /**
     * Makes a call to TaskList's printTasks()
     */
    public String listOutTasks() {
        StringBuilder successMessage = new StringBuilder();
        successMessage.append(line).append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getTaskListSize(); i ++) {
            successMessage.append(String.format("%o. ", i + 1));
            successMessage.append(tasks.getParticularTask(i).toString());
        }
        successMessage.append(line);
        return successMessage.toString();
    }

    public void checkTaskNumber() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        int taskNumber = obtainTaskNumber();
        if (taskNumber >= tasks.getTaskListSize() || taskNumber < 1) {
            errorMessage.append(line).append("Please provide the correct Task number!");
            errorMessage.append("Maybe review the list of tasks first,\nAnd then execute the command for mark/unmark/delete!").append(line);
            throw new DukeException(errorMessage.toString());
        }
    }

    /**
     * Makes a call on TaskList's mark()
     */
    public String markTaskAsDone() {
        Task task;
        StringBuilder successMessage = new StringBuilder();
        successMessage.append(line).append("Nice! I've marked this task as done: \n");
        try {
            checkTaskNumber();
            task = tasks.markTask(obtainTaskNumber());
            successMessage.append(task.toString()).append(line);
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Makes a call on duke.TaskList's unmark()
     */
    public String unmarkTask() {
        Task task;
        StringBuilder successMessage = new StringBuilder();
        successMessage.append(line).append("Nice! I've unmarked this task: \n");
        try {
            checkTaskNumber();
            task = tasks.unmarkTask(obtainTaskNumber());
            successMessage.append(task.toString()).append(line);
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Makes a call on duke.TaskList's delete()
     */
    public String deleteTask() {
        try {
            checkTaskNumber();
            StringBuilder successMessage = new StringBuilder();
            successMessage.append(line).append("Noted. I've removed this task: \n");
            successMessage.append(tasks.deleteTask(obtainTaskNumber()).toString()).append("\n");
            successMessage.append("Now you have ").append(tasks.getTaskListSize());
            successMessage.append(" tasks in the list.\n").append(line);
            return successMessage.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if user input is valid, then
     * creates a Todo duke.Task and adds into the list
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
        successMessage.append(line).append("Added Todo: ").append(task.toString()).append(line);
        return successMessage.toString();
    }

    /**
     * Checks if user input is valid, then
     * creates a Deadline Task and adds to the list
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
            successMessage.append(line).append("Added Deadline: \n").append(task.toString()).append(line);
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
     * creates a duke.Event duke.Task and adds to the list
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
            successMessage.append(line).append("Added Event: ").append(task.toString()).append(line);
            return successMessage.toString();
        }  catch (IllegalArgumentException i) {
            return i.getMessage();
        } catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds task with given clue word
     */
    public String findTask() {
        StringBuilder successMessage = new StringBuilder();
        StringBuilder noMatchMessage = new StringBuilder();
        String wordsProvided;
        try {
            successMessage.append(line).append("Here are the matching tasks in your list:\n");
            wordsProvided = obtainTitleOfTask();
            boolean hasMatch = false;
            for (int i = 0; i < tasks.getTaskListSize(); i ++) {
                Task task = tasks.getParticularTask(i);
                if (task.getName().toLowerCase().contains(wordsProvided)) {
                    hasMatch = true;
                    successMessage.append(task.toString());
                }
            }
            if (hasMatch) {
                successMessage.append(line);
                return successMessage.toString();
            } else {
                noMatchMessage.append(line).append("There are no Tasks that match the string inputted!").append(line);
                return noMatchMessage.toString();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves all content into file and stops the program
     */
    public String terminateAndSaveProgram(Storage storage) {
        StringBuilder successMessage = new StringBuilder();
        String byeMessage = "Bye. Hope to see you again soon!\n";
        successMessage.append(line).append(byeMessage).append(line);
        try {
            storage.saveAllTasks(tasks);
            storage.closeWriteFile();
            return successMessage.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

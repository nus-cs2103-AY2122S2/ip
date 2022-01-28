import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * This is an Action class that obtains a sentence as input that
 * can be deciphered to create tasks in the Duke system
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Parser {
    protected String userInput;
    protected TaskList tasks;
    protected String[] listOfUserInput;
    protected Storage storage;

    String line = "\n____________________________________________________________\n";
    enum Commands {
        todo, deadline, event, list, mark, unmark, delete, bye;
    }

    public Parser(String input, TaskList list, Storage store) {
        userInput = input;
        tasks = list;
        storage = store;
    }

    /**
     * First split of user input to obtain Action word from other user input words
     */
    public void splitUserInput() {
        listOfUserInput = userInput.split(" ");
    }

    /**
     * Obtains the Action word from user input
     */
    public String obtainCommandWord() {
        return listOfUserInput[0];
    }

    /**
     * Obtains title. Applicable for creating Todo, Deadline and Event
     */
    public String obtainTitle() throws DukeException {
        StringBuilder errorMessage = new StringBuilder();
        String firstWordInTitle = "";
        try {
            firstWordInTitle = listOfUserInput[1];
        } catch (Exception e) {
            errorMessage.append(line).append("☹ OOPS!!! The description of a Todo/Deadline/Event cannot be empty.\n");
            errorMessage.append(line);
            throw new DukeException(errorMessage.toString());
        }
        StringBuilder title = new StringBuilder();
        for (int i = 1; i < listOfUserInput.length; i ++) {
            String currentWord = listOfUserInput[i];
            if (currentWord.equals("/by") || currentWord.equals("/at")) {
                break;
            } else {
                title.append(listOfUserInput[i]).append(" ");
            }
        }
        return title.toString().trim();
    }

    /**
     * Obtains date. Applicable for Deadline, Event
     */
    public LocalDate obtainDate() {
        Boolean hasClue = false;
        StringBuilder stringDate = new StringBuilder();
        for (int i = 1; i < listOfUserInput.length; i ++) {
            String currentWord = listOfUserInput[i];
            if (hasClue) {
                stringDate.append(listOfUserInput[i]);
            }
            if (currentWord.equals("/by") || currentWord.equals("/at")) {
                hasClue = true;
            }
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
            LocalDate localDate = LocalDate.parse(stringDate, formatter);
            return localDate;
        } catch (DateTimeParseException e) {
            System.out.println("The date input provided does not comply to any correct standards\n" +
                    "Please follow this format instead: yyyy/MMMM/dd (eg. 2022/May/19)\n");
        }
        return null;
    }

    /**
     * Obtains integer. Applicable for mark, unmark and delete
     */
    public int obtainInteger() {
        return Integer.valueOf(listOfUserInput[1]);
    }

    /**
     * Based on supplied Action word, run the action
     */
    public void parse() throws DukeException, IOException {
        splitUserInput();
        Commands action = Commands.valueOf(obtainCommandWord());
        switch (action) {
            case todo:
                System.out.println(createToDoTask());
                break;
            case deadline:
                System.out.println(createDeadlineTask());
                break;
            case event:
                System.out.println(createEventTask());
                break;
            case list:
                System.out.println(listOutTasks());
                break;
            case mark:
                System.out.println(markTaskAsDone());
                break;
            case unmark:
                System.out.println(unmarkTask());
                break;
            case delete:
                System.out.println(deleteTask());
                break;
            case bye:
                terminateAndSaveProgram(storage);
                break;
            default:
                String s = line +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        line;
                throw new DukeException(s);
        }
    }

    /**
     * Makes a call on TaskList's printTasks()
     */
    public String listOutTasks() {
        StringBuilder res = new StringBuilder();
        res.append(line).append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getTaskListSize(); i ++) {
            res.append(String.format("%o. ", i + 1));
            res.append(tasks.getParticularTask(i).toString());
        }
        res.append(line);
        return res.toString();
    }

    /**
     * Makes a call on TaskList's mark()
     */
    public String markTaskAsDone() {
        StringBuilder res = new StringBuilder();
        res.append(line).append("Nice! I've marked this task as done: \n");
        Task task = tasks.markTask(obtainInteger());
        res.append(task.toString()).append(line);
        return res.toString();
    }

    /**
     * Makes a call on TaskList's unmark()
     */
    public String unmarkTask() {
        StringBuilder res = new StringBuilder();
        res.append(line).append("Nice! I've unmarked this task: \n");
        Task task = tasks.unmarkTask(obtainInteger());
        res.append(task.toString()).append(line);
        return res.toString();
    }

    /**
     * Makes a call on TaskList's delete()
     */
    public String deleteTask() throws DukeException {
        StringBuilder res = new StringBuilder();
        res.append(line).append("Noted. I've removed this task: \n");
        res.append(tasks.delete(obtainInteger()).toString()).append("\n");
        res.append("Now you have ").append(tasks.getTaskListSize());
        res.append(" tasks in the list.\n").append(line);
        return res.toString();
    }

    /**
     * Checks if user input is valid, then
     * creates a Todo Task and adds into the list
     */
    public String createToDoTask() throws DukeException {
        StringBuilder successMessage = new StringBuilder();
        String title = "";
        try {
            title = obtainTitle();
        } catch (DukeException e) {
            return e.getMessage();
        }
        Task task = new ToDo(title, 0);
        tasks.addTask(task);
        successMessage.append(line).append("Added Todo task successfully!\n").append(line);
        return successMessage.toString();
    }

    /**
     * Checks if user input is valid, then
     * creates a Deatasksine Task and adds to the list
     */
    public String createDeadlineTask() throws DukeException {
        StringBuilder successMessage = new StringBuilder();
        String title = "";
        try {
            title = obtainTitle();
        } catch (DukeException e) {
            return e.getMessage();
        }
        Task task = new Deadline(title, 0, obtainDate());
        tasks.addTask(task);
        successMessage.append("Added Deadline task successfully!\n");
        return successMessage.toString();
    }

    /**
     * Checks if user input is valid, then
     * creates a Event Task and adds to the list
     */
    public String createEventTask() throws DukeException {
        StringBuilder successMessage = new StringBuilder();
        String title = "";
        try {
            title = obtainTitle();
        } catch (DukeException e) {
            return e.getMessage();
        }
        Task task = new Event(title, 0, obtainDate());
        tasks.addTask(task);
        successMessage.append("Added Event task successfully!\n");
        return successMessage.toString();
    }

    /**
     * Saves all content into file and stops the program
     */
    public void terminateAndSaveProgram(Storage storage) throws IOException {
        StringBuilder successMessage = new StringBuilder();
        String byeMessage = "Bye. Hope to see you again soon!\n";
        successMessage.append(line).append(byeMessage).append(line);
        try {
            storage.saveAllTasks(tasks);
            storage.closeWriteFile();
            System.out.println(successMessage.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}

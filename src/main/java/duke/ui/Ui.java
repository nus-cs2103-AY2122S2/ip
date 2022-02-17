package duke.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ui {
    protected static final String DIVIDER = "--------------------------------------------";

    /** Print welcome message
     * @return String response
     */
    public static String printWelcomeMessage() {
        String response = "Hey "
                + System.getProperty("user.name")
                + "! What can I do for you today? :D\n"
                + "\nYou can type keyword 'help' for the list of commands!";
        return response;
    }

    /** Print exit message
     * @return String response
     */
    public static String printExitMessage() {
        String response = "Bye! Have a nice day! :)";
        return response;
    }

    /** Print add success message after task added
     * @param taskList list of tasks
     * @return String response
     */
    public static String printAddSuccess(TaskList taskList) {
        int lastTaskIndex = taskList.getTasks().size() - 1;
        String response = "Got it. I've added this task:\n"
                + taskList.getTasks().get(lastTaskIndex) + '\n'
                + "Now you have " + taskList.getTasks().size() + " task(s) in the list";
        return response;
    }

    /** Print add success message after task added
     * @param task specified task
     * @return String response
     */
    public static String printMarkSuccess(Task task) {
        String response = "Awesome great job! I've marked this task as complete:\n" + task;
        return response;
    }

    /** Print add success message after task added
     * @param task specified task
     * @return String response
     */
    public static String printUnmarkSuccess(Task task) {
        String response = "OK, I've unmarked this task as incomplete:\n" + task;
        return response;
    }

    /** Prints message to inform user no matching task with given keyword
     * @return String response
     */
    public static String printNoTaskFound() {
        String response = "No tasks found with given keyword. Please try again!";

        return response;
    }

    /**
     * Prints invalid command
     * @return response
     */
    public static String printInvalidCommand() {
        String response = "☹ OOPS!!! command is invalid, please try again!";

        return response;
    }

    /** Print result header for find command */
    public static String printFindResultHeader(String keyword, ArrayList<Task> tasks) {
        String response = "Here are the matching tasks in your list containing "
                + "\"" + keyword + "\"" + ":"
                + printTasks(tasks);

        return response;
    }

    /** Print result header for find command */
    public static String printReminderResultHeader(LocalDate date, ArrayList<Task> tasks) {
        String response = "Found them!\nReminder to do your task(s) on "
                + "\"" + date + "\"" + ":"
                + printTasks(tasks);

        return response;
    }

    /** Print result header for find command */
    public static String printHelpCommands() {
        String response = "Here are the commands available for you!\n"
                + "1. todo\n"
                + "2. event\n"
                + "3. deadline\n"
                + "4. mark/unmark\n"
                + "5. delete\n"
                + "6. find\n"
                + "7. list\n"
                + "8. reminder\n"
                + "9. help\n"
                + "10. bye\n";

        return response;
    }

    /**
     * Iterates and prints through the tasks
     *
     * @param tasks the ArrayList of tasks
     */
    public static String printTasks(ArrayList<Task> tasks) {
        int count = 1;
        String response = "";
        for (Task task : tasks) {
            response += "\n" + count + ". " + task;
            count++;
        }
        System.out.println(response);
        return response;
    }

    /**
     * Prints response when task is deleted
     * @param removedTask task to deleted
     * @return response
     */
    public static String printDeleteTask(String removedTask) {
        String response = "Noted. I've deleted this task:\n" + removedTask.toString();

        return response;
    }
    /**
     * Creates stage for UI
     * @param stage stage from Duke
     * @throws DukeException if error occurs when reading file
     */
    public void buildStage(Stage stage) throws DukeException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}

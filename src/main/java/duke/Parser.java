package duke;

import java.io.IOException;

/**
 * Parser class to read what the user inputs.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Find finder = new Find();

    /**
     * Constructs Parser object to read what user inputs.
     *
     * @param ui    Ui object for interacting with the user.
     * @param taskList  Tasklist object for storing the tasks.
     * @param storage   Storage object for loading and saving the tasks from/to local drive.
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Read user input and execute commands according to user input
     *
     * @param input User input.
     * @return Duke's reply to user input.
     * @throws IOException  If user inputs invalid command.
     */
    public String readUserInput(String input) throws IOException {
        if (input.startsWith("bye")) {
            return ui.exit();
        } else if (input.startsWith("list")) {
            return ui.list(taskList);
        } else if (input.startsWith("mark")) {
            int taskNum = Integer.parseInt(input.substring(5));
            Task markedTask = taskList.mark(taskNum);
            String outputLabel = ui.markTask(markedTask);
            storage.save(taskList);
            return outputLabel;
        } else if (input.startsWith("unmark")) {
            int taskNum = Integer.parseInt(input.substring(7));
            Task unmarkedTask = taskList.unmark(taskNum);
            String outputLabel = ui.unmarkTask(unmarkedTask);
            storage.save(taskList);
            return outputLabel;
        } else if (input.startsWith("delete")) {
            int taskNum = Integer.parseInt(input.substring(7));
            Task deletedTask = taskList.delete(taskNum);
            String outputLabel = ui.deleteTask(taskList, deletedTask);
            storage.save(taskList);
            return outputLabel;
        } else if (input.startsWith("todo")) {
            if (input.split(" ").length == 1) {
                return ui.throwError("todo");
            } else {
                String userInput = input.substring(5);
                taskList.addTodo(userInput);
                String outputLabel = ui.addTask(taskList);
                storage.save(taskList);
                return outputLabel;
            }
        } else if (input.startsWith("deadline")) {
            String userInput = input.substring(9);
            String[] strArr = userInput.split(" /by ");
            taskList.addDeadline(strArr[0], strArr[1]);
            String outputLabel = ui.addTask(taskList);
            storage.save(taskList);
            return outputLabel;
        } else if (input.startsWith("event")) {
            String userInput = input.substring(6);
            String[] strArr = userInput.split(" /at ");
            taskList.addEvent(strArr[0], strArr[1]);
            String outputLabel = ui.addTask(taskList);
            storage.save(taskList);
            return outputLabel;
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            TaskList matchingTasks = finder.find(taskList, keyword);
            return ui.listMatching(matchingTasks);
        } else {
            return ui.throwError("");
        }
    }
}

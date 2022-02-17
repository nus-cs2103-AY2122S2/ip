package duke;

import java.io.IOException;

import duke.task.Task;

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
     * Reads user input and execute commands according to user input.
     *
     * @param input User input.
     * @return  Duke's reply to user input.
     * @throws IOException  If user inputs invalid command.
     */
    public String readUserInput(String input) throws IOException {
        if (input.startsWith("bye") || input.startsWith("b")) {
            return ui.exit();
        } else if (input.startsWith("list") || input.startsWith("l")) {
            return ui.list(taskList);
        } else if (input.startsWith("mark")) {
            int taskNum = Integer.parseInt(input.substring(5));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleMarkTask(taskNum);
        } else if (input.startsWith("m")) {
            int taskNum = Integer.parseInt(input.substring(2));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleMarkTask(taskNum);
        } else if (input.startsWith("unmark")) {
            int taskNum = Integer.parseInt(input.substring(7));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleUnmarkTask(taskNum);
        } else if (input.startsWith("u")) {
            int taskNum = Integer.parseInt(input.substring(2));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleUnmarkTask(taskNum);
        } else if (input.startsWith("delete")) {
            int taskNum = Integer.parseInt(input.substring(7));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleDeleteTask(taskNum);
        } else if (input.startsWith("del")) {
            int taskNum = Integer.parseInt(input.substring(4));
            assert taskNum > 0 && taskNum <= taskList.getTaskList().size() : "taskNum out of bounds";
            return handleDeleteTask(taskNum);
        } else if (input.startsWith("todo") || input.startsWith("t")) {
            return handleTodoTask(input);
        } else if (input.startsWith("deadline")) {
            String userInput = input.substring(9);
            return handleDeadlineTask(userInput);
        } else if (input.startsWith("d")) {
            String userInput = input.substring(2);
            return handleDeadlineTask(userInput);
        } else if (input.startsWith("event")) {
            String userInput = input.substring(6);
            return handleEventTask(userInput);
        } else if (input.startsWith("e")) {
            String userInput = input.substring(2);
            return handleEventTask(userInput);
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            return handleFindTask(keyword);
        } else if (input.startsWith("f")) {
            String keyword = input.substring(2);
            return handleFindTask(keyword);
        } else {
            return ui.throwError("");
        }
    }

    /**
     * Marks task as done and updates the tasklist.
     *
     * @param taskNum   The task number to be marked as done.
     * @return  String to be printed.
     */
    public String handleMarkTask(int taskNum) throws IOException {
        Task markedTask = taskList.mark(taskNum);
        String outputLabel = ui.markTask(markedTask);
        storage.save(taskList);
        return outputLabel;
    }

    /**
     * Marks task as not done and updates the tasklist.
     *
     * @param taskNum   The task number to be marked as not done.
     * @return  String to be printed.
     */
    public String handleUnmarkTask(int taskNum) throws IOException {
        Task unmarkedTask = taskList.unmark(taskNum);
        String outputLabel = ui.unmarkTask(unmarkedTask);
        storage.save(taskList);
        return outputLabel;
    }

    /**
     * Deletes task and updates the tasklist.
     *
     * @param taskNum   The task number to delete.
     * @return  String to be printed.
     */
    public String handleDeleteTask(int taskNum) throws IOException {
        Task deletedTask = taskList.delete(taskNum);
        String outputLabel = ui.deleteTask(taskList, deletedTask);
        storage.save(taskList);
        return outputLabel;
    }

    /**
     * Adds a Todo task to the tasklist.
     *
     * @param userInput The user input containing the task information.
     * @return  String to be printed.
     */
    public String handleTodoTask(String userInput) throws IOException {
        if (userInput.split(" ").length == 1) {
            return ui.throwError("todo");
        } else {
            String input = userInput.substring(5);
            taskList.addTodo(input);
            String outputLabel = ui.addTask(taskList);
            storage.save(taskList);
            return outputLabel;
        }
    }

    /**
     * Adds a Deadline task to the tasklist.
     *
     * @param userInput The user input containing the task information.
     * @return  String to be printed.
     */
    public String handleDeadlineTask(String userInput) throws IOException {
        String[] strArr = userInput.split(" /by ");
        taskList.addDeadline(strArr[0], strArr[1]);
        String outputLabel = ui.addTask(taskList);
        storage.save(taskList);
        return outputLabel;
    }

    /**
     * Adds a Event task to the tasklist.
     *
     * @param userInput The user input containing the task information.
     * @return  String to be printed.
     */
    public String handleEventTask(String userInput) throws IOException {
        String[] strArr = userInput.split(" /at ");
        taskList.addEvent(strArr[0], strArr[1]);
        String outputLabel = ui.addTask(taskList);
        storage.save(taskList);
        return outputLabel;
    }

    /**
     * Finds and returns the tasks that contain the keyword.
     *
     * @param keyword   The keyword to search for.
     * @return  All the tasks that contain the keyword.
     */
    public String handleFindTask(String keyword) {
        TaskList matchingTasks = finder.find(taskList, keyword);
        return ui.listMatching(matchingTasks);
    }
}

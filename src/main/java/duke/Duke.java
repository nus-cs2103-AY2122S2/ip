package duke;

import parser.Parser;
import storage.IStorage;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Constructor for Duke class.
 * Main logic for Duke.
 */
public class Duke {
    public final IStorage storage;
    public TaskList taskList;
    public Ui ui;
    public Parser parser;
    private static final String BYE = "Bye! Hope to see you soon!";
    private static final int MARK_LENGTH = 5;
    private static final int UNMARK_LENGTH = 7;
    private static final int DELETE_LENGTH = 7;

    /**
     * Constructor for Duke that takes in saveFileDirectory and saveFileName and parsing it to Storage class.
     * @param saveFileDirectory save file directory for recorded tasks.
     * @param saveFileName save file name of the recorded tasks.
     */
    public Duke(String saveFileDirectory, String saveFileName) {
        this.storage = new Storage(saveFileDirectory, saveFileName);
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Another constructor for Duke which directly parse the input to storage attribute.
     * @param storage any object of IStorage class.
     */
    public Duke(IStorage storage) {
        this.storage = storage;
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * The main logic of the process of Duke.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("./data/", "TaskList.txt");
        duke.initialize();
        duke.run();
    }

    /**
     * Initialize Duke by reading from the default file.
     * Greet Users.
     * @throws DukeException
     */
    public void initialize() throws DukeException {
        this.taskList = storage.readFile();
    }

    /**
     * The main process of how Duke runs.
     */
    public void run() {
        while (true) {
            String userInput = this.ui.readUserInput();
            if (userInput.equals("bye")) {
                break;
            }
            ui.printMessage(processUserInput(userInput));
        }
    }

    /**
     * Method to write all current tasks to the default file via Storage.
     * @throws DukeException
     */
    public void writeTasks() throws DukeException {
        this.storage.writeToFile(this.taskList);
    }

    /**
     * Method to process user input and get what it means.
     * @param userInput user input in form of String.
     * @return response from the logic.
     */
    public String processUserInput(String userInput) {
        assert userInput != null;
        String[] userInputs = userInput.split(" ");
        String command = userInputs[0];
        String response;
        try {
            switch (command) {
            case "bye":
                if (userInput.equals("bye")) {
                    response = Duke.BYE;
                    return response;
                } else {
                    throw DukeException.DukeInvalidCommand();
                }

            case "list":
                if (userInput.equals("list")) {
                    response = this.taskList.toString();
                    return response;
                } else {
                    throw DukeException.DukeInvalidCommand();
                }

            case "mark":
                try {
                    int index = Integer.parseInt(userInput.substring(MARK_LENGTH)) - 1;
                    response = this.taskList.markTaskAsDone(index);
                    writeTasks();
                    return response;
                } catch (NumberFormatException e) {
                    throw DukeException.DukeInvalidIndex();
                } catch (IndexOutOfBoundsException e) {
                    throw DukeException.DukeInvalidIndex();
                } catch (DukeException d) {
                    throw d;
                }

            case "unmark":
                try {
                    int index = Integer.parseInt(userInput.substring(UNMARK_LENGTH)) - 1;
                    response = this.taskList.unmarkTaskAsDone(index);
                    writeTasks();
                    return response;
                } catch (NumberFormatException e) {
                    throw DukeException.DukeInvalidIndex();
                } catch (IndexOutOfBoundsException e) {
                    throw DukeException.DukeInvalidIndex();
                } catch (DukeException d) {
                    throw d;
                }

            case "delete":
                try {
                    int index = Integer.parseInt(userInput.substring(DELETE_LENGTH)) - 1;
                    response = this.taskList.deleteTaskAtIndex(index);
                    writeTasks();
                    return response;
                } catch (IndexOutOfBoundsException e) {
                    throw DukeException.DukeInvalidIndex();
                }

            case "find":
                try {
                    ArrayList<Task> matchingTasks = this.parser.findTasksByKeyword(userInput,
                            this.taskList.getTasks());
                    response = this.ui.getMatchingTasksMessage(matchingTasks);
                    return response;
                } catch (DukeException d) {
                    throw d;
                }

            default:
                Task task = this.parser.parseTaskFromUi(command, userInput);
                if (this.parser.isDuplicate(task, this.taskList.getTasks())) {
                    throw DukeException.DukeDuplicate();
                }
                response = this.taskList.addTask(task);
                writeTasks();
                return response;
            }
        } catch (DukeException d) {
             response = this.ui.getErrorMessage(d);
             return response;
        }
    }
}

package arthur;

import java.io.FileNotFoundException;
import java.io.IOException;

import arthur.exceptions.InvalidInstructionException;
import arthur.timings.DateTime;

/**
 * Handles decoding of the user commands
 */
public class Parser {
    private static final String BYE_MESSAGE = "Bye! \n" + "Have a great day!";
    private String command;

    /**
     * Constructor for the parser object
     *
     * @param inst String instruction to be followed
     */
    public Parser(String inst) {
        this.command = inst;
    }

    /**
     * Uses the command string to determine appropriate action.
     * If input is "list", will execute listOut method. If not, adds
     * instruction to tasks list.
     *
     * @param taskList The Tasklist of tasks and operations
     * @param storage  The object to access data file in storage
     * @return A string output of the result of execution
     */
    public String execute(TaskList taskList, Storage storage) throws InvalidInstructionException,
            IndexOutOfBoundsException, FileNotFoundException, IOException {
        String[] temp = this.command.split(" ", 2); // Helps to isolate the first word
        assert temp.length >= 1 : "Invalid command is executed";
        String inst = temp[0];
        switch (inst) {
        case "list":
            this.command = taskList.listOut();
            break;
        case "mark":
        case "unmark":
            this.command = taskList.marker(this.command);
            storage.editTasks(taskList.getTask(Integer.parseInt(temp[1]) - 1), 1);
            break;
        case "reminder":
            this.command = DateTime.checkDate(taskList);
            break;
        case "todo":
            this.command = taskList.todo(temp[1]);
            storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
            break;
        case "deadline":
            this.command = taskList.deadline(temp[1]);
            storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
            break;
        case "event":
            this.command = taskList.event(temp[1]);
            storage.addTasks(taskList.getTask(taskList.tasksSize() - 1));
            break;
        case "delete":
            storage.editTasks(taskList.getTask(Integer.parseInt(temp[1]) - 1), 2);
            this.command = taskList.deleter(Integer.parseInt(temp[1]));
            break;
        case "find":
            this.command = taskList.find(temp[1]);
            break;
        case "bye":
            this.command = BYE_MESSAGE;
            break;
        default:
            throw new InvalidInstructionException("Please enter the correct command");
        }
        return this.command;
    }
}

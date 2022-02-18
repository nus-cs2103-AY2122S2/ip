package duke;
import java.io.IOException;


/**
 * this class deals with interactions with the user
 * @author Kaiyi
 *
 */

public class Ui {

    private static final String DUKE_DIRECTORY = "data";
    private static final String DUKE_TXTFILE = "data/dukeSave.txt";
    private final TaskList taskList = new TaskList(DUKE_DIRECTORY, DUKE_TXTFILE);
    private final Storage storage = new Storage(DUKE_DIRECTORY, DUKE_TXTFILE);
    private boolean isExit = false;
    private final String separation = "\n__________________________________\n";
    private final String greeting = "Hello! I'm DukeTime\nWhat can I do for you?";


    /**
     */
    public Ui() {
    }
    /**
     * display welcome message everytime user logs in
     * @throws IOException
     */
    public String welcomeMsg() throws IOException {
        return greeting + separation
                + commandMessage() + taskList.writeToArrFromPrevData();
    }
    /**
     * @return strings of commands to guide user
     */
    public String commandMessage() {
        String todo = "todo: todo <task>";
        String deadLine = "deadline: deadline <task> /by <date><time>";
        String event = "event: event <task> /at <date><time>";
        String others = "others: delete, mark, unmark, find, search, list";
        return "List of commands: \n" + todo + "\n" + deadLine + "\n" + event + "\n" + others;
    }

    /**
     * for user to input command
     * this method will call other methods depending on the user's input
     * check if user input a valid commands and throw DukeException if commands are invalid
     * @exception DukeException
     * @throws IOException e
     */
    public String userCommand(String inp) throws IOException, DukeException {
        try {
            DukeException d = new DukeException();
            d.invalidCommands(inp);
        } catch (DukeException e) {
            System.err.println(e);
            isExit = true;
            return "command is invalid, please re-enter again";
        }

        String[] temp = inp.split(" ", 2);
        boolean isBye = inp.equals("bye");
        boolean isList = inp.equals("list");
        boolean isMark = temp[0].equals("mark");
        boolean isUnmark = temp[0].equals("unmark");
        boolean isTodo = temp[0].equals("todo");
        boolean isDeadine = temp[0].equals("deadline");
        boolean isEvent = temp[0].equals("event");
        boolean isDelete = temp[0].equals("delete");
        boolean isFind = temp[0].equals("find");
        boolean isSearch = temp[0].equals("search");
        if (isBye) {
            isExit = true;
            storage.reSavingFiles(taskList.getListOfInputs());
            return ("Bye. Hope to see you again soon!");
        } else if (isList) {
            return taskList.list();
        } else {
            String command = temp[1];
            if (isMark) {
                int currNo = Integer.parseInt(command) - 1;
                return taskList.mark(currNo);
            } else if (isUnmark) {
                int currNo = Integer.parseInt(command) - 1;
                return taskList.unMark(currNo);
            } else if (isTodo) {
                return taskList.toDo(command);
            } else if (isDeadine) {
                return taskList.deadLine(inp);
            } else if (isEvent) {
                return taskList.event(command);
            } else if (isDelete) {
                return taskList.delete(command);
            } else if (isFind) {
                return taskList.find(command);
            } else if (isSearch) {
                return taskList.search(command);
            } else {
                return "command is invalid, please re-enter again";
            }
        }
    }
}

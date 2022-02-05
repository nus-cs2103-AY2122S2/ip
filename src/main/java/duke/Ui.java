package duke;
import java.io.IOException;


/**
 * this class deals with interactions with the user
 * @author Kaiyi
 *
 */

public class Ui {

    private static final String DUKE_DIRECTORY = "C:\\DukeDirectory";
    private static final String DUKE_TXTFILE = "C:\\DukeDirectory\\DukeSave.txt";
    private TaskList taskList = new TaskList(DUKE_DIRECTORY, DUKE_TXTFILE);
    private Storage storage = new Storage(DUKE_DIRECTORY, DUKE_TXTFILE);
    public boolean isExit = false;

    private String separation = "\n******************************\n";
    private String greeting = "Hello! I'm Duke\nWhat can I do for you?";


    /**
     */
    public Ui() {
    }
    /**
     * display welcome message everytime user logs in
     * @throws IOException
     */
    public String welcomeMsg() throws IOException {
        return separation + greeting + separation + "\nYou past Todos:"
                + storage.readData(DUKE_TXTFILE) + taskList.writeToArrFromPrevData();
    }


    /**
     * for user to input command
     * this method will call other methods depending on the user's input
     * check if user input a valid commands and throw DukeException if commands are invalid
     * @exception DukeException
     * @throws IOException
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


        if (inp.equals("bye")) {
            isExit = true;
            storage.reSavingFiles(taskList.listOfInputs);
            return ("Bye. Hope to see you again soon!");
        } else if (inp.equals("list")) {
            return taskList.list();
        } else if (temp[0].equals("mark")) {
            int currNo = Integer.parseInt(temp[1]) - 1;
            return taskList.mark(currNo);
        } else if (temp[0].equals("unmark")) {
            int currNo = Integer.parseInt(temp[1]) - 1;
            return taskList.unMark(currNo);
        } else if (temp[0].equals("todo")) {
            return taskList.toDo(temp[1]);
        } else if (temp[0].equals("deadline")) {
            return taskList.deadLine(inp);
           // return "ok deadline";
        } else if (temp[0].equals("event")) {
            return taskList.event(temp[1]);
        } else if (temp[0].equals("delete")) {
            // DeleteCommand d = new DeleteCommand(DUKE_DIRECTORY,DUKE_TXTFILE);
            return taskList.delete(temp[1]);
        } else if (temp[0].equals("find")) {
            taskList.find(temp[1]);
            return "";
        } else {
            return "command is invalid, please re-enter again";
//            throw new DukeException(temp[0]);
        }
    }








}

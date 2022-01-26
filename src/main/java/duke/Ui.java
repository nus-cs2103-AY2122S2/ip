package duke;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * this class deals with interactions with the user
 * @author Kaiyi
 *
 */

public class Ui {

    private static final String DUKE_DIRECTORY = "C:\\DukeDirectory";
    private static final String DUKE_TXTFILE = "C:\\DukeDirectory\\DukeSave.txt";
    TaskList taskList = new TaskList(DUKE_DIRECTORY,DUKE_TXTFILE);
    Storage storage = new Storage(DUKE_DIRECTORY,DUKE_TXTFILE);
    public boolean isExit = false;

    public Ui() {
    }


    String greeting = "Hello! I'm Duke\nWhat can I do for you?";
    String separation = "\n******************************\n";

    /**
     * display welcome message everytime user logs in
     * @throws IOException
     */
    public void welcomeMsg() throws IOException {
        System.out.println(separation + greeting + separation + "\nYou past Todos:");
        storage.readData(DUKE_TXTFILE);
        taskList.writeToArrFromPrevData();
    }


    /**
     * for user to input command
     * this method will call other methods depending on the user's input
     * check if user input a valid commands and throw DukeException if commands are invalid
     * @exception DukeException
     * @throws IOException
     */
    public void userCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String inp = br.readLine();
            String[] temp = inp.split(" ", 2);

         try {
            DukeException d = new DukeException();
            d.invalidCommands(inp);
        } catch (DukeException e) {
            System.err.println(e);
            isExit = true;
        }


            if (inp.equals("bye")) {
                isExit = true;
                storage.reSavingFiles(taskList.list_of_inputs);
                System.out.println("Bye. Hope to see you again soon!");
            } else if (inp.equals("list")) {
                taskList.list();

            } else if (temp[0].equals("mark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
                taskList.mark(curr_no);
            } else if (temp[0].equals("unmark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
               taskList.unMark(curr_no);
            } else if (temp[0].equals("todo")) {
//                AddCommand c = new AddCommand(DUKE_DIRECTORY,DUKE_TXTFILE);
//                c.addCommand(temp[0],temp[1]);
                System.out.println(taskList.toDo(temp[1]));
            } else if (temp[0].equals("deadline")) {
                System.out.println(taskList.deadLine(temp[1]));
            } else if (temp[0].equals("event")) {
                System.out.println(taskList.event(temp[1]));
            } else if (temp[0].equals("delete")) {
              // DeleteCommand d = new DeleteCommand(DUKE_DIRECTORY,DUKE_TXTFILE);
               taskList.delete(temp[1]);
            }


        }






}

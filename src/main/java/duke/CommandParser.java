package duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Class to parse the command given to the chat bot
 to determine what tasks the chatbot is to complete.
 */
public class CommandParser {

    /**
     * Parses command to see what type of command it is
     *
     * @param command Command given by the user
     * @param storeList List of Tasks the chat bot restored from memory
     * @return Nothing
     * @throws IOException If command is blank
     */
    public static void parseCommand(String command, ArrayList<Task> storeList) throws IOException {
        if (command.equals("list")) {
            TaskList.printTheList(storeList);
        } else if (command.startsWith("mark ")) {
            TaskList.markCommand(command, storeList);
        } else if (command.startsWith("unmark ")) {
            TaskList.unmarkCommand(command, storeList);
        } else if (command.startsWith("deadline")) {
            TaskList.addDeadline(command, storeList);
        } else if (command.startsWith("todo")) {
            TaskList.addTodo(command, storeList);
        } else if (command.startsWith("event")) {
            TaskList.addEvent(command, storeList);
        } else if (command.startsWith("delete")) {
            TaskList.deleteTask(command, storeList);
        }  else if (command.startsWith("find")) {
            TaskList.findTask(command, storeList);
        } else {
            Ui.unknownCommand();
        }
    }
}

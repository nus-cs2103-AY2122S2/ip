import java.io.IOException;
import java.util.ArrayList;

public class CommandParser {

    public static void parseCommand(String command, ArrayList<Task> storeList) throws IOException {
        if (command.equals("bye")) {
            Ui.startGoodbye();
            Storage.saveList(storeList);
        } else if (command.equals("list")) {
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
        } else {
            Ui.unknownCommand();
        }
    }
}

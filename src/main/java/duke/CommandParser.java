package duke;

/**
 Class to parse the command given to the chatbot
 to determine what tasks the chatbot is to complete and gives
 the appropriate response.
 */
public class CommandParser {
    /**
     * Parses command to see what type of command it is
     *
     * @param command Command given by the user
     * @return String of response to command
     */
    public static String parseCommand(String command) {
        if (command.equals("list")) {
            return TaskList.printTheList(Duke.storeList);
        } else if (command.equals("hello") || command.equals("hi")) {
            return Ui.startGreeting();
        } else if (command.startsWith("mark ")) {
            return TaskList.markCommand(command, Duke.storeList);
        } else if (command.startsWith("unmark ")) {
            return TaskList.unmarkCommand(command, Duke.storeList);
        } else if (command.startsWith("deadline")) {
            return TaskList.addDeadline(command, Duke.storeList);
        } else if (command.startsWith("todo")) {
            return TaskList.addTodo(command, Duke.storeList);
        } else if (command.startsWith("event")) {
            return TaskList.addEvent(command, Duke.storeList);
        } else if (command.startsWith("delete")) {
            return TaskList.deleteTask(command, Duke.storeList);
        } else if (command.startsWith("find")) {
            return TaskList.findTask(command, Duke.storeList);
        } else if (command.startsWith("update")) {
            return TaskList.updateTask(command, Duke.storeList);
        } else {
            return Ui.unknownCommand();
        }
    }
}

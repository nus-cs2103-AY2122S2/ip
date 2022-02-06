package duke.duke;
public class Parser {

    public Parser() {
    }

    /**
     * Accepts the commands of the user input and resolves them. It searches the command string
     * and for the keyword and links them to all the tasks provided in TaskList. This will
     * allow the parser to direct the commandString's details to the respective command.
     *
     * @param commandString The String user input, inclusive of the command and details for Duke
     *                      to carry out.
     * @param tasks All the commands Duke can carry out. Initialized when Duke is declared.
     * @param storage All previously saved tasks. Initialized when Duke is declared
     * @return The output of the commands that the user has invoked.
     */
    public String parse(String commandString, TaskList tasks, Storage storage) {
        if (commandString.equals("bye")) {
            return storage.exit(tasks.arr);
        } else if (commandString.equals("list")) {
            return tasks.list(tasks.arr);
        } else if (commandString.contains("mark")) {
            return tasks.mark(commandString, tasks.arr);
        } else if (commandString.contains("delete")) {
            return tasks.delete(commandString, tasks.arr);
        } else if (commandString.contains("deadline")) {
            return tasks.deadline(commandString, tasks.arr);
        } else if (commandString.contains("event")) {
            return tasks.event(commandString, tasks.arr);
        } else if (commandString.contains("todo")) {
            return tasks.toDo(commandString, tasks.arr);
        } else if (commandString.contains("find")) {
            return tasks.find(commandString, tasks.arr);
        } else {
            return commandString + " is not a valid command. Please try again.";
        }
    }
}

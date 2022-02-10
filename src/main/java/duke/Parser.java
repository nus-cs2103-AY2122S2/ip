package duke;

/**
 * Represents a parser that can parse the commands sent to Duke and execute.
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Class constructor.
     *
     * @param taskList taskList to call commands on.
     * @param storage storage to save data to.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Waits for user commands, interprets them, and takes actions accordingly.
     */
    public String parse(String command) {
        try {
            if (command.startsWith("list")) {
                return taskList.list();
            } else if (command.startsWith("todo ")) {
                return taskList.addTodo(command.substring(5));
            } else if (command.startsWith("deadline ")) {
                return taskList.addDdl(command.substring(9));
            } else if (command.startsWith("event ")) {
                return taskList.addEvt(command.substring(6));
            } else if (command.startsWith("mark ")) {
                return taskList.mark(command.substring(5), true);
            } else if (command.startsWith("unmark ")) {
                return taskList.mark(command.substring(7), false);
            } else if (command.startsWith("delete ")) {
                return taskList.delete(command.substring(7));
            } else if (command.startsWith("find ")) {
                return taskList.find(command.substring(5));
            } else if (command.startsWith("bye")) {
                storage.writeFrom(taskList);
                return "Changes are saved. Hope to see you again soon!";
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}

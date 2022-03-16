package lucifer.commands;

import java.util.Arrays;

import lucifer.exceptions.InvalidException;
import lucifer.tasklist.TaskList;
import lucifer.ui.Ui;

/**
 * Class ParseCommands which encapsulates the command.
 * @author Yu Meng
 */
public class ParseCommands {
    /** A checker to check if it is within Lucifer.Lucifer chatbot commands **/
    private static final String[] WORD_COMMANDS = {"todo", "deadline", "event", "list", "mark",
                                                   "unmark", "delete", "find", "clear"};
    /** TaskList which stores the list of user tasks. **/
    private final TaskList task;

    /**
     * Constructor for Class ParseCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public ParseCommands(TaskList task) {
        this.task = task;
    }

    /**
     * Parses the command from input.
     * @return user outputs whether the input has been parsed.
     * @throws InvalidException if input does not match any of the commands.
     */
    public String parseCommand(String command) throws InvalidException {
        DeleteCommands deleter = new DeleteCommands(this.task);
        MarkCommands marker = new MarkCommands(this.task);
        FindCommands finder = new FindCommands(this.task);
        AddCommands adder = new AddCommands(this.task);
        String[] commands = command.split(" ");
        String theCommand = commands[0].toLowerCase();
        String output = null;
        if (command.equals("list")) {
            output = task.giveList();
        } else if (command.equals("!help")) {
            output = Ui.helpCommands();
        } else if (Arrays.stream(WORD_COMMANDS).anyMatch(command::contains)) {
            if (theCommand.equals("mark")) {
                output = marker.mark(command);
            } else if (theCommand.equals("unmark")) {
                output = marker.unMark(command);
            } else if (theCommand.equals("delete")) {
                output = deleter.delete(command);
            } else if (theCommand.equals("find")) {
                output = finder.find(command);
            } else if (theCommand.equals("todo")) {
                output = adder.addToDo(command);
            } else if (theCommand.equals("deadline")) {
                output = adder.addDeadline(command);
            } else if (theCommand.equals("event")) {
                output = adder.addEvent(command);
            } else if (theCommand.equals("clear")) {
                output = deleter.clear(command);
            }
        } else {
            throw new InvalidException();
        }
        return output;
    }
}

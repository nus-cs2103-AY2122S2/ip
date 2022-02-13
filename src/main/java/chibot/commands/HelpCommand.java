package chibot.commands;

import chibot.exception.ChiException;
import chibot.storage.Storage;
import chibot.tasklist.TaskList;

/**
 * Command for helping user navigate the bot.
 */
public class HelpCommand extends Command {

    /** Array containing words sent in the command */
    private final String[] tokens;

    /**
     * Constructor of the class.
     *
     * @param tokens Message sent by user in array format.
     */
    public HelpCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Returns a tailored help message to the user.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String of help message.
     * @throws ChiException If the user wants help on an invalid command.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        if (tokens.length == 1) {
            return printGenericHelpMessage();
        } else {
            return printSpecificHelpMessage(tokens[1]);
        }
    }

    /**
     * Prints help message to begin using the chat bot.
     *
     * @return A String of help message.
     */
    public String printGenericHelpMessage() {
        return "Chi is happy you are asking for help!\nDo go to the following link on how to get started!";
    }

    /**
     * Prints help message for a specific command.
     *
     * @param command The command specified.
     * @return A String of help message.
     * @throws ChiException If the user inputs an invalid command.
     */
    public String printSpecificHelpMessage(String command) throws ChiException {
        switch(command) {
        case "list":
            return "Type \"list\" to get a list of all your tasks!";
            // Fallthrough
        case "mark":
            return "Type \"mark index\" to mark the task at index as complete!\n E.g. mark 1";
            // Fallthrough
        case "unmark":
            return "Type \"unmark index\" to unmark the task at index as incomplete!\n E.g. unmark 1";
            // Fallthrough
        case "delete":
            return "Type \"delete index\" to remove a task at the index!\n E.g. delete 1";
            // Fallthrough
        case "find":
            return "Type \"find word1 word2 ...\" to find tasks which match ALL specified words! "
                    + "\nE.g. find new beach";
            // Fallthrough
        case "todo":
            return "Type \"todo task\" to create new todo task!\nE.g. todo write essay tonight";
        case "deadline":
            return "Type \"deadline description /by Y-M-D H:M\" to create new deadline!"
                    + "\nE.g.deadline finish report /by 2022-03-10 14:55";
            // Fallthrough
        case "event":
            return "Type \"event <description> /at Y-M-D H:M-H:M\" to "
                    + "create new event!\nE.g. event visit girlfriend /at 2022-10-10 12:00-18:00"
                    + "\nMake sure the timings are progressive!";
            // Fallthrough
        case "exit":
            return "To exit ChiBot, simply type \"bye\"! Don't worry, Chi will be waiting for you to return!";
            // Fallthrough
        default:
            break;
        }
        throw new ChiException("Chi can't help you with that weird command nyan!");
    }


}

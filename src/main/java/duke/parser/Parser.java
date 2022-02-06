package duke.parser;

import duke.commands.Command;
import duke.commands.Keywords;
import duke.exception.ChiException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.IOException;

/**
 * Interprets messages sent by the user.
 */
public class Parser {

    /**
     * Processes the message sent by the user into an appropriate response.
     *
     * @param msg The message sent by the user.
     * @param tl The TaskList which tasks will be added/deleted from.
     * @param sge The Storage component managing task data in hard-disk.
     * @return A String response to the message typed by the user.
     * @throws ChiException If there is something wrong with the format or content of the message.
     * @throws IOException If there is something wrong with the I/O during message processing.
     */
    public String processMessage(String msg, TaskList tl, Storage sge) throws ChiException, IOException {
        // Split the string by the space
        String[] messageFragments = msg.trim().split(" ");
        if (messageFragments.length == 0) {
            // User didn't type anything
            throw new ChiException("Hey it's not like I want you to...but can you type something nyan!");
        } else {
            // Check if message is valid command
            Keywords s = messageValidator(messageFragments);
            // Create a new command instance for the user command
            Command r = Command.of(s, messageFragments);
            // Execute the command
            return r.execute(tl, sge);
        }
    }

    /**
     * Checks if the message is an appropriate command.
     *
     * @param tokens An array of words in the message.
     * @return A Keyword enum of the command to be executed.
     * @throws ChiException If the command sent by the user is not in correct format.
     */
    public Keywords messageValidator(String[] tokens) throws ChiException {
        try {
            /* Check if the 1st word is a valid command
               @author WJunHong-reused
               Reused from https://github.com/mslevis/ip/tree/master/src/main/java/aoi/parser
               with modification

             */
            Keywords res = Keywords.getKeyword(tokens[0].toUpperCase());
            // Check if it is an add task or find command that has at least 1 word defined
            if ((res.equals(Keywords.ADD) || res.equals(Keywords.FIND)) && tokens.length < 2) {
                throw new ChiException("Hey this command is too short nyan!");
            } else if ((res.equals(Keywords.MARK) || res.equals(Keywords.UNMARK) || res.equals(Keywords.DELETE))
                    && tokens.length < 2) {
                throw new ChiException("Hey can you specify a number nyan!"); // Check if numeric commands have numbers
            } else if (res.equals(Keywords.LIST) && tokens.length > 2) {
                // Check if list has anything defined behind it
                throw new ChiException("Hey list doesn't take in so many arguments nyan!");
            }
            return res;
        } catch (ChiException e) {
            throw new ChiException(e.getMessage());
        }
    }
}

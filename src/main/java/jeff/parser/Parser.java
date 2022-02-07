package jeff.parser;

import jeff.main.JeffException;

import jeff.command.ByeCommand;
import jeff.command.Command;
import jeff.command.DeadlineCommand;
import jeff.command.DeleteCommand;
import jeff.command.EventCommand;
import jeff.command.HelpCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.TodoCommand;
import jeff.command.UnmarkCommand;

/**
 * Parser class is used to parse the raw input from the user
 * and call the correct Command class while handling exceptions.
 */
public class Parser {

    /**
     * Parses the user input and calls the correct command
     *
     * @param fullCommand Raw input from user.
     * @return Returns the command according to what the user wants.
     * @throws JeffException When certain information are missing, or formatting is wrong.
     */
    public static Command parse(String fullCommand) throws JeffException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String keyword = splitCommand[0];

        // To prevent NullPointerException for when body does not exist.
        String body = null;
        int len = splitCommand.length;
        if (len > 1) {
            body = splitCommand[1];
        }

        // Perform the correct instructions according to the keyword.
        switch(keyword) {
        case ("bye"):
            return new ByeCommand();
        case ("list") :
            return new ListCommand();
        case ("mark"):
            // When no index is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! Please tell me the task's"
                        + " index number so that I can mark it as done.");
            }
            return new MarkCommand(body);
        case ("unmark"):
            // When no index is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! Please tell me the task's"
                        + " index number so that I can mark it as not done.");
            }
            return new UnmarkCommand(body);
        case ("todo"):
            // When no description is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new TodoCommand(body);
        case ("deadline"):
            // When no description is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            String[] splitBody = body.split(" /by ", 2);
            String description = splitBody[0];

            // To prevent NullPointerException for when body does not exist.
            String dateInfo = null;
            int bodyLength = splitBody.length;
            if (bodyLength > 1) {
                dateInfo = splitBody[1];
            }

            // When no description or dateInfo are given.
            if (description.equals("") || description.equals(" ")) {
                throw new JeffException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (bodyLength == 1 || dateInfo.equals("") || dateInfo.equals(" ")) {
                throw new JeffException(" ☹ OOPS!!! Please input a due date for this task");
            } else {
                return new DeadlineCommand(description, dateInfo);
            }
        case ("event"):
            // When no description is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! The description of a event cannot be empty.");
            }
            splitBody = body.split(" /at ", 2);
            description = splitBody[0];

            // To prevent NullPointerException for when body does not exist.
            dateInfo = null;
            bodyLength = splitBody.length;
            if (bodyLength > 1) {
                dateInfo = splitBody[1];
            }

            // When no description or dateInfo are given.
            if (description.equals("") || description.equals(" ")) {
                throw new JeffException(" ☹ OOPS!!! The description of a event cannot be empty.");
            } else if (bodyLength == 1 || dateInfo.equals("") || dateInfo.equals(" ")) {
                throw new JeffException(" ☹ OOPS!!! Please input a due date for this task");
            } else {
                return new EventCommand(description, dateInfo);
            }
        case ("delete"):
            // When no description is given.
            if (len == 1) {
                throw new JeffException(" ☹ OOPS!!! Please tell me the task's"
                        + " index number so that I can delete it from the list.");
            }
            return new DeleteCommand(body);
        default:
            return new HelpCommand();
        }
    }
}

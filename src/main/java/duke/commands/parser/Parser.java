package duke.commands.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UpdateDeadlineCommand;
import duke.commands.UpdateEventCommand;
import duke.commands.UpdateNameCommand;
import duke.exceptions.DeadlineException;
import duke.exceptions.DukeException;
import duke.exceptions.EventException;
import duke.exceptions.ParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.format.DateTimeParseException;

/**
 * Represents a <code>Parser</code> which parses input strings
 * to <code>Duke</code> and returns the appropriate Command.
 */


public class Parser {

    /**
     * Default constructor.
     */
    public Parser() {}

    /**
     * Parses input string to return appropriate enum type.
     * @param input
     * @return The appropriate enum type.
     */
    public Command parse(String input) throws DukeException {
        assert !input.isEmpty() : "Given input should not be empty";
        if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            Todo toAdd = new Todo(input.split("todo")[1].trim());
            return new TodoCommand(toAdd);
        } else if (input.startsWith("mark")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 2) {
                throw new DukeException("Invalid format for mark. \n" +
                        "Correct format: mark <INDEX>\n" +
                        "Example: mark 3"
                );
            }
            if (!splitted[1].matches("\\d+$")) {
                throw new DukeException("Invalid format for mark. \n" +
                        "Correct format: mark <INDEX>\n" +
                        "Example: mark 3"
                );
            }
            int index = Integer.valueOf(splitted[1]);
            return new MarkCommand(index);
        } else if (input.startsWith("unmark")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 2) {
                throw new DukeException("Invalid format for unmark. \n" +
                        "Correct format: unmark <INDEX>\n" +
                        "Example: unmark 3"
                );
            }
            if (!splitted[1].matches("\\d+$")) {
                throw new DukeException("Invalid format for unmark. \n" +
                        "Correct format: unmark <INDEX>\n" +
                        "Example: unmark 3"
                );
            }
            int index = Integer.valueOf(splitted[1]);
            return new UnmarkCommand(index);
        } else if (input.startsWith("delete")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 2) {
                throw new DukeException("Invalid format for delete. \n" +
                        "Correct format: delete <INDEX>\n" +
                        "Example: delete 3"
                );
            }
            if (!splitted[1].matches("\\d+$")) {
                throw new DukeException("Invalid format for delete. \n" +
                        "Correct format: delete <INDEX>\n" +
                        "Example: delete 3"
                );
            }
            int index = Integer.valueOf(splitted[1]);
            return new DeleteCommand(index);
        } else if (input.startsWith("find")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 2) {
                throw new DukeException("Invalid format for find. \n" +
                        "Correct format: find <KEYWORD>\n" +
                        "Example: find library\n" +
                        "This will print any tasks that have keyword library in them."
                );
            }
            String keyword = splitted[1];
            return new FindCommand(keyword);
        } else if (input.startsWith("deadline")) {
            try {
                String[] splitted = input.split("deadline")[1].split("/by");

                if (splitted.length != 2) {
                    throw new DeadlineException();
                }

                if (splitted[0].trim().equals("")) {
                    throw new DeadlineException();
                }
                Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
                return new DeadlineCommand(toAdd);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DeadlineException();
            }
        } else if (input.startsWith("event")) {
            if (input.trim().equals("event")) {
                throw new EventException();
            }
            String[] splitted = input.split("event")[1].split("/at");
            if (splitted.length != 2) {
                throw new EventException();
            }
            String name = splitted[0].trim();
            if (name.equals("")) {
                throw new EventException();
            }
            String[] splittedTime = splitted[1].trim().split(" ");
            if (splittedTime.length != 4) {
                throw new EventException();
            }
            String startTime = splittedTime[0] + " " + splittedTime[1];
            String endTime = splittedTime[2] + " " + splittedTime[3];
            try {
                return new EventCommand(new Event(name, startTime, endTime));
            } catch (IndexOutOfBoundsException | DateTimeParseException | DukeException e) {
                if (e instanceof DukeException) {
                    throw e;
                } else {
                    throw new EventException();
                }
            }
        } else if (input.startsWith("update name")) {
            String[] splitted = input.split(" ");
            if (splitted.length <= 3) {
                throw new DukeException("Invalid format for update name. \n" +
                        "Correct format: update name <index> <newname>\n" +
                        "Example: update name 3 Borrow Book\n changes the name of the Task at index 3" +
                        " to Borrow Book."
                );
            }
            int index = Integer.valueOf(splitted[2]);
            String newName = "";
            for (int i = 3; i < splitted.length; i++) {
                newName += splitted[i] + " ";
            }
            newName = newName.trim();
            if (newName.equals("")) {
                throw new DukeException("Provided name cannot be empty!");
            }
            return new UpdateNameCommand(index, newName);
        } else if (input.startsWith("update deadline")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 5) {
                throw new DukeException("Invalid format for update deadline. \n" +
                        "Correct format: update deadline <index> <YYYY-MM-DD> <HHMM>\n" +
                        "Example: update deadline 3 2012-04-03 1500\n changes the deadline of the Task at index 3" +
                        " to 3rd April 2012 3PM.");
            }
            int index = Integer.valueOf(splitted[2]);
            String newDeadline = splitted[3] + " " + splitted[4];
            return new UpdateDeadlineCommand(index, newDeadline.trim());

        } else if (input.startsWith("update event time")) {
            String[] splitted = input.split(" ");
            if (splitted.length != 8) {
                throw new DukeException("Invalid format for update event time. \n" +
                        "Correct format: update event time <index> " +
                        "<FROM YYYY-MM-DD> <FROM HHMM> <TO YYYY-MM-DD> <TO HHMM>\n");
            }
            int index = Integer.valueOf(splitted[3]);
            String newStartTime = splitted[4] + " " + splitted[5];
            String newEndTime = splitted[6] + " " + splitted[7];
            return new UpdateEventCommand(index, newStartTime.trim(), newEndTime.trim());
        } else if (input.startsWith("bye")) {
            return new ByeCommand();
        } else {
            throw new ParseException("Invalid Command! See user guide for list of commands.");
        }
    }


}

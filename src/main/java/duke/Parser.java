package duke;
import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Parser for user input command
 */
public class Parser {


    /**
     * Parse all given String input to determine respective corresponding commands that the user is calling
     * @param input String user input command
     * @return Command corresponding to user input
     * @throws IOException
     * @throws DukeException
     * @throws DukeDeadlineException
     * @throws DukeEventException
     */
    public static Command parse(String input) throws IOException, DukeException, DukeDeadlineException, DukeEventException {
        String[] words = input.split(" ", 2);
        if (words[0].equals("bye")) {
            return new ByeCommand(); //ending sentence

        } else if (words[0].equals("list")) {
            return new ListCommand();

        } else if (words[0].equals("mark")) {
            if (words.length != 2) {
                throw new DukeEventException(
                        "OOPS!!! Please enter in format: mark <task_number>\n " +
                        "e.g. mark 1");
            } else {
                try {
                    int ranking = Integer.parseInt(words[1]);
                    return new MarkCommand(ranking);
                } catch (Exception e) {
                    throw new DukeException("Ranking should be a number! Call 'list' to see what tasks you have.");
                }
            }

        } else if (words[0].equals("unmark")) {
            if (words.length != 2) {
                throw new DukeEventException(
                        "OOPS!!! Please enter in format: unmark <task_number>\n " +
                                "e.g. unmark 1");
            } else {
                try {
                    int ranking = Integer.parseInt(words[1]);
                    return new UnmarkCommand(ranking);
                } catch (Exception e) {
                    throw new DukeException("Ranking should be a number! Call 'list' to see what tasks you have.");
                }
            }


        } else if (words[0].equals("delete")) {
            if (words.length != 2) {
                throw new DukeEventException(
                        "OOPS!!! Please enter in format: delete <task_number>\n " +
                                "e.g. delete 1");
            } else {
                try {
                    int ranking = Integer.parseInt(words[1]);
                    return new DeleteCommand(ranking);
                } catch (Exception e) {
                    throw new DukeException("Ranking should be a number! Call 'list' to see what tasks you have.");
                }
            }


        } else if (words[0].equals("todo")) {
            if (words.length != 2) {
                throw new DukeEventException(
                        "OOPS!!! Please enter in format: todo <task>\n " +
                                "e.g. todo sing a song");
            } else {
                Task task = new ToDo(words[1]);
                if (task == null) {
                    throw new DukeDeadlineException("Todo not added...");
                } else {
                    return new AddCommand(task);
                }
            }

        } else if (words[0].equals("event")) {
            if (words.length != 2) {
                throw new DukeEventException(
                        "OOPS!!! Please enter in format: event <event> /at <event venue> \n " +
                                "e.g. event Lesson /at Com1");
            } else {
                try {
                    Task task = Event.setEvent(words[1]);
                    if (task == null) {
                        throw new DukeDeadlineException("Event not added...");
                    } else {
                        return new AddCommand(task);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }

        } else if (words[0].equals("deadline")) {
            if (words.length != 2) {
                throw new DukeDeadlineException("OOPS!!! Please enter in format: deadline <task> /by <yyyy-mm-dd> \n " +
                        "e.g. deadline complete project /by 2022-12-24");
            } else {
                try {
                    Task task = Deadline.setDeadline(words[1]);
                    if (task == null) {
                        throw new DukeDeadlineException("Deadline not added...");
                    } else {
                        return new AddCommand(task);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }

        } else if (words[0].equals("delete")) {
            int ranking = Integer.parseInt(words[1]);
            return new DeleteCommand(ranking);

        } else if (words[0].equals("find")) {
            try {
                return new FindCommand(words[1]);
            } catch (Exception e) {
                throw new DukeException("I'm not sure what's happening. Please try again later!");
            }

        } else {
            throw new DukeException("Invalid Command!! Try something else hehe:)");
        }
    }
}

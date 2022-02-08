package duke.utils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Represent instance of Parser class
 * that deals with interpreting user commands
 */
public class Parser {

    /**
     * Interpret a user's input
     * and carries out the command
     *
     * @param userInput The current command to be parsed
     * @param tl Instance of the tasklist at this moment
     * @throws DukeException If the user's command is not valid
     */
    public static String parse(String userInput, TaskList tl) throws DukeException {

        // User Input cannot be Null
        assert userInput != null : "assertion error";

        StringTokenizer st = new StringTokenizer(userInput, " ");
        String curr = st.nextToken();


        switch (curr) {

        case "list":

            return tl.printList();

        case "mark":

            try {
                int toMark = Integer.parseInt(st.nextToken());
                return tl.markTaskAsCompleted(toMark);
            } catch (DukeException | NumberFormatException e) {
                throw new DukeException.DukeInvalidNumberException();
            }

        case "unmark":

            try {
                int toUnmark = Integer.parseInt(st.nextToken());
                return tl.markTaskAsUncomplete(toUnmark);
            } catch (DukeException | NumberFormatException e) {
                throw new DukeException.DukeInvalidNumberException();
            }

        case "todo":

            try {
                return tl.addToDo(st.nextToken(""));
            } catch (NoSuchElementException e) {
                throw new DukeException.DukeNoTaskGivenException();
            }

        case "deadline":

            try {
                userInput = userInput.replace(curr, "");
                String[] spl = userInput.split("/by ");
                if (spl.length <= 1) {
                    throw new DukeException.DukeNoTimeProvided();
                }
                return tl.addDeadline(spl[0], spl[1]);
            } catch (DukeException e) {
                throw e;
            }



        case "event":

            try {
                userInput = userInput.replace(curr, "");
                String[] splo = userInput.split("/at ");
                if (splo.length <= 1) {
                    throw new DukeException.DukeNoTimeProvided();
                }
                return tl.addEvent(splo[0], splo[1]);
            } catch (DukeException e) {
                throw e;
            }



        case "delete":

            try {
                int toDelete = Integer.parseInt(st.nextToken());
                return tl.deleteTask(toDelete);
            } catch (NumberFormatException | DukeException e) {
                throw new DukeException.DukeInvalidNumberException();
            }



        case "find":

            try {
                return tl.findEvent(st.nextToken(""));
            } catch (NoSuchElementException e) {
                throw new DukeException.DukeNoTaskGivenException();
            }



        case "bye":

            return Ui.printBye();


        default:
            throw new DukeException.DukeInvalidCommandException();
        }
    }
}

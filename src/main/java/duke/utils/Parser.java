package duke.utils;

import java.util.StringTokenizer;

/**
 * Represent instance of Parser class
 * that deals with interpreting user commands
 */
public class Parser {

    /**
     * Interpret a user's input
     * and calls the relevant command
     *
     * @param userInput The current command to be parsed
     * @param tl Instance of the tasklist at this moment
     * @param cd Instance of command class to call relevant command
     * @return Response for givne user input
     * @throws DukeException If the user's command is not valid
     */
    public static String parse(String userInput, TaskList tl, Command cd) throws DukeException {

        // User Input cannot be Null
        assert userInput != null : "assertion error";

        StringTokenizer st = new StringTokenizer(userInput, " ");
        String curr = st.nextToken();

        switch (curr) {

        case "list":
            return tl.printList();
        case "mark":
            return cd.markTaskComplete(tl, st);
        case "unmark":
            return cd.markTaskIncomplete(tl, st);
        case "todo":
            return cd.createToDo(tl, st);
        case "deadline":
            return cd.createDeadline(userInput, tl, curr);
        case "event":
            return cd.createEvent(userInput, tl, curr);
        case "delete":
            return cd.deleteTask(tl, st);
        case "find":
            return cd.findTask(tl, st);
        case "sort":
            return cd.sortTasks(tl, st);
        case "bye":
            return cd.handleBye(st);
        default:
            throw new DukeException.DukeInvalidCommandException();
        }
    }




}

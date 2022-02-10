package duke.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.AddTask;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodayTask;
import duke.commands.TryAgain;
import duke.commands.UnMarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.WrongFormatException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;


public class DukeParser {

    /**
     * Reads the data from the specified file,
     * parses the content into Tasks and stores
     * it in an ArrayList
     * @param f File to be parsed
     * @return ArrayList containing Tasks parsed from f
     * @throws IOException
     */
    public static ArrayList<Task> readData(File f) throws IOException {
        Scanner s = new Scanner(f);
        ArrayList<Task> ans = new ArrayList<>();
        while (s.hasNext()) {
            Task j;
            String[] k = s.nextLine().split("\\|");
            switch (k[0]) {
            case "T" :
                j = new ToDos(k[2]);
                break;
            case "D" :
                j = new Deadlines(k[2], k[3]);
                break;
            case "E" :
                j = new Events(k[2], k[3]);
                break;
            default :
                throw new IOException();
            }
            if (k[1].equals("true")) {
                j.mark();
            }
            ans.add(j);
        }
        return ans;
    }

    /**
     * Checks whether the String argument is
     * in the LocalDate format "yyyy-mm-dd".
     * @param k String to be checked
     * @return Boolean which tells if String is in valid format
     */
    public static boolean isValidDate(String k) {
        try {
            LocalDate d = LocalDate.parse(k);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the String argument is a valid Integer.
     * Returns true if it is an Integer, false if not
     * @param i String to be checked
     * @return Boolean which tells if String is Integer
     */
    public static boolean isInt(String i) {
        try {
            int n = Integer.parseInt(i);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the input is in the right format for Bye command
     * @param arg user input
     * @return Bye command
     * @throws DukeException
     */
    private static Command checkByeFormat(String[] arg) throws DukeException {
        if (arg.length == 1) {
            return new ExitCommand();
        } else {
            throw new WrongFormatException();
        }
    }

    /**
     * Check whether the input is in the right format for the mark,unmark and delete command
     * @param arg User input
     * @param cmd The command to return
     * @return Mark, Unmark or Delete command
     * @throws DukeException
     */
    private static Command checkMarkUnmarkDelete(String[] arg, String cmd) throws DukeException {
        Command curr;
        if (arg.length == 1) {
            throw new MissingArgumentException();
        } else if (!isInt(arg[1])) {
            throw new WrongFormatException();
        }
        switch (cmd) {
        case "mark" :
            curr = new MarkCommand(Integer.parseInt(arg[1].trim()));
            break;
        case "unmark" :
            curr = new UnMarkCommand(Integer.parseInt(arg[1].trim()));
            break;
        case "delete" :
            curr = new DeleteCommand(Integer.parseInt(arg[1].trim()));
            break;
        default :
            throw new MissingArgumentException();
        }
        return curr;
    }

    /**
     * Checks whether the Today or List command is in the right format. One word with no other arguments
     * @param arg user input
     * @param cmd command
     * @return TodayTask or List Command
     * @throws DukeException
     */
    private static Command checkTodayList(String[] arg, String cmd) throws DukeException {
        if (arg.length != 1) {
            throw new WrongFormatException();
        }
        switch (cmd) {
        case "today" :
            return new TodayTask();
        case "list" :
            return new ListCommand();
        default :
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks whether the Find or Todo command is in the right format.
     * Input must be the command, followed by an argument
     * @param arg User input
     * @param cmd Command to be checked
     * @return Find or Todo command
     * @throws DukeException
     */
    private static Command checkFindTodo(String[] arg, String cmd) throws DukeException {
        if (arg.length == 1) {
            throw new MissingArgumentException();
        }
        switch (cmd) {
        case "find" :
            return new FindCommand(arg[1]);
        case "todo" :
            return new AddTask(cmd, arg[1]);
        default :
            throw new InvalidCommandException();
        }
    }

    private static Command checkDeadlineEvent(String[] arg, String cmd) throws DukeException {
        String regex;
        String term;
        switch (cmd) {
        case "deadline" :
            regex = ".+/by.+";
            term = "/by";
            break;
        case "event" :
            regex = ".+/at.+";
            term = "/at";
            break;
        default :
            throw new InvalidCommandException();
        }

        if (arg.length == 1) {
            throw new MissingArgumentException();
        }

        if (!arg[1].matches(regex)) {
            throw new WrongFormatException();
        }

        String[] body = arg[1].split(term);
        if (!isValidDate(body[1].trim())) {
            throw new InvalidDateException();
        }

        return new AddTask(cmd, body[0], body[1].trim());
    }

    /**
     * Creates a Command object depending on the String arguments.
     * It returns a Command object corresponding to the String cmd, if the arguments
     * are in the right format.
     * @param arg Input from user split into a String array of length 2,
     *            where String[0] is the command and String[1] is the command body
     * @param cmd The string corresponding to a Command type
     * @return A Command instance
     * @throws DukeException If the argument is invalid or missing arguments
     */
    private static Command createCommand(String[] arg, String cmd) throws DukeException {
        Command curr;
        switch (cmd) {
        case "bye" :
            curr = checkByeFormat(arg);
            break;
        case "mark" :
            //Fallthrough
        case "unmark" :
            //Fallthrough
        case "delete" :
            curr = checkMarkUnmarkDelete(arg, cmd);
            break;
        case "today" :
            //Fallthrough
        case "list" :
            curr = checkTodayList(arg, cmd);
            break;
        case "find" :
            //Fallthrough
        case "todo" :
            curr = checkFindTodo(arg, cmd);
            break;
        case "deadline" :
            //Fallthrough
        case "event" :
            curr = checkDeadlineEvent(arg, cmd);
            break;
        default :
            throw new InvalidCommandException();
        }
        return curr;
    }

    /**
     * Parses the raw input from the user and returns a Command instance
     * @param inp String input from user
     * @return A Command instance
     */
    public static Command parseInput(String inp) {
        String[] arg = inp.split(" ", 2);
        try {
            Command c = createCommand(arg, arg[0]);
            assert c != null : "Command to return cannot be null";
            return c;
        } catch (DukeException e) {
            return new TryAgain(e.getMessage());
        }
    }

}

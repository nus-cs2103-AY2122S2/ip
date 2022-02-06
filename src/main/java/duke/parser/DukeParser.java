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
        ArrayList<Task> ans = new ArrayList<Task>();
        while (s.hasNext()) {
            String[] k = s.nextLine().split("\\|");
            if (k[0].equals("T")) {
                Task j = new ToDos(k[2]);
                if (k[1].equals("true")) {
                    j.mark();
                }
                ans.add(j);
            } else if (k[0].equals("D")) {
                Task j = new Deadlines(k[2], k[3]);
                if (k[1].equals("true")) {
                    j.mark();
                }
                ans.add(j);
            } else if (k[0].equals("E")) {
                Task j = new Events(k[2], k[3]);
                if (k[1].equals("true")) {
                    j.mark();
                }
                ans.add(j);
            }
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
            if (arg.length == 1) {
                curr = new ExitCommand();
                break;
            } else {
                throw new WrongFormatException();
            }
        case "mark" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])) {
                throw new WrongFormatException();
            } else {
                curr = new MarkCommand(Integer.parseInt(arg[1].trim()));
                break;
            }
        case "unmark" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])) {
                throw new WrongFormatException();
            } else {
                curr = new UnMarkCommand(Integer.parseInt(arg[1].trim()));
                break;
            }
        case "delete" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else if (!isInt(arg[1])) {
                throw new WrongFormatException();
            } else {
                curr = new DeleteCommand(Integer.parseInt(arg[1].trim()));
                break;
            }
        case "today" :
            if (arg.length == 1) {
                curr = new TodayTask();
                break;
            } else {
                throw new WrongFormatException();
            }
        case "list" :
            if (arg.length == 1) {
                curr = new ListCommand();
                break;
            } else {
                throw new WrongFormatException();
            }
        case "todo" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else {
                curr = new AddTask(cmd, arg[1]);
                break;
            }
        case "deadline" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else if (!arg[1].matches(".+/by.+")) {
                throw new WrongFormatException();
            } else {
                String[] body = arg[1].split("/by");
                if (!isValidDate(body[1].trim())) {
                    throw new InvalidDateException();
                } else {
                    curr = new AddTask(cmd, body[0], body[1].trim());
                    break;
                }
            }
        case "event" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else if (!arg[1].matches(".+/at.+")) {
                throw new WrongFormatException();
            } else {
                String[] body = arg[1].split("/at");
                if (!isValidDate(body[1].trim())) {
                    throw new InvalidDateException();
                } else {
                    curr = new AddTask(cmd, body[0], body[1].trim());
                    break;
                }
            }
        case "find" :
            if (arg.length == 1) {
                throw new MissingArgumentException();
            } else {
                curr = new FindCommand(arg[1]);
                break;
            }
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
            return c;
        } catch (DukeException e) {
            return new TryAgain(e.getMessage());
        }
    }

}

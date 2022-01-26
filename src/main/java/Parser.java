import java.util.Scanner;

public class Parser {
    public enum RESULT {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        ERROR
    }

    public Parser() {
    }

    public RESULT parseInput(String input) {
        String[] split = input.split(" ");
        if (input.equals("bye")) {
            return RESULT.BYE;
        } else if (input.equals("list")) {
            return RESULT.LIST;
        } else if (split[0].equals("mark")) {
            return RESULT.MARK;
        } else if (split[0].equals("unmark")) {
            return RESULT.UNMARK;
        } else if (split[0].equals("delete")) {
            return RESULT.DELETE;
        } else if (split[0].equals("todo")) {
            return RESULT.TODO;
        } else if (split[0].equals("deadline")) {
            return RESULT.DEADLINE;
        } else if (split[0].equals("event")) {
            return RESULT.EVENT;
        }
        return RESULT.ERROR;
    }

    public int parseIndex(String input) throws DukeException {
        String[] split = input.split(" ");
        try {
            if (split.length < 2) {
                throw new DukeException("What is this? Missing number!");
            } else {
                return Integer.parseInt(split[1]) - 1;
            }
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number");
        }
    }

    public String parseTodo(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of event cannot be empty.");
        } else {
            return input.replace("todo ", "");
        }
    }

    public String[] parseDeadline(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of deadline cannot be empty.");
        } else {
            String[] newSplit = input.split(" /by ");
            if (newSplit.length > 1) {
                newSplit[0] = newSplit[0].replace("deadline ", "");
                return newSplit;
            } else {
                throw new DukeException("You are missing the date!.");
            }
        }
    }
    public String[] parseEvent(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of deadline cannot be empty.");
        } else {
            String[] newSplit = input.split(" /at ");
            if (newSplit.length > 1) {
                newSplit[0] = newSplit[0].replace("event ", "");
                return newSplit;
            } else {
                throw new DukeException("You are missing the date!.");
            }
        }
    }

}

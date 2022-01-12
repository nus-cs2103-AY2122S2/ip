package duke.parser;

import duke.main.DukeException;
import duke.command.*;
import duke.task.*;

public class Parser {
    public static Command parse(String cmd) throws DukeException {
        String[] input = cmd.split(" ");
        switch (input[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
            case "deadline":
            case "event":
                return Parser.prepareAdd(input);
            case "mark":
            case "unmark":
                return Parser.prepareMark(input);
            case "delete":
                return Parser.prepareDelete(input);
//            case "findDate":
//                if (input.length != 2) {
//                    throw new duke.main.DukeException("Fill in proper integer to find date.\n");
//                }
//                for (Task task : todo) {
//                    if (task.sameTime(input[1])) {
//                        System.out.println(task);
//                    }
//                }
//                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static Command prepareAdd(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " + input[0] + " cannot be empty.\n");
        }

        if (input[0].equals("todo")) {
            StringBuilder obj = new StringBuilder("");
            for (int i = 1; i < input.length; i++) {
                obj.append(input[i]);
                obj.append(" ");
            }
            return new AddCommand(new ToDos(obj.toString()));
        } else {
            int i = 1;
            StringBuilder obj = new StringBuilder("");
            for (; i < input.length; i++) {
                if (input[i].charAt(0) == '/') break;
                obj.append(input[i]);
                obj.append(" ");
            }
            obj.setLength(obj.length()-1);

            if (input[0].equals("deadline")) {
                return new AddCommand(new DeadLine(obj.toString(), input[input.length-2], input[input.length-1]));
            } else {
                return new AddCommand(new Events(obj.toString(), input[input.length-3], input[input.length-2], input[input.length-1]));
            }
        }
    }

    public static Command prepareMark(String[] input) throws DukeException {
        if (input.length != 2) {
            throw new DukeException("Fill in proper integer for marking/unmarking.\n");
        }
        if (input[0].equals("mark")) {
            return new MarkCommand(Integer.parseInt(input[1]), true);
        } else {
            return new MarkCommand(Integer.parseInt(input[1]), false);
        }
    }

    public static Command prepareDelete(String[] input) throws DukeException {
        if (input.length != 2) {
            throw new DukeException("Fill in proper integer for deletion.\n");
        }
        return new DeleteCommand(Integer.parseInt(input[1]));
    }
}

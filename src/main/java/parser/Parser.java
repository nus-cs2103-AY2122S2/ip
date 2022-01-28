package parser;

import commands.*;
import common.NoGoodException;
import common.Type;
import data.Task;

public class Parser {

    /**
     * Parse the user input to a command if the input is valid.
     * @param input The text that the user input.
     **/
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ") && (input.length() == 6 || input.length() == 7)) {
            try {
                int i = Integer.parseInt(input.split("\\s+")[1]) - 1;
                return new MarkCommand(i);
            } catch (NumberFormatException e) {
                return new PrintCommand("Please enter a number after mark! (E.g. mark 2)");
            } catch (IndexOutOfBoundsException e) {
                return new PrintCommand("Please enter a valid number!");
            }
        } else if ((input.startsWith("delete ") && input.length() == 8 || input.length() == 9)) {
            try {
                int i = Integer.parseInt(input.split("\\s+")[1]) - 1;
                return new DeleteCommand(i);
            } catch (NumberFormatException e) {
                return new PrintCommand("Please enter a number after delete! (E.g. delete 2)");
            } catch (IndexOutOfBoundsException e) {
                return new PrintCommand("Please enter a valid number!");
            }
        } else if ((input.startsWith("search ") && input.length() > 7)) {
            String prefix = input.split("\\s+")[1];
            return new SearchCommand(prefix);
        } else {
            try {
                Task t;
                if (input.startsWith("todo ")) t = new Task(input.split(" ", 2)[1], Type.TODO);
                else if (input.startsWith("event ")) {
                    t = new Task(input.split(" /")[0].split(" ", 2)[1], Type.EVENT);
                    t.setTime(input.split("/at ", 2)[1]);
                } else if (input.startsWith("deadline ")) {
                    t = new Task(input.split(" /")[0].split(" ", 2)[1], Type.DEADLINE);
                    t.setTime(input.split("/by ", 2)[1]);
                } else {
                    throw new NoGoodException("");
                }
                return new AddCommand(t);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new PrintCommand("Your expression of time is not valid");
            } catch (NoGoodException e) {
                return new PrintCommand("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}

package baron.commands;

import baron.exceptions.BaronException;

public class CommandParser {
    public static int parseTaskIntArg(String arg) throws BaronException {
        if (arg.isBlank()) {
            throw new BaronException("Please enter the index of the task!");
        }

        int intVal;
        try {
            intVal = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new BaronException("Invalid argument type, please give a valid integer!");
        }

        return intVal;
    }
}

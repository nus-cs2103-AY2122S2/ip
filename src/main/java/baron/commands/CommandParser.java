package baron.commands;

import baron.exceptions.BaronException;

public class CommandParser {
    public static int parseIntArg(String arg) throws BaronException {
        int intVal;
        try {
            intVal = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new BaronException("Invalid argument type, please give a valid integer");
        }

        return intVal;
    }
}

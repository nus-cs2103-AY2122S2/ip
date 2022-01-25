import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

public class Parser {

    public Parser() {
    }

    protected String parseCommand(String s) throws WrongInputException {
        String[] inputs = s.split(" ");
        String[] acceptableInputs = new String[]{"mark","unmark","todo","deadline","event","delete"};
        boolean isAcceptable = false;
        for (String acceptableInput : acceptableInputs) {
            if (acceptableInput.equals(inputs[0])) {
                isAcceptable = true;
                break;
            }
        }
        if (!isAcceptable) {
            throw new WrongInputException("D: D: D: I don't understand the input D: D: D:");
        }
        return inputs[0];
    }

    protected int parseNumericalDescription(String s, String command, int length) throws WrongInputException {
        String[] inputs = s.split(command + " ");
        if (length == 0) {
            throw new WrongInputException("D: D: D: There are no items in the list D: D: D:");
        }
        if (inputs.length > 2) {
            throw new WrongInputException("D: D: D: The " + command +
                    " should be followed by a single number D: D: D:");
        }
        int tasknumber;
        try {
            tasknumber = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("D: D: D: The input doesn't seem to contain a number D: D: D:");
        }
        if (tasknumber > length || tasknumber < 1) {
            throw new WrongInputException("D: D: D: The task number isn't in the list D: D: D:");
        }
        return tasknumber - 1;
    }

    protected String[] parseFormatDescription(String s, String command, String format)
            throws WrongInputException, IncompleteInputException {
        try {
            String[] inputs = s.split(" " + format + " ");
            if (inputs.length < 2) {
                throw new IncompleteInputException("D: D: D: The description of a " + command +
                        " is incorrect or empty D: D: D:");
            }
            String[] params = new String[2];
            if (inputs.length == 2) {
                String[] desc = inputs[0].split(command + " ");
                if (desc.length == 2) {
                    params[0] = desc[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                    params[0] = firstPart.toString();
                }
                params[1] = inputs[1];
            } else {
                String[] desc = inputs[0].split(command + " ");
                if (desc.length == 2) {
                    params[0] = desc[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                    params[0] = firstPart.toString();
                }
                StringBuilder secondPart = new StringBuilder();
                for (int i = 1; i < inputs.length; i++) {
                    secondPart.append(inputs[i]);
                    if (i < inputs.length - 1) {
                        secondPart.append(format + " ");
                    }
                }
                params[1] = secondPart.toString();
            }
            return params;
        } catch (PatternSyntaxException e) {
            throw new WrongInputException("D: D: D: Please specify the timing using " + format + " D: D: D:");
        }
    }

    protected String parseStringDescription(String s, String command) throws IncompleteInputException {
        String[] inputs = s.split(command + " ");
        if (inputs.length == 1) {
            throw new IncompleteInputException("D: D: D: The description of a " + command +
                    " cannot be empty D: D: D:");
        }
        if (inputs.length == 2) {
            return inputs[1];
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            output.append(inputs[i]);
            if (i < inputs.length - 1) {
                output.append(command + " ");
            }
        }
        return output.toString();
    }

    protected static Task parseFileFormat(String entry) throws DateTimeParseException {
        String[] entrySplit = entry.split(" \\| ");
        if (entrySplit[0].equals("T")) {
            if (entrySplit[1].equals("1")) {
                return new Todo(entrySplit[2], true);
            } else {
                return new Todo(entrySplit[2], false);
            }
        } else if (entrySplit[0].equals("D")) {
            if (entrySplit[1].equals("1")) {
                return new Deadline(entrySplit[2],  entrySplit[3], true);
            } else {
                return new Deadline(entrySplit[2], entrySplit[3], false);
            }
        } else {
            if (entrySplit[1].equals("1")) {
                return new Event(entrySplit[2], entrySplit[3], true);
            } else {
                return new Event(entrySplit[2], entrySplit[3], false);
            }
        }
    }
}
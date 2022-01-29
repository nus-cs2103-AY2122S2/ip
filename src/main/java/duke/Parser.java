package duke;

public class Parser {
    public Parser() {};

    /**
     * Parses the input from user and checks if there are any invalid commands or format.
     *
     * @param str Input from User.
     * @return String of the original user input if no violations are found.
     * @throws MissingDescriptionException If a command is missing required information.
     * @throws DukeException If the command or format is invalid.
     */
    public String parse(String str) throws MissingDescriptionException, DukeException {
        String[] strArr = str.split(" ");
        String firstWord = strArr[0];
        if (firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo")
                || firstWord.equals("list") || firstWord.equals("delete") || firstWord.equals("unmark")
                || firstWord.equals("mark") || firstWord.equals("find")) {
            if ((firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo"))
                    && strArr.length == 1) {
                throw new MissingDescriptionException();
            }
            if (firstWord.equals("unmark") || firstWord.equals("mark") || firstWord.equals("delete")) {
                if (strArr.length == 1) {
                    throw new DukeException();
                } else {
                    try {
                        int index = Integer.parseInt(strArr[1]);
                    } catch (NumberFormatException e) {
                        throw new DukeException();
                    }
                }
            }
        } else {
            throw new DukeException();
        }
        return str;
    }
}

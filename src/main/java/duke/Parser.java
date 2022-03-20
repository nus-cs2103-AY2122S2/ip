package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for parsing user inputs and
 * returns a ParsedAnswer object that contains information that has been parsed by this class.
 */
public class Parser {
    private String input;

    Parser(String input) {
        this.input = input;
    }

    void assertInputNotEmpty() {
        assert !input.isEmpty() : "Input cannot be left blank.";
    }

    boolean isDateValid(String date) {
        boolean isValid = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date.trim(), formatter);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    ParsedAnswer parseMark(String input) {
        try {
            int idx = Integer.parseInt(input);
            return new ParsedAnswer("mark", idx);
        } catch (Exception e) {
            return new ParsedAnswer("mark", -1);
        }
    }

    ParsedAnswer parseUnmark(String input) {
        try {
            int idx = Integer.parseInt(input);
            return new ParsedAnswer("unmark", idx);
        } catch (Exception e) {
            return new ParsedAnswer("unmark", -1);
        }
    }

    ParsedAnswer parseDelete(String input) {
        try {
            int idx = Integer.parseInt(input);
            return new ParsedAnswer("delete", idx);
        } catch (Exception e) {
            return new ParsedAnswer("delete", -1);
        }
    }

    ParsedAnswer parseFind(String input) {
        try {
            ParsedAnswer pa = new ParsedAnswer("find", -1);
            pa.setDesc(input);
            return pa;
        } catch (Exception e) {
            ParsedAnswer pa = new ParsedAnswer("error", -1);
            pa.setDesc("Please specify what you're searching for.");
            return pa;
        }
    }

    ParsedAnswer parseError(String errorMessage) {
        ParsedAnswer pa = new ParsedAnswer("error", -1);
        pa.setDesc(errorMessage);
        return pa;
    }

    ParsedAnswer handleTodo(String command, String desc) {
        try {
            if (desc.trim().isEmpty()) {
                ParsedAnswer pa = new ParsedAnswer("error", -1);
                pa.setDesc("The description cannot be empty!");
                return pa;
            }
            ParsedAnswer pa = new ParsedAnswer(command, -1);
            pa.setDesc(desc.stripLeading());
            return pa;
        } catch (Exception e) {
            ParsedAnswer pa = new ParsedAnswer("error", -1);
            pa.setDesc("The description cannot be empty!");
            return pa;
        }
    }

    ParsedAnswer handleEventAndDeadline(String command, String[] eventParseBy) {
        ParsedAnswer pa;
        if (eventParseBy.length <= 1 || !isDateValid(eventParseBy[1])) {
            pa = new ParsedAnswer("error", -1);
            pa.setDesc("Please check that your input format is correct.");
        } else {
            pa = new ParsedAnswer(command, -1);
            if (eventParseBy[0].trim().isEmpty()) {
                ParsedAnswer pa_err = new ParsedAnswer("error", -1);
                pa_err.setDesc("The description cannot be empty!");
                return pa_err;
            }
            pa.setDesc(eventParseBy[0].stripLeading());
            pa.setDate(eventParseBy[1]);
        }
        return pa;
    }

    ParsedAnswer handleUnmark (String[] parsedString) {
        if (parsedString.length == 1) {
            return parseError("Index cannot be empty!");
        } else {
            return parseUnmark(parsedString[1]);
        }
    }

    ParsedAnswer handleMark (String[] parsedString) {
        if (parsedString.length == 1) {
            return parseError("Index cannot be empty!");
        } else {
            return parseMark(parsedString[1]);
        }
    }

    ParsedAnswer handleDelete(String[] parsedString) {
        if (parsedString.length == 1) {
            return parseError("Index cannot be empty!");
        } else {
            return parseDelete(parsedString[1]);
        }
    }

    ParsedAnswer handleFind(String[] parsedString) {
        if (parsedString.length == 1) {
            return parseError("Index cannot be empty!");
        } else {
            return parseFind(parsedString[1]);
        }
    }

    ParsedAnswer updateTodo(int index, String desc) {
        ParsedAnswer pa = new ParsedAnswer("update", index);
        if (!desc.trim().isEmpty()) {
            pa.setType("Todo");
            pa.setDesc(desc.stripLeading());
            pa.setDate("");
            return pa;
        } else {
            return parseError("Description cannot be empty!");
        }
    }

    ParsedAnswer updateDescOnly(String type, int index, String desc) {
        ParsedAnswer pa = new ParsedAnswer("update", index);
        pa.setType(type);
        if (!desc.trim().isEmpty()) {
            pa.setDesc(desc.stripLeading());
            pa.setDate("");
            return pa;
        } else {
            return parseError("Description cannot be empty!");
        }
    }

    ParsedAnswer updateDescAndDate(String type, int index, String[] parsedDescAndDate) {
        ParsedAnswer pa = new ParsedAnswer("update", index);
        pa.setType(type);
        if (isDateValid(parsedDescAndDate[1]) && !parsedDescAndDate[0].trim().isEmpty()) {
            pa.setDesc(parsedDescAndDate[0].stripLeading());
            pa.setDate(parsedDescAndDate[1]);
            return pa;
        } else {
            return parseError("Either your date is invalid or your description cannot be empty!");
        }
    }

    ParsedAnswer updateTaskWithDate(String type, int index, String[] parsedDescAndDate) {
        if (parsedDescAndDate.length == 1) {
            return updateDescOnly(type, index, parsedDescAndDate[0]);
        } else if (parsedDescAndDate.length > 1) {
            return updateDescAndDate(type, index, parsedDescAndDate);
        } else {
            return parseError("Format error. Please try again.");
        }
    }

    /**
     * A string value is returned depending on what value of regex is being passed to the function.
     * This string value represents one of the three possible command types.
     * @param regex
     * @return either a String value of "deadline", "event", or "todo"
     */
    String getCommandThroughRegex(String regex) {
        String command;
        if (regex.equalsIgnoreCase("/by")) {
            command = "deadline";
        } else if (regex.equalsIgnoreCase("/at")) {
            command = "event";
        } else {
            command = "todo";
        }

        return command;
    }

    /**
     * regex determines what command type is being asked of.
     * inputToParse is the input that is being passed in by the user.
     * Depending on the command type, the input will be parsed differently.
     *
     * @param regex
     * @param inputToParse
     * @return
     */

    ParsedAnswer parseInputWithRegex(String regex, String[] inputToParse) {
        String command = getCommandThroughRegex(regex);
        if (regex.equalsIgnoreCase("none")) {
            try {
                return handleTodo(command, inputToParse[1]);
            } catch (Exception e) {
                return parseError("The description cannot be empty!");
            }

        } else {
            try {
                return handleEventAndDeadline(command, inputToParse[1].split(regex));
            } catch (Exception e) {
                return parseError("The description cannot be empty!");
            }
        }
    }

    /**
     * This method is responsible for creating a ParsedAnswer corresponding to the update command.
     * A separate method is specially created because of how different the update command is
     * compared to the other commands.
     * @param inputToParse
     * @return ParsedAnswer
     */

    ParsedAnswer parseUpdate(String inputToParse) {
        try {
            String[] parsedInput = inputToParse.split(" " , 2);
            String[] parsedContent = parsedInput[1].split(" ", 2);
            int index = Integer.parseInt(parsedContent[0]) - 1;
            String[] parsedDescAndDate = parsedContent[1].split("/date");
            if (Storage.taskList.get(index) instanceof Deadline) {
                return updateTaskWithDate("Deadline", index, parsedDescAndDate);
            } else if (Storage.taskList.get(index) instanceof Event) {
                return updateTaskWithDate("Event", index, parsedDescAndDate);
            } else {
                return updateTodo(index, parsedContent[1]);
            }

        } catch (Exception e) {
            return parseError("Format error. Please try again.");
        }
    }

    /**
     * Parses a user input and depending on the cases
     * a ParsedAnswer object is created and is returned by the function for the ParsedAnswerHandler class to process.
     * @return ParsedAnswer
     */
    public ParsedAnswer parse() {
        assertInputNotEmpty();
        String[] parsedString = input.toLowerCase().split(" ", 2);
        switch (parsedString[0]) {
            case "bye":
                return new ParsedAnswer("bye", -1);

            case "list":
                return new ParsedAnswer("list", -1);

            case "unmark":
                return handleUnmark(parsedString);

            case "mark":
                return handleMark(parsedString);

            case "delete":
                return handleDelete(parsedString);

            case "todo":
                return parseInputWithRegex("none", parsedString);

            case "event":
                return parseInputWithRegex("/at", parsedString);

            case "deadline":
                return parseInputWithRegex("/by", parsedString);

            case "find":
                return handleFind(parsedString);

            case "update":
                return parseUpdate(input);

            default:
                return parseError("Sorry, but I have no idea what you mean.");
        }
    }
}

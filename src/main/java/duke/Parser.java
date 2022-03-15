package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
            LocalDate parsedDate = LocalDate.parse(date.trim(), formatter);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    String getCommandThroughRegex(String regex) {
        String command = "";
        if (regex.equalsIgnoreCase("/by")) {
            command = "deadline";
        } else if (regex.equalsIgnoreCase("/at")) {
            command = "event";
        } else {
            command = "todo";
        }

        return command;
    }

    ParsedAnswer parseInputWithRegex(String regex, String[] inputToParse) {
        String command = getCommandThroughRegex(regex);
        if (regex.equalsIgnoreCase("none")) {
            try {
                String desc = inputToParse[1];
                ParsedAnswer pa = new ParsedAnswer(command, -1);
                pa.setDesc(desc);
                return pa;
            } catch (Exception e) {
                ParsedAnswer pa = new ParsedAnswer("error", -1);
                pa.setDesc("The description cannot be empty!");
                return pa;
            }
        } else {
            try {
                String[] eventParseBy = inputToParse[1].split(regex);
                ParsedAnswer pa;
                if (eventParseBy.length <= 1 || !isDateValid(eventParseBy[1])) {
                    pa = new ParsedAnswer("error", -1);
                    pa.setDesc("Please check that your input format is correct.");
                } else {
                    pa = new ParsedAnswer(command, -1);
                    pa.setDesc(eventParseBy[0]);
                    pa.setDate(eventParseBy[1]);
                }
                return pa;
            } catch (Exception e) {
                ParsedAnswer pa = new ParsedAnswer("error", -1);
                pa.setDesc("The description cannot be empty!");
                return pa;
            }
        }
    }

    ParsedAnswer parseUpdate(String inputToParse) {
        try {
            String[] parsedInput = inputToParse.split(" " , 2);
            String[] parsedContent = parsedInput[1].split(" ", 2);
            int index = Integer.parseInt(parsedContent[0]) - 1;
            ParsedAnswer pa = new ParsedAnswer("update", index);
            System.out.println(index);
            if (Storage.taskList.get(index) instanceof Deadline) {
                pa.setType("Deadline");
            } else if (Storage.taskList.get(index) instanceof Event) {
                pa.setType("Event");
            } else if (Storage.taskList.get(index) instanceof ToDos) {
                pa.setType("Todo");
            }

            String[] parsedDescAndDate = parsedContent[1].split("/date");

            if (parsedDescAndDate.length == 1) {
                pa.setDesc(parsedDescAndDate[0]);
                pa.setDate("");
                return pa;
            } else if (parsedDescAndDate.length > 1) {
                pa.setDesc(parsedDescAndDate[0]);
                if (isDateValid(parsedDescAndDate[1])) {
                    pa.setDate(parsedDescAndDate[1]);
                    return pa;
                } else {
                    ParsedAnswer pError = new ParsedAnswer("error", -1);
                    pError.setDesc("Format error. Please try again.");
                    return pError;
                }

            } else {
                ParsedAnswer pError = new ParsedAnswer("error", -1);
                pError.setDesc("Format error. Please try again.");
                return pError;
            }

        } catch (Exception e) {
            ParsedAnswer pa = new ParsedAnswer("error", -1);
            pa.setDesc("Format error. Please try again.");
            return pa;
        }
    }

    public ParsedAnswer parse() {
        assertInputNotEmpty();
        String[] parsedString = input.toLowerCase().split(" ", 2);
        switch (parsedString[0]) {
            case "bye":
                return new ParsedAnswer("bye", -1);

            case "list":
                return new ParsedAnswer("list", -1);

            case "unmark":
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("unmark", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("unmark", -1);
                }

            case "mark":
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("mark", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("mark", -1);
                }

            case "delete":
                try {
                    int idx = Integer.parseInt(parsedString[1]);
                    return new ParsedAnswer("delete", idx);
                } catch (Exception e) {
                    return new ParsedAnswer("delete", -1);
                }

            case "todo":
                return parseInputWithRegex("none", parsedString);

            case "event":
                return parseInputWithRegex("/at", parsedString);

            case "deadline":
                return parseInputWithRegex("/by", parsedString);

            case "find":
                try {
                    ParsedAnswer pa = new ParsedAnswer("find", -1);
                    pa.setDesc(parsedString[1]);
                    return pa;
                } catch (Exception e) {
                    ParsedAnswer pa = new ParsedAnswer("error", -1);
                    pa.setDesc("Please specify what you're searching for.");
                    return pa;
                }

            case "update":
                return parseUpdate(input);

            default:
                ParsedAnswer pa = new ParsedAnswer("error", -1);
                pa.setDesc("Sorry, but I have no idea what you mean.");
                return pa;
        }
    }
}

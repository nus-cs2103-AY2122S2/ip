package parser;

import enums.Command;
import exception.JarvisException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Parser {
    /**
     * Parses the input string into a command for Jarvis.
     *
     * @param input The user input to parse.
     * @return The parsed command map.
     * @throws JarvisException If input does not conform to the format.
     */
    public static HashMap<String, Object> parseInput(String input) throws JarvisException {
        HashMap<String, Object> parsedCommand = new HashMap<>();
        String[] tokens = input.split(" ");
        parsedCommand.put("command", tokens[0].trim().toUpperCase());

        Command command = Command.valueOf((String) parsedCommand.get("command"));
        if (command == Command.MARK ||
                command == Command.UNMARK ||
                command == Command.DELETE) {
            try {
                int num = Integer.parseInt(tokens[1]) - 1;
                parsedCommand.put("num", num);
            } catch (IndexOutOfBoundsException e) {
                throw new JarvisException("Please specify a task number.");
            } catch (NumberFormatException e) {
                throw new JarvisException("Please specify the task number numerically.");
            }
        } else if (command == Command.TODO) {
            try {
                String description = input.trim().substring(Command.TODO.toString().length() + 1);
                parsedCommand.put("description", description);
            } catch (IndexOutOfBoundsException e) {
                throw new JarvisException("The description of the todo cannot be empty.");
            }
        } else if (command == Command.DEADLINE) {
            String[] split = input.split("/by");

            try {
                String description = split[0].trim().substring(Command.DEADLINE.toString().length() + 1);
                parsedCommand.put("description", description);
            } catch (IndexOutOfBoundsException e) {
                throw new JarvisException("The description of the deadline cannot be empty.");
            }
            if (split.length == 1) {
                throw new JarvisException("Please specify the date of the deadline " +
                        "(usage: `deadline <description> /by <date>`).");
            }
            if (split[1].trim().equals("")) {
                throw new JarvisException("The date of the deadline cannot be empty.");
            }
            parsedCommand.put("date", parseDateTime(split[1]));
        } else if (command == Command.EVENT) {
            String[] split = input.split("/at");

            try {
                String description = split[0].trim().substring(Command.EVENT.toString().length() + 1);
                parsedCommand.put("description", description);
            } catch (IndexOutOfBoundsException e) {
                throw new JarvisException("The description of the event cannot be empty.");
            }
            if (split.length == 1) {
                throw new JarvisException("Please specify the date of the event " +
                        "(usage: `event <description> /at <date>`).");
            }
            if (split[1].trim().equals("")) {
                throw new JarvisException("The date of the event cannot be empty.");
            }
            parsedCommand.put("date", parseDateTime(split[1]));
        }

        return parsedCommand;
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param str The datetime string to parse.
     * @return LocalDateTime object of the string.
     * @throws JarvisException If string format is incorrect.
     */
    private static LocalDateTime parseDateTime(String str) throws JarvisException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(str.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new JarvisException("Please specify the date format as follows: 2022-12-25 2359");
        }
    }
}

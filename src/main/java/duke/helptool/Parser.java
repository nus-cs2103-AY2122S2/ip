package duke.helptool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.tag.Tag;

/**
 * The type Parser.
 */
public class Parser {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Instantiates a new Parser.
     */
    public Parser() {

    }

    /**
     * Is valid date boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isValidDate(String input) {
        try {
            LocalDateTime.parse(input, dateFormat);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * Is empty boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isEmpty(String input) {
        if (input.equals("")) {
            return true;
        }
        int curr = 0;
        while (curr < input.length()) {
            if (input.charAt(curr) != ' ') {
                return false;
            }
            curr++;
        }
        return true;
    }

    /**
     * Add tag help method.
     *
     * @param input the input
     * @return the tag
     */
    public static Tag addTag(String input) {
        String tagDescription;
        if (!input.contains("#")) {
            tagDescription = "";
        } else {
            int tagPos = input.indexOf("#");
            tagDescription = input.substring(tagPos + 1);
        }
        return new Tag(tagDescription);
    }

    /**
     * Add tag help method.
     *
     * @param input the input
     * @param type  the type
     * @return the tag
     */
    public static String getDescription(String input, String type) {
        String description;
        if (type.equals("T") && !input.contains("#")) {
            description = input.substring(4);
        } else if (type.equals("T") && input.contains("#")) {
            int tagPos = input.indexOf("#");
            description = input.substring(4, tagPos);
        } else if (type.equals("D")) {
            int byPos = input.indexOf("/by");
            description = input.substring(9, byPos);
        } else if (type.equals("E")) {
            int atPos = input.indexOf("/at");
            description = input.substring(6, atPos);
        } else {
            description = "";
        }
        return description;
    }

    /**
     * Gets tag pos.
     *
     * @param input the input
     * @return the tag pos
     */
    public static int getTagPos(String input) {
        if (!input.contains("#")) {
            return -1;
        } else {
            return input.indexOf("#");
        }
    }

    /**
     * Parse command.
     *
     * @param input the input
     * @return the command
     * @throws DukeException the duke exception
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            // detect bye
            return new ExitCommand();
        } else if (input.equals("list")) {
            // detect list
            return new ListCommand();
        } else if (input.startsWith("find")) {
            String findDetail = input.substring(4);
            if (isEmpty(findDetail)) {
                throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
            }
            return new FindCommand(findDetail);
        } else {
            if (input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("delete")) {
                boolean isDigit = !Character.isDigit(input.charAt(input.length() - 1));
                int markDigit = Character.getNumericValue(input.charAt(input.length() - 1));
                if (input.startsWith("mark")) {
                    // detect digit
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The mark cannot be done.");
                    }
                    return new MarkCommand(markDigit);
                }
                if (input.startsWith("unmark")) {
                    // detect unmark
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The unmark cannot be done.");
                    }
                    return new UnmarkCommand(markDigit);
                }
                if (input.startsWith("delete")) {
                    // detect delete
                    if (isDigit) {
                        throw new DukeException("☹ OOPS!!! The delete cannot be done.");
                    }
                    return new DeleteCommand(markDigit);
                }
            } else if (input.startsWith("todo")) {
                // generate
                String description = getDescription(input, "T");
                if (isEmpty(description)) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Tag newTag = addTag(input);
                return new AddCommand(description, "T", newTag);
            } else if (input.startsWith("deadline")) {
                if (isEmpty(input.substring(8))) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!input.contains("/by")) {
                    throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                }
                int byPos = input.indexOf("/by");
                String description = getDescription(input, "D");
                String byDateTime;
                if (!input.contains("#")) {
                    byDateTime = input.substring(byPos + 4);
                } else {
                    byDateTime = input.substring(byPos + 4, getTagPos(input) - 1);
                }
                if (isValidDate(byDateTime)) {
                    LocalDateTime dateTime = LocalDateTime.parse(byDateTime, dateFormat);
                    Tag newTag = addTag(input);
                    return new AddCommand(description, "D", dateTime, newTag);
                } else {
                    throw new DukeException("☹ OOPS!!! The date time is not of correct format dd/MM/yyyy HHmm.");
                }
            } else if (input.startsWith("event")) {
                if (isEmpty(input.substring(5))) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                if (!input.contains("/at")) {
                    throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                }
                int atPos = input.indexOf("/at");
                String description = getDescription(input, "E");
                String atDateTime;
                if (!input.contains("#")) {
                    atDateTime = input.substring(atPos + 4);
                } else {
                    atDateTime = input.substring(atPos + 4, getTagPos(input) - 1);
                }
                if (isValidDate(atDateTime)) {
                    Tag newTag = addTag(input);
                    LocalDateTime dateTime = LocalDateTime.parse(atDateTime, dateFormat);
                    return new AddCommand(description, "E", dateTime, newTag);
                } else {
                    throw new DukeException("☹ OOPS!!! The date time is not of correct format dd/MM/yyyy HHmm.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return null;
    }
}

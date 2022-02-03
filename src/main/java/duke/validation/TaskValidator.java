
package duke.validation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import duke.exceptions.DukeException;

public class TaskValidator {

    /**
     * Validate user input date and converts string to LocalDate if true
     *
     * @param date the string date input from user
     * @throws DukeException if date input from user is invalid
     */
    public static LocalDate convertDate(final String date) throws DukeException {

        try {
            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate convertDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            return convertDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Put the date in this format YYYY-MM-DD");
        }
    }

    /**
     * Validate user input for ToDo task
     *
     * @param userInput the input from user
     *
     * @return the validated ToDo description
     * @throws DukeException if ToDo input from user is invalid
     */
    public static String validateToDo(String userInput) {
        String description = "";

        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);

            if (emptyCommand) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            description = words[1].trim();

            boolean noDescription = description.equals("");
            if (noDescription) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    /**
     * Validate user input for Deadline task
     *
     * @param userInput the input from user
     *
     * @return the validated deadline information
     * @throws DukeException if Deadline input from user is invalid
     */
    public static String[] validateDeadline(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";

        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);

            if (emptyCommand) {
                throw new DukeException("The description of a deadline task cannot be empty.");
            }

            int keywordIndex = words[1].indexOf("/by");
            boolean noKeyword = (keywordIndex == -1);
            if (noKeyword) {
                throw new DukeException("The deadline time of the deadline task cannot be empty.");
            }

            information[0] = words[1].substring(0, keywordIndex).trim();
            information[1] = words[1].substring(keywordIndex + 3).trim();

            boolean isDescriptionEmpty = information[0].equals("");
            boolean isDeadlineEmpty = information[1].equals("");

            if (isDescriptionEmpty || isDeadlineEmpty) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }

    /**
     * Validate user input for Event task
     *
     * @param userInput the input from user
     *
     * @return the validated event information
     * @throws DukeException if Event input from user is invalid
     */
    public static String[] validateEvent(String userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            String[] words = userInput.split(" ", 2);
            boolean emptyCommand = (words.length == 1);
            if (emptyCommand) {
                throw new DukeException("The description of a event task cannot be empty.");
            }

            int keywordIndex = words[1].indexOf("/at");
            boolean noKeyword = (keywordIndex == -1);
            if (noKeyword) {
                throw new DukeException("The event time of the event task cannot be empty.");
            }

            information[0] = words[1].substring(0, keywordIndex).trim();
            information[1] = words[1].substring(keywordIndex + 3).trim();

            boolean isDescriptionEmpty = information[0].equals("");
            boolean isEventTimeEmpty = information[1].equals("");

            if (isDescriptionEmpty || isEventTimeEmpty) {
                throw new DukeException("The description or dateline time cannot be empty.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }
}

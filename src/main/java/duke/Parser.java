package duke;

import java.time.LocalDate;

/**
 * Parser class handles parsing the stored text file strings
 * into Task objects that can be loaded into TaskList
 */
public class Parser {
    /**
     * String to be parsed.
     */
    protected String string;

    /**
     * Constructor for Parser.
     *
     * @param string The string to be parsed
     */
    Parser(String string) {
        this.string = string;
    }

    /**
     * The method that parses the string and then
     * creates a Deadline, Task, or Event object as necessary.
     *
     * @return Task object containing the data specified in the string
     */
    Task parse() throws DukeException {
        Task t = new Task("null");
        switch (string.substring(0, 3)) {
        case "[T]":
            t = new Todo(string.substring(7));
            break;
        case "[D]":
            String[] arr = string.split("\\(by: ");
            String[] dateString = arr[1].split("\\)");
            String[] date = dateString[0].split("-");
            LocalDate by = LocalDate.of(Integer.parseInt(date[2]),
                    Integer.parseInt(monthToInt(date[1])),
                    Integer.parseInt(date[0]));
            t = new Deadline(arr[0].substring(7), by);
            break;
        case "[E]":
            String[] arr2 = string.split("\\(at: ");
            t = new Event(arr2[0].substring(7), arr2[1].split("\\)")[0]);
            break;
        default:
            throw new DukeException("Storage file is corrupted");
        }
        return t;
    }

    private String monthToInt(String month) throws DukeException {
        String r = "";
        switch (month) {
        case "Jan":
            r = "01";
            break;
        case "Feb":
            r = "02";
            break;
        case "Mar":
            r = "03";
            break;
        case "Apr":
            r = "04";
            break;
        case "May":
            r = "05";
            break;
        case "Jun":
            r = "06";
            break;
        case "Jul":
            r = "07";
            break;
        case "Aug":
            r = "08";
            break;
        case "Sep":
            r = "09";
            break;
        case "Oct":
            r = "10";
            break;
        case "Nov":
            r = "11";
            break;
        case "Dec":
            r = "12";
            break;
        default:
            throw new DukeException("Storage file is corrupted");

        }
        return r;
    }
}

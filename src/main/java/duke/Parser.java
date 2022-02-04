package duke;

/**
 * Parser class to handle the inputs of the user
 *
 * @author Justin Ng Jie Ern
 */
public class Parser {
    /**
     * Empty Constructor.
     */
    public Parser() {}

    /**
     * Used to split instruction by " "
     *
     * @param inst Instruction from user
     * @return String Array of 2 Strings
     */
    public String[] splitLimitTwo(String inst) {
        return inst.split(" ", 2);
    }

    /**
     * Used to parse an Event Task.
     *
     * @param inst String of a Event Task.
     * @return String Array containing String of details of Event, and String of time of Event.
     */
    public String[] parseEvent(String inst) {
        String[] taskArr = splitLimitTwo(inst);
        return taskArr[1].split("/at ");
    }

    /**
     * Used to parse an Deadline Task.
     *
     * @param inst String of a Deadline Task.
     * @return String Array containing String of details of Deadline task, and String of time of Deadline.
     */
    public String[] parseDeadline(String inst) {
        // deadline return book /by Sunday
        String[] taskArr = splitLimitTwo(inst);
        return taskArr[1].split("/by ");
    }

    /**
     * Used to parse a Todo Task.
     *
     * @param inst String of a Todo Task.
     * @return String of details of Todo Task.
     */
    public String parseToDo(String inst) {
        String[] taskArr = splitLimitTwo(inst);
        return taskArr[1];
    }

    /**
     * Used to help format the date from a load file into a "YYYY-MM-DD HH:MM" format
     *
     * @param dateTime String of date and time that is from a loaded file
     * @return String in "YYYY-MM-DD HH:MM" format.
     */
    public String dateFormatHelper(String dateTime) {
        String[] dateTimeArr = dateTime.split(", ");
        String date = dateTimeArr[0];
        String[] dateArr = date.split(" ");
        String month = dateArr[0];
        String day = dateArr[1];
        String year = dateArr[2];
        String monthNum = "";
        String time = dateTimeArr[1];
        switch (month) {
        case "Jan":
            monthNum = "01";
            break;
        case "Feb":
            monthNum = "02";
            break;
        case "Mar":
            monthNum = "03";
            break;
        case "Apr":
            monthNum = "04";
            break;
        case "May":
            monthNum = "05";
            break;
        case "Jun":
            monthNum = "06";
            break;
        case "Jul":
            monthNum = "07";
            break;
        case "Aug":
            monthNum = "08";
            break;
        case "Sep":
            monthNum = "09";
            break;
        case "Oct":
            monthNum = "10";
            break;
        case "Nov":
            monthNum = "11";
            break;
        case "Dec":
            monthNum = "12";
            break;
        default:
            System.out.println("Month does not exist");

        }// YYYY-MM-DD HH:MM
        return year + "-" + monthNum + "-" + day + " " + time;
    }

}

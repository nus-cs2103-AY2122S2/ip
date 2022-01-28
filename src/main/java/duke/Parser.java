package duke;

public class Parser {
    public Parser() {}

    public String[] splitLimitTwo (String inst) {
        return inst.split(" ", 2);
    }

    public String[] parseEvent(String inst) {
        String[] taskArr = splitLimitTwo(inst);
        String[] taskDetails = taskArr[1].split("/at ");
        return taskDetails;
    }

    public String[] parseDeadline(String inst) {
        // deadline return book /by Sunday
        String[] taskArr = splitLimitTwo(inst);
        String[] taskDetails = taskArr[1].split("/by ");
        return taskDetails;
    }

    public String parseToDo(String inst) {
        String[] taskArr = splitLimitTwo(inst);
        return taskArr[1];
    }

    public String dataFormatHelper(String dateTime) {
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

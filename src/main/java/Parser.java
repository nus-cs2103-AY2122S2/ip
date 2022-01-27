import java.time.LocalDate;

public class Parser {
    protected String string;

    Parser(String string) {
        this.string =  string;
    }

    Task parse() {
        Task t = new Task("null");
        switch (string.substring(0,3)) {
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
        }
        return t;
    }

    String monthToInt(String month) {
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

        }
        return r;
    }
}

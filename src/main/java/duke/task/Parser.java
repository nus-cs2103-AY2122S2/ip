package duke.task;

public class Parser {
    public Parser() {
    }

    public String[] parseDeadline(String string) {
        return string.split("/by");
    }

    public String[] parseEvent(String string) {
        return string.split("/at");
    }
}

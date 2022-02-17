package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String[] splitTimeAndDescription(String command) {
        return command.split("/", 2);
    }

}

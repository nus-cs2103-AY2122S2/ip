package duke.task;

import duke.parser.DateTimeParser;
import java.time.LocalDate;

public class Event extends Task {
    protected String type;
    protected LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
        this.type = "E";
    }

    /**
     * Checks whether the command date is present
     *
     * @param input input command of user
     * @return True if the command contains valid name, false otherwise
     */
    public static boolean isInvalidWithMissingEventDate (String input) {
        String[] splitString = input.split("/", 2);
        String[] instruction = splitString[0].split(" ", 2);

        return splitString.length == 1 || !splitString[1].startsWith("at ");
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (at: "
                + DateTimeParser.formatDate(this.time) + ")";
    }

}

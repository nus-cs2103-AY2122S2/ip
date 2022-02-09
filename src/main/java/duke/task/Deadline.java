package duke.task;

import duke.parser.DateTimeParser;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String type;
    protected LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
        this.type = "D";
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

        return splitString.length == 1 || !splitString[1].startsWith("by ");
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (by: "
                + DateTimeParser.formatDate(this.time) + ")";
    }
}

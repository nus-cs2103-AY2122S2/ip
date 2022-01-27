import exceptions.DukeDeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
//    protected String by;
    protected LocalDate by_date;

    public Deadline(String name, LocalDate by_date) {
        super(name);
//        this.by = by;
        this.by_date = by_date;
    }

    public static Deadline setDeadline(String input) throws DukeDeadlineException { //method to call constructor solving
                                                    // "'this' should be called in first line" error
        String taskname;
        String taskby;
        LocalDate taskby_date;

        try {
            String[] split = input.split(" /by ");
            taskname = split[0];
            taskby = split[1];

            taskby_date = LocalDate.parse(taskby);

            Deadline d_line = new Deadline(taskname, taskby_date);
//            System.out.println("Got it. I've added this task: ");
//            System.out.println(d_line);
            return d_line;
        } catch (Exception e) {
            DukeDeadlineException error = new DukeDeadlineException(
                                                    "OOPS!!! Please enter in format: deadline <task> /by <yyyy-mm-dd> \n " +
                                                    "e.g. deadline complete project /by 2022-12-24");
            System.out.println(error.getMessage());
        }
        return null;
    }

    public String getDate() {
        return this.by_date.toString();
    }

    @Override
    public String toString() {
        String formatted = this.by_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by:" + formatted + ")";
    }
}

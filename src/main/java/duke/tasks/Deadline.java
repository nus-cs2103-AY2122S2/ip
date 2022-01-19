package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    String time;
    // Keeping this for now since we do not know what to use LocalDate date for...
    String deadline;
    LocalDate date;

    public Deadline(String task, boolean markStatus, String deadline) {
        super(task, markStatus);
        this.deadline = deadline;
        this.dateFormatter(deadline);
    }

    @Override
    public String getStringCmd() {
        // mark status | type | descriptor | deadline
        return super.getMarkStatus() + "&D&" + super.getTask() + "&" + this.deadline;
    }

    private void dateFormatter(String dateTime) throws ArrayIndexOutOfBoundsException{
        // the string is of the form: 2/12/2019 1800
        dateTime = dateTime.replace('/', '-');
        String[] splitDateTime = dateTime.split(" ");
        try {
            this.date = LocalDate.parse(splitDateTime[0]);
            this.time = splitDateTime[1];
        } catch (Exception e) {
            System.out.println("Wrong date format");
        }
    }

    public String getDate() {
        return date.toString();
    }

    public String getDeadline() {
        return "(by:" + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}

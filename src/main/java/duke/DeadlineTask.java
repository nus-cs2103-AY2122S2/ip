package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    LocalDate date;

    public DeadlineTask(String input) {
        super(input);
        this.type = "deadline";
        this.date = this.getDate();
        this.updateDescription();
    }

    /**
     * Format DeadlineTask description into display format for Duke
     */
    private void updateDescription() {
        this.description += this.getInfo();
        this.description += "(by: ";
        this.description += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.description += ")";
    }

    /**
     * Find the index from which datetime information starts
     *
     * @return Integer index
     */
    private int findDeadlineIndex() {
        int index = this.input.indexOf("/by "); // "/by" as delimiting character for events
        try {
            if (index == -1) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("No /by date specified.");
            System.exit(2);
        }
        return index;
    }

    /**
     * Gets the task information
     *
     * @return Task information
     */
    private String getInfo() {
        int index = this.findDeadlineIndex();
        String info = this.input.substring(0, index);

        return info;
    }

    /**
     * Gets the deadline date
     *
     * @return Deadline date string
     */
    private LocalDate getDate() {
        int index = this.findDeadlineIndex() + 4;
        String date = this.input.substring(index);
        LocalDate localDate = LocalDate.parse(date);

        return localDate;
    }

}

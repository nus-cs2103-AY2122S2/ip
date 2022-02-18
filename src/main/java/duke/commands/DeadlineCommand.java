package duke.commands;

import duke.info.task.Deadline;

public class DeadlineCommand extends AddCommand {

    /**
     * Constructs a Deadline command
     * @param deadline - the deadline to be created
     * @param date - the date of the deadline
     */
    public DeadlineCommand(String deadline, String date) {
        super(new Deadline(deadline, date, false));
    }

}

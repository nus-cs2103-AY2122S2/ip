package duke.commands;

import duke.info.task.Deadline;

public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String deadline, String date) {
        super(new Deadline(deadline, date, false));
    }

}

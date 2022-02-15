package duke;

import java.time.LocalDate;

/**
 * The command to handle keyword check
 */
public class CheckCommand extends Command {

    CheckCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Checks for the task having the same date as input
     *
     * @return All tasks having the same date as input
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        LocalDate date = LocalDate.parse(detailArray[1]);
        return ui.echo(this.taskList.checkDate(date));
    }
}

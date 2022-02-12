package duke;

import java.time.LocalDate;

public class CheckCommand extends Command {

    CheckCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        LocalDate date = LocalDate.parse(detailArray[1]);
        return ui.echo(this.taskList.checkDate(date));
    }
}

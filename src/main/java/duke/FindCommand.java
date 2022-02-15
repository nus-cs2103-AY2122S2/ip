package duke;

/**
 * Command to handle keyword find
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class FindCommand extends Command {

    FindCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    /**
     * Finds all task contains the keyword
     *
     * @return String of tasks contain the keyword
     */
    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        String word = detailArray[1];
        return ui.echo(this.taskList.checkWord(word));
    }
}

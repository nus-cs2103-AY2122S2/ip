package duke;

public class FindCommand extends Command {

    FindCommand(Ui ui, TaskList taskList, Storage storage, String detail) {
        super(ui, taskList, storage, detail);
    }

    @Override
    String runCommand() {
        String[] detailArray = detail.split(" ", 2);
        String word = detailArray[1];
        return ui.echo(this.taskList.checkWord(word));
    }
}

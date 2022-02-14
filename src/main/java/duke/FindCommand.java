package duke;

public class FindCommand extends Command {
    public String word;

    public FindCommand (String word) {
        this.word = word;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.find(word);
        ui.showFound(matchingTasks);
    }

    public boolean isExit() {
        return false;
    }
}
package duke;

public class FindCommand extends Command {
    public String word;

    public FindCommand (String word) {
        this.word = word;
    }

    public String execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.find(word);
        return ui.showFound(matchingTasks);
    }

    public boolean isExit() {
        return false;
    }
}
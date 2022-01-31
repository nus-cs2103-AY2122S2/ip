package kenobi.command;

import kenobi.util.TaskList;

public class FindCommand extends Command {
    final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute() {
        TaskList found = (TaskList) tasks.clone();
        found.removeIf(e -> !e.hasWord(searchTerm));

        if (found.size() == 0) {
            return "Nothing in the archives matches your search term";
        }

        return "Here's what I found from the archives:\n" + found;
    }
}

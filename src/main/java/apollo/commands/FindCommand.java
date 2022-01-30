package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;

public class FindCommand extends Command {

    private final String keyword;
    private int index;
    private final StringBuilder results;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
        index = 1;
        results = new StringBuilder();
        results.append("Here are your related tasks. ");
    }

    private void appendToResults(String result) {
        results.append("\n").append(index).append(".");
        results.append(result);
        index++;
    }

    @Override
    public String execute() throws ApolloOutOfBoundsException {
        for (int i = 0; i < taskList.taskCount(); i++) {
            if (taskList.getTaskDescription(i).toLowerCase().contains(keyword)) {
                appendToResults(taskList.getTaskString(i));
            }
        }

        if (index == 1) {
            return "I could not find any related tasks. ";
        }
        return results.toString();
    }
}

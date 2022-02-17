package ari.command;

import ari.tasks.Task;

/**
 * Finds Task in TaskList that contains the keyword
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private static final String FIND_MESSAGE = "Yes Master, here are the tasks in your list that matches the keyword:";
    private static final String NOT_FOUND_MESSAGE = "I am sorry Master, "
            + "the keyword you are searching for is not in your list";
    private static final String EMPTY_LIST_MESSAGE = "Dear Master, you have not added anything";
    private static final String INDEX_MESSAGE = "%2d. ";

    private String toFindWord;

    public FindCommand(String toFindWord) {
        this.toFindWord = toFindWord.toLowerCase().stripLeading().stripTrailing();
    }

    @Override
    public String execute() {
        if (taskList.getSize() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        int numFound = 0;

        StringBuilder resultString = new StringBuilder();
        resultString.append(FIND_MESSAGE);
        resultString.append("\n");

        for (Task task : taskList.getAllTasks()) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(toFindWord)) {
                numFound++;
                resultString.append(String.format(INDEX_MESSAGE, numFound));
                resultString.append(task);
                resultString.append("\n");
            }
        }

        if (numFound == 0) {
            return NOT_FOUND_MESSAGE;
        }
        return resultString.toString();
    }
}

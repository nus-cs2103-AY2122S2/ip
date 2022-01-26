package main.java.ari.command;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private static final String EMPTY_LIST_MESSAGE = "Dear Master, you have not added anything";
    private static final String LIST_MESSAGE = "Dear Master, here is a list of items you have added:";
    private static final String INDEX_MESSAGE = "%2d. ";

    @Override
    public String execute() {
        if (taskList.getSize() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder resultString = new StringBuilder();
        resultString.append(LIST_MESSAGE);
        resultString.append("\n");

        for (int i = 0; i < taskList.getSize(); i++) {
            resultString.append(String.format(INDEX_MESSAGE, i + 1));
            resultString.append(taskList.getTask(i));
            resultString.append("\n");
        }

        return resultString.toString();
    }

}

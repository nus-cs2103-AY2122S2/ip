package chatbot.command;

import chatbot.task.Task;

import java.util.ArrayList;

public class UnmarkCommand implements Command {
    public static final String KEYWORD = "unmark";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = tasks.get(index - 1);
            task.setDone(false);
            return String.format("OK, I've marked this task as not done yet:\n  %s", task);
        } catch (Exception e) {
            return "☹ OOPS!!! The index of unmark must be within the size of the list.";
        }
    }
}

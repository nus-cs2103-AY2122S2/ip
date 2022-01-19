package chatbot.command;

import chatbot.task.Task;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    public static final String KEYWORD = "delete";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = tasks.remove(index - 1);
            return String.format("Noted. I've removed this task:\n  %s", task);
        } catch (Exception e) {
            return "☹ OOPS!!! The index of delete must be within the size of the list.";
        }
    }
}

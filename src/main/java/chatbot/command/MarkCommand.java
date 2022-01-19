package chatbot.command;

import chatbot.task.Task;

import java.util.ArrayList;

public class MarkCommand implements Command {
    public static final String KEYWORD = "mark";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        try {
            int index = Integer.parseInt(input[1]);
            Task task = tasks.get(index - 1);
            task.setDone(true);
            return String.format("Nice! I've marked this task as done:\n  %s", task);
        } catch (Exception e) {
            return "☹ OOPS!!! The index of mark must be within the size of the list.";
        }
    }
}

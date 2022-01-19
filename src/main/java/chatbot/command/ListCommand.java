package chatbot.command;

import chatbot.task.Task;

import java.util.ArrayList;

public class ListCommand implements Command {
    public static final String KEYWORD = "list";

    @Override
    public String execute(String[] input, ArrayList<Task> tasks) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            output += String.format("%d. %s\n", i+1, tasks.get(i).toString());
        }
        return output;
    }
}
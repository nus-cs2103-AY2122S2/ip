package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.util.Save;
import duke.util.TaskList;

/**
 * This FindCommand class will find a task name that has a matching given keyword when executed.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes command by matching a keyword and printing the matched tasks that contains keyword.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    @Override
    public String execute(TaskList tasks, Save save) {
        ArrayList<Task> taskArrList = tasks.getCurrentList();
        ArrayList<Boolean> keywordMatchedList = new ArrayList<>(tasks.getCount());
        boolean hasMatch = false;

        taskArrList.forEach(x -> {
            boolean hasKeyword = x.getName().contains(this.keyword);
            keywordMatchedList.add(hasKeyword);
        });

        Boolean[] keywordMatchArr = new Boolean[tasks.getCount()];
        keywordMatchArr = keywordMatchedList.toArray(keywordMatchArr);

        for (Boolean x : keywordMatchArr) {
            hasMatch = hasMatch || x;
        }

        String response = "";
        if (hasMatch) {
            int matchedTaskCount = 1;
            response = response.concat("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getCount(); i++) {
                if (keywordMatchedList.get(i)) {
                    Task task = tasks.getTask(i);
                    String str = matchedTaskCount + "." + task.track() + task.getStatus() + " " + task;
                    matchedTaskCount++;
                    response = response.concat("\n" + str);
                }
            }
        } else {
            response = "No tasks found with keyword '" + this.keyword + "'.";
        }
        return response;
    }
}

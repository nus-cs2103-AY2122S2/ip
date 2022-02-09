package luca.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import luca.storage.Storage;
import luca.task.Task;
import luca.task.TaskList;

public class FindCommand extends Command {

    /** Keyword used for search. */
    private String keyword;

    /**
     * Constructor to create FindCommand.
     */
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    /**
     * Loops through the list of tasks and outputs string of tasks
     * containing the keyword in the description.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @retrun response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {

        List<Task> list = taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (list.size() == 0) {
            return "Sorry, I was unable to find any matching tasks.";
        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        IntStream.range(0, list.size())
                .forEach(index -> stringBuilder.append("" + (index + 1) + "."
                        + list.get(index).toString() + "\n"));

        return stringBuilder.toString().trim();
    }
}

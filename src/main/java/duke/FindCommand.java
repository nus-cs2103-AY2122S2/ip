package duke;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FindCommand extends Command {

    private final String searchWord;

    public FindCommand(String command, String searchWord) {
        super(command);
        this.searchWord = searchWord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        int taskCount = 0;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : tasks.getList()) {
            List<String> descriptionWords = Arrays.asList(task.getDescription().split(" "));
            if (descriptionWords.contains(searchWord)) {
                taskCount += 1;
                output.append("\n").append(taskCount).append(".").append(task);
            }
        }
        if (taskCount == 0) {
            ui.showMessage("Uh-oh! There are no matching tasks in your list!");
        } else {
            ui.showMessage(String.valueOf(output));
        }
    }
}

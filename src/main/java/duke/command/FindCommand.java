package duke.command;

import duke.exception.InvalidArgumentException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = Parser.parseDescription(input);
            ui.print(Messages.FIND_MSG);
            int matchCount = 0;
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.get(i).getTaskData().contains(keyword)) {
                    matchCount ++;
                    ui.print(i + 1 + "." + tasks.getTaskStatement(i));
                }
            }
            if (matchCount == 0) {
                ui.showToUser(Messages.NO_FIND_MATCH_MSG);
            } else {
                ui.showToUser(Messages.MATCH_COUNT_MSG(matchCount));
            }
            //use string.contains(string) method to search
        } catch (InvalidArgumentException e) {
            ui.showError(e.getMessage());
        }

    }
}

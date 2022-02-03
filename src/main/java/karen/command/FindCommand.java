package karen.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import karen.KarenException;
import karen.Storage;
import karen.Ui;
import karen.task.Task;

/**
 * To search Task's descriptions to match with a given keyword.
 */
public class FindCommand extends Command {
    protected String keyTerm;

    public FindCommand(String keyTerm) {
        this.keyTerm = keyTerm;
    }

    /**
     * Finds Task with matching description with keyTerm and passes list of matching Tasks
     * to output with UI.
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String output after successful execution of command
     * @throws KarenException
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        Pattern keyTermFormat = Pattern.compile(this.keyTerm);
        ArrayList<Task> matchingList = new ArrayList<>();

        for (Task item: storage.getTaskList()) {
            Matcher checkMatch = keyTermFormat.matcher(item.getDescription());
            if (checkMatch.matches()) {
                matchingList.add(item);
            }
        }

        StringBuilder formatString = new StringBuilder();
        if (matchingList.size() > 0) {
            formatString.append("You made me find to just get this:\n");
        }
        formatString.append(ui.formatTaskList(matchingList));
        return ui.displayUserInput(formatString.toString());
    }
}

package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;
import karen.task.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    protected String keyTerm;

    public FindCommand(String keyTerm) {
        this.keyTerm = keyTerm;
    }


    @Override
    public void execute(Ui ui, Storage storage) throws KarenException {
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
        formatString.append(ui.displayTaskList(matchingList));
        ui.displayUserInput(formatString.toString());
    }
}

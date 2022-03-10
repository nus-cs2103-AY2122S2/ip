package duke;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.util.CustomStringBuilder;
import javafx.application.Platform;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList();
        try {
            storage.load();
        } catch (CustomException e) {
            ui.showLoadingError();
        }
    }

    public String getResponse(String input) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        try {
            if (input.equals("bye")) {
                storage.addToFile();
                customStringBuilder.bulkAppend(ui.endSession());
                Platform.exit();
            } else if (input.equals("list")) {
                customStringBuilder.bulkAppend(TaskList.reportList());
            } else {
                String[] details = input.split(" ", 2);
                String command = details[0];
                if (command.equalsIgnoreCase("mark")) {
                    customStringBuilder.bulkAppend(TaskList.markAsDone(Integer.parseInt(details[1])));
                } else if (command.equalsIgnoreCase("unmark")) {
                    customStringBuilder.bulkAppend(TaskList.markNotDone(Integer.parseInt(details[1])));
                } else if (command.equalsIgnoreCase("delete")) {
                    customStringBuilder.bulkAppend(TaskList.deleteTask(Integer.parseInt(details[1])));
                } else if (command.equalsIgnoreCase("find")) {
                    customStringBuilder.bulkAppend(TaskList.findTasks(details[1]));
                } else if (command.equalsIgnoreCase("snooze")) {
                    customStringBuilder.bulkAppend(TaskList.snooze(details[1]));
                } else {
                    String taskType = details[0];
                    if (!parser.isValidCommand(taskType)) {
                        customStringBuilder.bulkAppend("sorry, this isn't a valid command yet!");
                    } else {
                        customStringBuilder.bulkAppend(TaskList.addTask(taskType, input));
                    }
                }
            }
            customStringBuilder.bulkAppend(ui.addLineBreak());
        } catch (CustomException e) {
            customStringBuilder.bulkAppend(e.getMessage(), "\n", ui.addLineBreak());
        }

        return customStringBuilder.toString();
    }
}

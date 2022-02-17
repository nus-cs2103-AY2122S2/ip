package ari.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ari.tasks.Dateable;
import ari.tasks.Task;

/**
 * Views Task in TaskList that is on viewDate
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    private static final String VIEW_MESSAGE = "Yes Master, here are the tasks you have on %s";
    private static final String NOT_FOUND_MESSAGE = "Dear Master, you do not have anything planned on %s";
    private static final String INDEX_MESSAGE = "%2d. ";

    private LocalDate viewDate;

    public ViewCommand(LocalDate toViewDate) {
        this.viewDate = toViewDate;
    }

    @Override
    public String execute() {
        if (taskList.getSize() == 0) {
            return NOT_FOUND_MESSAGE;
        }

        boolean isBusy = false;

        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format(VIEW_MESSAGE, viewDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))));
        resultString.append("\n");

        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task instanceof Dateable) {
                if (((Dateable) task).getDate().equals(viewDate)) {
                    isBusy = true;
                    resultString.append(String.format(INDEX_MESSAGE, index++));
                    resultString.append(task);
                    resultString.append("\n");
                }
            }
        }

        if (!isBusy) {
            return String.format(NOT_FOUND_MESSAGE, viewDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        }
        return resultString.toString();
    }
}

package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.MissingDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static duke.constant.Message.COLON;
import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.LINE_SEPARATOR;
import static duke.constant.Message.LIST_SCHEDULE;
import static duke.constant.Message.NO_SCHEDULE;
import static duke.constant.Message.SPACE;

public class ViewCommand extends Command {
    private String commandArgument;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public ViewCommand(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MissingDateTimeException, DateTimeException {
        String[] viewDetail = commandArgument.split(SPACE,2) ;
        if (viewDetail.length != 1) {
            throw new MissingDateTimeException();
        }

        LocalDate viewDate = LocalDate.parse(viewDetail[0], inputFormatter);
        TaskList tasksOnDate = new TaskList();
        for (Task task: tasks.getTasks()) {
            if (task instanceof Deadline) {
                LocalDate deadlineDate = ((Deadline) task).getDateInLocalDateTime().toLocalDate();
                if (deadlineDate.equals(viewDate)) {
                    tasksOnDate.addTask(task);
                }
            } else if (task instanceof Event) {
                LocalDate eventDate = ((Event) task).getDateInLocalDateTime().toLocalDate();
                if (eventDate.equals(viewDate)) {
                    tasksOnDate.addTask(task);
                }
            }
        }

        TaskList sortedTasksOnDate = new TaskList();
        ArrayList<Task> sortedArrayList = tasksOnDate.getTasks();
        Collections.sort(sortedArrayList, new TaskComparator());
        sortedTasksOnDate.setTasks(sortedArrayList);

        if (sortedTasksOnDate.getNumberOfTasks() == 0) {
            return ui.printMessage(NO_SCHEDULE + viewDate.format(dayOutputFormatter));
        } else {
            String output = "";
            output += LIST_SCHEDULE + viewDate.format(dayOutputFormatter) + COLON;
            output += LINE_SEPARATOR;
            for (Task task: sortedTasksOnDate.getTasks()) {
                if (task instanceof Deadline) {
                    output += ui.printMessage(LINE_PREFIX +
                            ((Deadline) task).getDateInLocalDateTime().format(outputFormatter) +
                            " " + task.getDescription());
                }
                if (task instanceof Event) {
                    output += ui.printMessage(LINE_PREFIX +
                            ((Event) task).getDateInLocalDateTime().format(outputFormatter) +
                            " " + task.getDescription());
                }
                output += LINE_SEPARATOR;
            }
            return output;
        }


    }

    private static class TaskComparator implements Comparator<Task> {
        public int compare(Task task1, Task task2) {
            LocalDateTime task1DateTime = LocalDateTime.now();
            LocalDateTime task2DateTime = LocalDateTime.now();

            if (task1 instanceof Deadline) {
                task1DateTime = ((Deadline) task1).getDateInLocalDateTime();
            } else if (task1 instanceof Event) {
                task1DateTime = ((Event) task1).getDateInLocalDateTime();
            }
            if (task2 instanceof Deadline) {
                task2DateTime = ((Deadline) task2).getDateInLocalDateTime();
            } else if (task2 instanceof Event) {
                task2DateTime = ((Event) task2).getDateInLocalDateTime();
            }
            return task1DateTime.compareTo(task2DateTime);
        }
    }

}

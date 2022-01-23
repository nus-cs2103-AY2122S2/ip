package Command;

import DukeUtils.CortanaException;
import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;
import Task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ShowAllTasksOnSameDateCommand extends Command{
    private final String time;

    public ShowAllTasksOnSameDateCommand(String time) {
        this.time = time;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?").matcher(time).find();
        LocalDateTime localDateTime;
        LocalDate localDate;
        int numberOfTasksOnSameDate = 0;
        try {
            if (correctTimeFormat) {
                for(Task task: taskList.tasksArrayList) {
                    boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(time).find();
                    if (task instanceof Deadline) { //task is a deadline
                        if (hasTime) { //deadline has date and time
                            localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                            if (((Deadline) task).getBy().equals(localDateTime)) { //all deadlines with the exact same date and time
                                numberOfTasksOnSameDate ++;
                                ui.printTask(task);
                            }
                        } else { //deadline only has date
                            localDate = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-M-d"));
                            if (((Deadline) task).getBy().toLocalDate().equals(localDate)) { //all deadlines with the same date regardless of time
                                numberOfTasksOnSameDate ++;
                                ui.printTask(task);
                            }
                        }
                    } else if (task instanceof Event) { //task is an event
                        if (hasTime) { //event has date and time
                            localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                            if (((Event) task).getAt().equals(localDateTime)) { //all events with the exact same date and time
                                numberOfTasksOnSameDate ++;
                                ui.printTask(task);
                            }
                        } else { //event only has date
                            localDate = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-M-d"));
                            if (((Event) task).getAt().toLocalDate().equals(localDate)) { //all events with the same date regardless of time
                                numberOfTasksOnSameDate ++;
                                ui.printTask(task);
                            }
                        }
                    }
                }
                if (numberOfTasksOnSameDate == 0) { //no tasks found
                    throw new CortanaException("No task found on " + time + "!");
                } else {
                    ui.foundTaskOnSameDate(numberOfTasksOnSameDate, time);
                }
            } else {
                throw new CortanaException("Invalid date time format! Please follow the format yyyy-M-d HHmm!");
            }
        } catch (DateTimeParseException e) {
            throw new CortanaException("Invalid date/time!");
        }
    }
}

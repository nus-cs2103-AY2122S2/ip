package spike.command;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import spike.task.Deadline;
import spike.task.Event;
import spike.task.Task;
import spike.task.TaskList;



/**
 * Reminds user about the upcoming deadlines
 */
public class RemindCommand extends Command {
    // deadlines within this number of days from now will be brought up to user's attention
    private static int withinDays = 1;
    private static final String MSG_REMINDER = "Here are the tasks that will be due soon:\n";

    /**
     * Constructors which allow user to customer the day range for reminder.
     *
     * @param numDays
     */
    public RemindCommand(int numDays) {
        withinDays = numDays;
    }

    @Override
    public String execute(TaskList tasks) {
        AtomicInteger i = new AtomicInteger(1);
        StringBuilder result = new StringBuilder();
        tasks.getTasks().stream().forEach(task -> {
            if ((task instanceof Deadline || task instanceof Event) && withinRange(task)) {
                result.append(i + "." + task + "\n");
            }
            i.getAndIncrement();
        });
        if (!result.toString().equals("")) {
            result.insert(0, MSG_REMINDER);
        }
        return result.toString().trim();
    }


    private static boolean withinRange(Task task) {
        boolean isBefore = task.getDateTime().isBefore(LocalDateTime.now().plusDays(withinDays));
        boolean isAfter = task.getDateTime().isAfter(LocalDateTime.now());
        return isBefore && isAfter;
    }
}

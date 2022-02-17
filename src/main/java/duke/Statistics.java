package duke;

import java.time.LocalDate;

public class Statistics {
    private static final String BORDER = "    ____________________________________________________________\n";
    private static final String SPACING = "    ";
    private final ListStorage myStorage;
    public Statistics(ListStorage myStorage) {
        this.myStorage = myStorage;
    }
    public String getCompletedTask(LocalDate date) {
        String statList = BORDER + SPACING
                            + "Here are the tasks completed during enquiry period:\n";
        int taskCounter = 0;
        for (Task task : myStorage.getMyTasks()) {
            LocalDate taskDate = task.doneOn;
            if (taskDate == null) {
                continue;
            }
            if (date.compareTo(taskDate) <= 0) {
                taskCounter += 1;
                statList += SPACING + taskCounter + ". " + task + "\n";
            }
        }
        if (taskCounter == 0) {
            String noCompletedTasks = BORDER + SPACING
                                        + "You have not completed any tasks within enquiry period.\n"
                                        + BORDER;
            return noCompletedTasks;
        } else {
            statList += BORDER;
            return statList;
        }
    }
}

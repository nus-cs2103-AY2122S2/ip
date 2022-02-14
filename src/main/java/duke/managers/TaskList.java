package duke.managers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;


public class TaskList {
    protected ArrayList<Task> userTaskList;
    protected Ui ui;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        userTaskList = new ArrayList<Task>();
        ui = new Ui();
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Returns exact Task of the specified index.
     *
     * @param taskIdx
     * @return Task
     */
    public Task getTask(int taskIdx) {
        return this.userTaskList.get(taskIdx);
    }

    /**
     * Returns total size of the TaskList (Number of task the user has)
     *
     * @return Size of TaskList
     */
    public int getSize() {
        return this.userTaskList.size();
    }

    /**
     * Returns TaskList as an ArrayList
     *
     * @return TaskList
     */
    public ArrayList<Task> getArrayList() {
        return this.userTaskList;
    }

    /**
     * Adds a task to the user Task List
     *
     * @param task exact task to be added into users' Task List
     * @param toPrint boolean value to decide if we want to execute print statements
     */
    public void addTask(Task task, Boolean toPrint) throws DukeException {
        String tag = task.getTag();
        assert (tag.equals("T") | tag.equals("D") | tag.equals("E"));
        this.userTaskList.add(task);
        if (toPrint) {
            if (tag.equals("T")) {
                ui.print("Fantabulous! You have added this Todo Item:\n" + task);
            } else if (tag.equals("D")) {
                ui.print("Perfect! You have added this Deadline Item:\n" + task
                        + "\nRemember to stick to your objective date!");
            } else if (tag.equals("E")) {
                ui.print("Magnifico! You have added this Event Item:\n" + task
                        + "\nRemember to be there 5 minutes early!");
            } else {
                ui.throwDukeException("This tag does not have a valid tag!"); //When a tasks tag is wrong
            }
        }
    }

    /**
     * Deletes specified user Task from Task List
     *
     * @param taskIndex exact task index to be deleted
     * @throws DukeException if specified taskIndex is not valid in the case of
     * taskIndex <=0 or taskIndex > total size of TaskList)
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (this.checkValidTask(taskIndex)) {
            int originalSize = this.getSize();
            Task userTask = this.userTaskList.get(taskIndex);
            this.userTaskList.remove(taskIndex);
            assert (this.getSize() == originalSize - 1); //Check if size has decreased by 1
            ui.print("I have removed " + userTask);
        }
    }

    /**
     * Checks if a specified task index is value
     *
     * @param taskIndex exact task index to be verified
     * @return true if index is valid
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public boolean checkValidTask(int taskIndex) throws DukeException {
        boolean isNotWithinSizeLimit = taskIndex > this.userTaskList.size() - 1;
        boolean isNegativeIndex = taskIndex < 0;
        if (isNotWithinSizeLimit || isNegativeIndex) {
            ui.throwDukeException("There is no such task! Maybe you entered the wrong task?");
        }
        return true;
    }

    /**
     * Prints all of the users tasks in a nice format
     */
    public void printUserTasks() {
        int numberOfTasks = this.userTaskList.size();
        int counter = 0;
        if (numberOfTasks == 0) {
            ui.print("You currently do not have any outstanding tasks! Great job! :D");
        } else {
            ui.println("Here are your tasks:");
            for (int i = 1; i <= numberOfTasks; i++) {
                Task userTask = this.userTaskList.get(i - 1);
                ui.println((String.valueOf(i) + ": " + userTask));
                if (!userTask.checkIsDone()) {
                    counter += 1;
                }
            }
            assert (counter <= this.getSize());
            if (counter == 0) {
                ui.print("You have completed all your tasks! Great job!");
            } else {
                ui.print("These are all your tasks! Only " + String.valueOf(counter) + " more remaining! Keep going!");
            }
        }
    }

    /**
     * Prints out all of users tasks based on a matching String criteria
     */
    public void printUserTasks(String userFindTask) {
        int numberOfTasks = this.userTaskList.size();
        if (numberOfTasks == 0) {
            ui.print("You currently do not have any outstanding tasks! Great job! :D");
        } else {
            ui.println("Here are your tasks that satisfy your criteria:");
            int counter = 1;
            for (int i = 1; i <= numberOfTasks; i++) {
                Task userTask = this.userTaskList.get(i - 1);
                if (userTask.getTaskDescription().matches("^.*" + userFindTask + ".*$")) {
                    ui.println((String.valueOf(counter) + ": " + userTask));
                    counter++;
                }
            }
            assert (counter <= this.getSize());
            ui.print("");
        }
    }

    /**
     * Checks if a date is between current date and an end date
     *
     * @param taskDeadlineDate
     * @param endDate
     * @return
     */
    public boolean taskWithinDate(LocalDate taskDeadlineDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        long daysFromCurrentDate = ChronoUnit.DAYS.between(currentDate, taskDeadlineDate);
        long daysBeforeReminderEndDate = ChronoUnit.DAYS.between(taskDeadlineDate, endDate);
        boolean isAfterCurrentDate = daysFromCurrentDate >= 0;
        boolean isBeforeReminderEndDate = daysBeforeReminderEndDate >= 0;
        if ((isAfterCurrentDate) & (isBeforeReminderEndDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gives reminder of upcoming deadlines within the next week
     *
     * @param reminderEndDate
     */
    public void remindUserTasks(LocalDate reminderEndDate) {
        int numberOfTasks = this.userTaskList.size();
        String remindEndDateFormatted = reminderEndDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String message = String.format("Here are the upcoming "
                + "deadlines that you have before %s:", remindEndDateFormatted);
        ui.println(message);
        int counter = 1;
        for (int i = 1; i <= numberOfTasks; i++) {
            Task userTask = this.userTaskList.get(i - 1);
            if (userTask instanceof Deadline) {
                if (taskWithinDate(((Deadline) userTask).getDeadlineDate(), reminderEndDate)) {
                    ui.println((String.valueOf(counter) + ": " + userTask));
                    counter++;
                }
            }
        }
        assert (counter <= this.getSize());
        ui.print("");
    }

    /**
     * Marks tasks as done as specified by the task index
     *
     * @param taskIndex exact task index to be marked
     * @param toPrint boolean value to decide if we want to execute print statements
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public void markTaskDone(int taskIndex, Boolean toPrint) throws DukeException {
        //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTaskList.get(taskIndex);
            task.markAsDone();
            if (toPrint) {
                ui.print("Congratulations on completing the task!\n" + task);
            }
            assert (task.checkIsDone() == true);
        }
    }

    /**
     * Marks tasks as not done as specified by the task index
     *
     * @param taskIndex exact task index to be unmarked
     * @param toPrint boolean value to decide if we want to execute print statements
     * @throws DukeException if specified taskIndex is not valid (<=0 or > total size of TaskList)
     */
    public void markTaskNotDone(int taskIndex, Boolean toPrint) throws DukeException {
        //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTaskList.get(taskIndex);
            task.markAsNotDone();
            if (toPrint) {
                ui.print("I have un-marked this task!\n" + task);
            }
            assert (task.checkIsDone() == false);
        }
    }

}

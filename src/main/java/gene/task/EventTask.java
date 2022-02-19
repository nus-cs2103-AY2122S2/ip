package gene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final String taskTitle;
    private final String taskType = "E";
    private boolean markedStatus;
    private final LocalDateTime deadline;

    /**
     * Constructor for event task, this constructor is the default constructor
     * that makes new event tasks. Confirm will be unmarked
     * @param taskTitle title of task
     * @param deadline deadline of task
     */
    public EventTask(String taskTitle, LocalDateTime deadline) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = false;
    }

    /**
     * Constructor to toggle between marked and unmarked to do tasks
     * @param taskTitle the title of task
     * @param deadline deadline of event task
     * @param isMarked whether task is masked or not
     */
    public EventTask(String taskTitle, LocalDateTime deadline, boolean isMarked) {
        super(taskTitle, deadline);
        this.taskTitle = taskTitle;
        this.deadline = deadline;
        this.markedStatus = isMarked;
    }

    /**
     * This method marks tasks to be done
     * @return New marked task
     */
    public Task markTask() {
        assert this.markedStatus == false : "Cannot mark marked tasks";
        if (!this.markedStatus) {
            this.markedStatus = true;
        }

        return this;
    }

    /**
     * This method un marks marked tasks
     * @return New unmarked task
     */
    public Task unmarkTask() {
        assert this.markedStatus == true : "Cannot unmark unmarked tasks";
        if (this.markedStatus) {
            this.markedStatus = false;
        }

        return this;
    }

    public boolean isMarked() {
        return this.markedStatus;
    }

    @Override
    public String toString() {
        String mark = this.markedStatus ? "[X]" : "[ ]";
        String deadlineText = "(at: "
                + deadline.format(
                        DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm a")
        ) + ")";
        return "[E]" + mark + " " + this.taskTitle + " " + deadlineText;
    }

    public boolean containsKeyword(String keyword) {
        return this.taskTitle.contains(keyword);
    }
}

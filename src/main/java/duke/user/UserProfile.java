package duke.user;

import java.time.LocalDate;

import duke.task.TaskList;

public class UserProfile {
    public static final String STORAGE_HEADER = "userinfo";
    public static final int STATS_NO = 2;

    private TaskList taskList;

    private LocalDate dateJoined;
    private int numOfTaskCompleted;

    public UserProfile() {
        this(new TaskList(), LocalDate.now(), 0);
    }

    /**
     * Creates an instance of a UserProfile object.
     *
     * @param taskList the task list of the user.
     * @param dateJoined the date the user started using JJBA.
     * @param numOfTaskCompleted the number of task user completed since the start.
     */
    public UserProfile(TaskList taskList, LocalDate dateJoined, int numOfTaskCompleted) {
        this.taskList = taskList;
        this.dateJoined = dateJoined;
        this.numOfTaskCompleted = numOfTaskCompleted;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        String info = String.format("      Current tasks: %d\n", taskList.size());
        info += String.format("      Task completed: %d\n", numOfTaskCompleted);
        info += String.format("      Date joined: %s\n", dateJoined.toString());

        return info;
    }

    /**
     * Returns the data of the user profile.
     *
     * @return data of the user profile.
     */
    public String toData() {
        String data = taskList.toData();
        data += String.format("[%s]\n", STORAGE_HEADER);
        data += dateJoined.toString() + "\n";
        data += numOfTaskCompleted;

        return data;
    }

    public void addCompletion() {
        numOfTaskCompleted++;
    }

    public void removeCompletion() {
        numOfTaskCompleted--;
    }
}

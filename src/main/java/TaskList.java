import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;
    private int numberOfTasks;

    /**
     * Initializes a new instance of list of tasks, according to the size specified.
     *
     * @param size Size of list of tasks.
     */
    public TaskList(int size) {
        this.listOfTasks = new ArrayList<>(size);
        this.numberOfTasks = 0;
    }

    /**
     * Retrieves the current amount of tasks in the list.
     *
     * @return the total number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * Iterates through the list and prints out each task that is on the list.
     */
    public void display() {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.numberOfTasks; i++) {
            Task currTask = this.listOfTasks.get(i - 1);
            String output = "     " + i + "." + currTask + "\n";
            System.out.println(output);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Marks task by changing its status to "done"
     *
     * @param taskId The taskID that corresponds to the task that is to be marked.
     */
    public void mark(int taskId) {
        Task currTask = this.listOfTasks.get(taskId - 1);
        currTask.setStatus(true);
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + currTask + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Unmarks task by changing its status to "not done".
     *
     * @param taskId The taskID that corresponds to the task that is to be unmarked.
     */
    public void unmark(int taskId) {
        Task currTask = this.listOfTasks.get(taskId - 1);
        currTask.setStatus(false);
        System.out.println("    ____________________________________________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       " + currTask + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Adds the to-do task into the list
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public void todo(String userInput) {
        this.numberOfTasks++;

        Task currentTask = new Todo(userInput);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Adds the task (which has a deadline) into the list
     *
     * @param userInput Command entered by the user that describes the task.
     * @param dueDate Specifies the date that this task has to be done by.
     * @param dueTime Specifies the time that this task has to be done by.
     */
    public void deadline(String userInput, LocalDate dueDate, LocalTime dueTime) {
        this.numberOfTasks++;

        Task currentTask = new Deadline(userInput, dueDate, dueTime);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Adds the event task into the list
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public void event(String userInput, LocalDate eventDate,
                      LocalTime eventStartTime, LocalTime eventEndTime) {
        this.numberOfTasks++;

        Task currentTask = new Event(userInput, eventDate, eventStartTime, eventEndTime);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Deletes task that corresponds to the specified task ID.
     *
     * @param taskId The taskID that corresponds to the task that is to be deleted.
     */
    public void delete(int taskId) {
        Task currTask = this.listOfTasks.get(taskId - 1);
        this.listOfTasks.remove(taskId - 1);
        this.numberOfTasks--;
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + currTask + "\n"
                + "    ____________________________________________________________\n");
    }
}
import java.io.FileWriter;
import java.io.IOException;
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
     * Retrieves the array list of task.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void incrementTasks(){
        this.numberOfTasks++;
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
        writeToFile();
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
        writeToFile();
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
        writeToFile();

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
     */
    public void deadline(String userInput, String by) {
        this.numberOfTasks++;

        Task currentTask = new Deadline(userInput, by);
        this.listOfTasks.add(currentTask);
        writeToFile();

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
    public void event(String userInput, String at) {
        this.numberOfTasks++;

        Task currentTask = new Event(userInput, at);
        this.listOfTasks.add(currentTask);
        writeToFile();

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
        writeToFile();

        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + currTask + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Converts the TaskList into a single string file, to be stored in data.txt on the hard disk.
     *
     * @return All tasks in the form of a String.
     */
    private String parseList() {
        StringBuilder res = new StringBuilder();
        for (Task listOfTask : listOfTasks) {
            String currTask = listOfTask.toString();
            res.append(currTask).append("\n");
        }
        return res.toString();
    }

    /**
     * Save the tasks in the hard disk automatically whenever the task list changes.
     */
    private void writeToFile() {
        String filePath = Duke.DATA_PATH;
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            fw.write(parseList());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occured when writing to data file!\n"
                    + "Please try again later.");
            e.printStackTrace();
        }
    }
}
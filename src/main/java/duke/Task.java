package duke;

/**
 * Task Class that contains the name/details of the task and whether it has been completed
 */
public class Task {
    //Attributes of a task
    private String taskName;
    private boolean done;

    /**
     * Constructor for task, always initialised to be not done
     *
     * @param taskName name/details of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    /**
     * Marks the task as completed/uncompleted
     *
     * @param done boolean dictating whether task is done or undone
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Prints out the status of the task and the details
     *
     * @return boolean value of whether task is or is not done
     */
    public boolean getDone() {
        return this.done;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Prints out the status of the task and the details
     */
    public void printTask() {
        if (this.getDone()) {
            System.out.println("[X] " + this.getTaskName());
        } else {
            System.out.println("[ ] " + this.getTaskName());
        }
    }

    /**
     * returns the string of each task together with their status and name to the GUI
     * @return the string of each task together with their status and name to the GUI
     */
    public String guiPrintTask() {
        String output;
        if (this.getDone()) {
            output = "[X] " + this.getTaskName();
        } else {
            output = "[ ] " + this.getTaskName();
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            if (this.taskName == ((Task) o).taskName) {
                return true;
            }
        }
        return false;
    }
}

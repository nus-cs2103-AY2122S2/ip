package siri;
/**
 * Class for the individual items of the task list, with int done to be indicative of whether the task had been completed and String item to be the name of the task.  
 */

class Task {
    protected int done;
    protected String item;

    /**
     * Constructor for Task class.
     * 
     * @param item a String to description of the Task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     */
    Task(String item, int done) {
        this.done = done;
        this.item = item;
    }

    /**
     * Method to return a symbol the doneness of the task. 
     * If task is done, return 'X', else return empty space to indicate task not done.
     * 
     * @param done integer to indicate whether done or undone. 1 represents the done and 0 represents undone.
     * @return a string " " to indicate the task had not been completed and "X" to indicate the task had be completed.
     */
    private String donenessSymbol(int done) {
        if (done == 0) {
            return " ";
        } else {
            return "X";
        }
    }

    /**
     * Method to mark the task as done.
     */
    public void markDone() {
        done = 1;
        System.out.printf("Great job for completing task:\n%s\n", this.getItemAndStatus());
    }

    /**
     * Method to mark task as undone.
     */
    public void markUndone() {
        done = 0;
        System.out.printf("Task marked as uncompleted:\n%s\n", this.getItemAndStatus());
    }

    /**
     * Method to get String of task status and task name.
     * 
     * @return String representation of the task details, including whether it is done and task name.
     */
    public String getItemAndStatus() {
        String returned = "[" + donenessSymbol(this.done) + "] " + this.item;
        return returned;
    }

    /**
     * Method to return the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    public String saveData() {
        String returned = this.done + " " + this.item;
        return returned;
    }
}
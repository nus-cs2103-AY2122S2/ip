/**
    Class for the individual items of the to do list, with int done to be indicative of
    whether the task had been completed and String item to be the name of the task.
 */

class Task {
    protected int done;
    protected String item;

    Task(String item) {
        done = 0;
        this.item = item;
    }

    /*
        Method to return a symbol the doneness of the task:
        If task is done, return 'X', else return empty space to indicate task not done.
     */
    private String donenessSymbol(int done) {
        if (done == 0) {
            return " ";
        } else {
            return "X";
        }
    }

    /*
        Method to mark task done.
    */
    public void markDone() {
        done = 1;
        System.out.printf("Great job for completing task: %s\n", this.getItemAndStatus());
    }

    /*
        Method to mark task undone.
    */
    public void markUndone() {
        done = 0;
        System.out.printf("Task marked as uncompleted: %s\n", this.getItemAndStatus());
    }

    /*
        Method to get String of task status and task name.
    */
    public String getItemAndStatus() {
        String returned = "[" + donenessSymbol(this.done) + "] " + this.item;
        return returned;
    }
}
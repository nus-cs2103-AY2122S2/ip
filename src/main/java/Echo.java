public class Echo {

    /** TaskList. */
    private final TaskList taskList;

    /**
     * Constructor for TaskList class.
     */
    public Echo() {
        this.taskList = new TaskList();
    }
    /**
     * Add taskList and replies that it has been added.
     *
     * @param s String to be printed.
     */
    public void addTask(String s) {
        taskList.add(s);
        System.out.println("        added: " + s);
    }

    /**
     * Returns taskList.
     */
    public void getTask() {
        System.out.println(taskList.toString());
    }

    /**
     * Marks taskList.
     *
     * @param i TaskList.
     */
    public void mark(int i) {
        taskList.mark(i);
    }

    /**
     * Unmark taskList.
     *
     * @param i TaskList.
     */
    public void unmark(int i) {
        taskList.unmark(i);
    }

    /**
     * Prints goodbye.
     */
    public void exit() {
        System.out.println("        Goodbye!");
    }
}

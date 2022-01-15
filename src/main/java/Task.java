public class Task {

    /** Array of task. */
    private final String[] task;

    /** Length of task. */
    private int count;

    /**
     * Constructor for Task class.
     */
    public Task() {
        this.task = new String[100];
        this.count = 0;
    }

    /**
     * Add string to the task.
     *
     * @param s String to be added to task.
     */
    public void add(String s) {
        task[count++] = String.format("        %d. %s", count, s);
    }

    /**
     * String representation of task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (String s : task) {
            if (s == null) break;
            sb.append(prefix);
            prefix = "\n";
            sb.append(s);
        }
        return sb.toString();
    }
}

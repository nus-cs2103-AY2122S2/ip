public class Commands {

    private final TaskHistory taskHistory = new TaskHistory();

    public Commands() { // Empty Constructor
    }

    void addTask(String input) { // Get DukeLCH to Echo
        taskHistory.addTo(input); // Add new input entry into history
        String echo = "_______________________________________________________\n"
                + "added: " + input + "\n"
                + "_______________________________________________________\n";
        System.out.println(echo);
    }

    void bye() { // Get DukeLCH to Exit
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }

    void list() { // Get DukeLCH to List cmdHistory
        String border = "_______________________________________________________\n";
        System.out.println(border + taskHistory.printAll() + border);
    }

    void mark(int index) {
        taskHistory.getTask(index).markTask();
        String msg = "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + taskHistory.getTask(index).getDescription()
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void unmark(int index) {
        taskHistory.getTask(index).unmarkTask();
        String msg = "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + taskHistory.getTask(index).getDescription()
                + "_______________________________________________________\n";
        System.out.println(msg);
    }
}

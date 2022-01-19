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
        System.out.println(border
                + "These are your tasks that we have in our records:\n"
                + taskHistory.printAll() + border);
    }

    void mark(int index) {
        taskHistory.getTask(index).markTask();
        String msg = "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + taskHistory.getTask(index).getTask()
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void unmark(int index) {
        taskHistory.getTask(index).unmarkTask();
        String msg = "_______________________________________________________\n"
                + "A reminder that the following task has not been done:\n"
                + taskHistory.getTask(index).getTask()
                + "_______________________________________________________\n";
        System.out.println(msg);
    }

    void todo(String[] tokens) {
        String description = "";
        for (int i = 1; i < tokens.length; i++) {
            description = description.concat(tokens[i]);
            if (i != (tokens.length - 1)) {
                description = description.concat(" ");
            }
        }
        taskHistory.addToDo(description);
    }

    void deadline(String[] tokens) {

    }

    void event(String[] tokens) {

    }
}

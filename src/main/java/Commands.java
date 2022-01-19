public class Commands {

    InputHistory cmdHistory = new InputHistory();

    public Commands() { // Empty Constructor
    }

    void echoInput(String input) { // Get DukeLCH to Echo
        cmdHistory.addTo(input); // Add new input entry into history
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
        System.out.println(border + cmdHistory.printAll() + border);
    }
}

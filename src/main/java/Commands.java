public class Commands {

    public Commands() { // Empty Constructor
    }

    void echoInput(String input) { // Get DukeLCH to Echo
        String echo = "_______________________________________________________\n"
                + input + "\n"
                + "_______________________________________________________\n";
        System.out.println(echo);
    }

    void bye() { // Get DukeLCH to Exit
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }
}

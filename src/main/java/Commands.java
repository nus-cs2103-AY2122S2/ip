public class Commands {

    public Commands() {
    }

    void echoInput(String input) {
        String echo = "_______________________________________________________\n"
                + input + "\n"
                + "_______________________________________________________\n";
        System.out.println(echo);
    }

    void bye() {
        String bye = "_______________________________________________________\n"
                + "Goodbye. I hope to be of service to you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }
}

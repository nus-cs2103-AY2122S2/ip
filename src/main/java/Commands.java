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
                + "Bye. Hope to see you again soon!\n"
                + "_______________________________________________________\n";
        System.out.println(bye);
    }
}

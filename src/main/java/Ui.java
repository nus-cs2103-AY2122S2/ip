public class Ui {
    //Greeting
    //Logo
    //Goodbye
    private final static String GREETING = "Hello! I'm ";
    private final static String LOGO = " GGGG                      \n"
            + "G    G   eeee   n nnn    eeee \n"
            + "G       e    e  nn   n  e    e\n"
            + "G  GGG  eeeeee  n    n  eeeeee\n"
            + "G    G  e       n    n  e     \n"
            + "G    G  e    e  n    n  e    e\n"
            + " GGGG    eeee   n    n   eeee ";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";
    //command keys to enum

    public void print() {

    }

    public void print(String toPrint) {
        System.out.print(toPrint);
    }

    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
    }

    public void printUnrecognised() {
        System.out.println("----------------------------" +
                "----------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                + "--------------------------------------------------------");
    }

    public static void exitProg() {
        System.out.println("--------------------------------" +
                "------------------------\n"
                + GOODBYE
                + "\n"
                + "--------------------------------------------------------");
    }
}

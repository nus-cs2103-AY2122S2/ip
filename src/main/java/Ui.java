public class Ui {
    // greeting message
    String greetings = "    Hi there! 👋 I'm Duke\n"
            + "    What can I do for you?";

    // divider
    String LINES = "    ---------------------------------";
    public void greet() {
        System.out.println(LINES);
        System.out.println(greetings);
        System.out.println(LINES);
    }

}

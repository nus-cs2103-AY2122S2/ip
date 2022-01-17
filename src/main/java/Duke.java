public class Duke {
    private static final String SEPARATOR = "------------------------------------";
    private static final String BOT_NAME = "Megumin";

    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
        System.out.println(SEPARATOR);
        System.out.println("Hi! I'm " + BOT_NAME);
        System.out.println("What do you need?");
        System.out.println(SEPARATOR);
    }
}

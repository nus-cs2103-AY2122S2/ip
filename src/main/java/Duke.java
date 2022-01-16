import java.util.Scanner;

public class Duke {
    private static final String BOT_ART =
            "     /\\   | |   | |          \n" +
            "    /  \\  | |__ | |__  _   _ \n" +
            "   / /\\ \\ | '_ \\| '_ \\| | | |\n" +
            "  / ____ \\| |_) | |_) | |_| |\n" +
            " /_/    \\_\\_.__/|_.__/ \\__, |\n" +
            "                        __/ |\n" +
            "                       |___/ ";
    private static final String BOT_NAME = "Abby";

    public void output(String output) {
        String result =
                "____________________________________________________________\n\n" +
                output +
                "\n____________________________________________________________\n\n";
        System.out.printf("%s", result);
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public void start() {
        Input input = null;
        Scanner sc = new Scanner(System.in);

        output(BOT_ART + "\nHello! I'm " + BOT_NAME + "\nWhat can I do for you?\n");
            
        while (true) {
            input = new Input(sc.nextLine());

            switch (input.getStates()) {
                case ECHO:
                    output(input.getInput());

                    break;
                case BYE:
                    output(bye());

                    break;
                default:
                    break;
            }

            if (input.getStates() == Input.States.BYE) {
                break;
            }
        }

        sc.close(); 
    }

    public static void main(String[] args) {
        Duke abby = new Duke();

        abby.start();
    }
}

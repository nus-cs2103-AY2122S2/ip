import java.util.Scanner;

public class Duke {

//    private final static String divPadding = "    ";
    private final static String strPadding = "      ";
    private static boolean exitFlag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greet();

        while(!exitFlag) {
            echoInput(sc.nextLine());
        }

        sc.close();
    }

    private static void greet() {
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        botResponse(greeting);
    }

    private static void botResponse(String resString) {
        String divString = "    -----------------------------------------";

        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    private static void echoInput(String input) {
        if (input.equals("exit")) {
            botResponse("Pleasure to be of service, see you soon!");
            exitFlag = true;
        } else {
            botResponse(input);
        }
    }
}

import java.util.Scanner;

public class Duke {

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;

        while(!exitFlag) {
            String input = sc.nextLine();
            if (input.equals("exit")) {
                botResponse("Pleasure to be of service, see you soon!");
                exitFlag = true;
            } else {
                botResponse(input);
            }
        }
        sc.close();
    }

    private static void botResponse(String resString) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";

        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    private static void greet() {
        String strPadding = "      ";
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        botResponse(greeting);
    }

    public static void main(String[] args) {
        greet();
        run();
    }

}

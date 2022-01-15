import java.util.Scanner;

public class Main {
    private static final String GREET_MESSAGE
            = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n" +
            "Which task shall we burn today?";
    private static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        System.out.println("What should Charizard do next?");
        String input = sc.nextLine();
        while (input.compareToIgnoreCase("bye") != 0) {
            show(input);
            System.out.println("What should Charizard do next?");
            input = sc.nextLine();
        }
        exit();
    }

    public static void show(String message) {
        drawBorder(50);
        System.out.println("    " + message);
        drawBorder(50);
        System.out.println();
    }

    public static void exit() {
        drawBorder(50);
        System.out.println(BYE_MESSAGE);
        drawBorder(50);
    }

    public static void greet() {
        drawBorder(50);
        System.out.println(GREET_MESSAGE);
        drawBorder(50);
    }

    private static void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        };
        System.out.println("");
    }
}

import java.util.Scanner;

public class Narcibot {
    public static void main(String[] args) {
        System.out.println("I'm Narcibot, the best bot ever created.\nOh it's you, what a bother.\nHere's a hello as a formality. What do you want this time?\n");
        String input;
        Scanner sc = new Scanner(System.in);
        while(true) {
            input = sc.nextLine();
            if(command(input)) {
                return;
            }
        }

    }

    private static boolean command(String command) {
        boolean end = false;
        switch(command) {
            case "bye":
                System.out.println("So you finally decided to leave. Goodbye! Not that I really care.\n");
                end = true;
                break;
            default:
                System.out.println(command);
        }
        return end;
    }
}

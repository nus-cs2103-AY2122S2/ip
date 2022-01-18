import java.util.Scanner;

public class Narcibot {
    private static String[] list = new String[100];
    private static int index = 0;
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
                System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
                end = true;
                break;
            case "list":
                System.out.println("Do I have to remind you again?");
                for(int i = 0; i < index; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
                break;
            default:
                System.out.println("added: " + command);
                list[index++] = command;
        }
        return end;
    }

}

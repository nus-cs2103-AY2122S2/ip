import java.util.Scanner;

public class Narcibot {
    private static Task[] list = new Task[100];
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
        String delims ="[ ]+";
        String[] tokens = command.split(delims);
        switch(tokens[0]) {
            case "bye":
                System.out.println("So you finally decided to leave. Goodbye! Not that I really care.");
                end = true;
                break;
            case "list":
                System.out.println("Do I have to remind you again?");
                for(int i = 0; i < index; i++) {
                    String status = list[i].getStatus() ? "x" : " ";
                    System.out.println((i+1) + ".[" + status + "] " + list[i].getName());
                }
                break;
            case "mark":
                System.out.println("You finally did something? I'll mark it for you then.");
                list[Integer.parseInt((tokens[1])) - 1].markDone();
                String message1 = "[x] " + list[Integer.parseInt(tokens[1]) - 1].getName();
                System.out.println(message1);
                break;
            case "unmark":
                System.out.println("As expected... another task that wasn't finished at all.");
                list[Integer.parseInt((tokens[1])) - 1].markNotDone();
                String message2 = "[ ] " + list[Integer.parseInt(tokens[1]) - 1].getName();
                System.out.println(message2);
                break;
            default:
                System.out.println("added: " + command);
                list[index++] = new Task(command);
        }
        return end;
    }

}

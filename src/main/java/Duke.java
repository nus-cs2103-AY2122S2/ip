import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean endProgram = false;
        welcomeMessage();

        while(!endProgram) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                endProgram();
                endProgram = true;
                break;
            }
            drawDivider();
            System.out.println("    " + input);
            drawDivider();
        }
    }

    private static void drawDivider() {
        System.out.println("________________________________________");
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawDivider();
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        drawDivider();
    }

    private static void endProgram() {
        drawDivider();
        System.out.println("    " + "Bye. Hope to see you again soon!");
        drawDivider();
    }
}

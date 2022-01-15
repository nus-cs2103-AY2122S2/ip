import java.util.Scanner;

public class Duke {
    static String line = "------------------------------------";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = "/\\___ \\                            __           \n"
                + "\\/__/\\ \\     __     _ __   __  __ /\\_\\    ____  \n"
                + "   _\\ \\ \\  /'__`\\  /\\`'__\\/\\ \\/\\ \\\\/\\ \\  /',__\\ \n"
                + "  /\\ \\_\\ \\/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_/ |\\ \\ \\/\\__, `\\\n"
                + "  \\ \\____/\\ \\__/.\\_\\\\ \\_\\  \\ \\___/  \\ \\_\\/\\____/\n"
                + "   \\/___/  \\/__/\\/_/ \\/_/   \\/__/    \\/_/\\/___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?");
        System.out.println(line);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                System.out.println(line);
                System.out.println("Goodbye! I'll be here if you need anything else.");
                System.out.println(line);
                break;
            }
            else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
    }
}



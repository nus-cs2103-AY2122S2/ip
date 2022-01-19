import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        String logo = "\n" +
                " ____  ____     _       _____     ________  \n" +
                "|_  _||_  _|   / \\     |_   _|   |_   __  | \n" +
                "  \\ \\  / /    / _ \\      | |       | |_ \\_| \n" +
                "   \\ \\/ /    / ___ \\     | |   _   |  _| _  \n" +
                "   _|  |_  _/ /   \\ \\_  _| |__/ | _| |__/ | \n" +
                "  |______||____| |____||________||________| \n" +
                "                                            \n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = receiveInput(scanner);
            produceOutput(command);
            if (checkExit(command)) {
                break;
            }
        }
    }

    public static String receiveInput(Scanner scanner) {
        String input = scanner.nextLine();
        return input;
    }

    public static void produceOutput(String input) {
        if (checkExit(input)) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            echo(input);
        }
    }
    public static void echo(String input) {
        System.out.println(input);
    }

    public static boolean checkExit(String input) {
        return input.equals("bye");
    }

}

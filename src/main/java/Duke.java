import java.util.Scanner;

public class Duke {
    static String line = "------------------------------------";
    static Scanner scanner = new Scanner(System.in);
    static String[] arr = new String[100];

    public static void main(String[] args) {
        int counter = 0;
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
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Goodbye! I'll be here if you need anything else.");
                System.out.println(line);
                break;
            }
            else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + arr[i] +"\n");
                }
                System.out.println(line);
            }
            else {
                System.out.println(line);
                System.out.println("added: " + input);
                arr[counter] = input;
                counter += 1;
                System.out.println(line);
            }
        }
    }
}



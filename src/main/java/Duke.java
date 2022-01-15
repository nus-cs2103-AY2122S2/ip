import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(generateIntro());
        // Starting input loop
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            String input = sc.nextLine();
            if (input.equals("quit") || input.equals("exit")) {
                done = true;
                System.out.println(generateGoodbye());
            } else {
                System.out.println(generateResponse(input));
            }
        }
    }

    public static String generateResponse(String input) {
        String temp = "<---------------------------------------------------------->\n";
        return temp + input + "\n" + temp;
    }

    public static String generateIntro() {
        String welcomeMessage = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
        return generateResponse(welcomeMessage);
    }

    public static String generateGoodbye() {
        String goodbyeMessage = "Goodbye!";
        return generateResponse(goodbyeMessage);
    }
}

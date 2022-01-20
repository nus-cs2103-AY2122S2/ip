import java.util.Scanner;

public class ISTJBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean doneChatting = false;

        String welcomeMessage = "Hello! I'm ISTJBot. \n" + "What can I do for you? \n";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            String request = sc.nextLine();
            if (request.equals("bye")) {
                doneChatting = true;
                printResponse("Bye, I'll be organizing your tasks until you come back.");
                sc.close();
            } else {
                printResponse(request);
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*__________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }
}

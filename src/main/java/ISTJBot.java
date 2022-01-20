import java.util.Scanner;
import java.util.ArrayList;

public class ISTJBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean doneChatting = false;
        ArrayList<Task> tasks = new ArrayList<>();
        String list = "";

        String welcomeMessage = "Hello! I'm ISTJBot. \n" + "What can I do for you?";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            String request = sc.nextLine();
            if (request.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    if (i == tasks.size()) {
                        list += i + ". " + tasks.get(i - 1).toString();
                    } else {
                        list += i + ". " + tasks.get(i - 1).toString() + "\n";
                    }
                }
                printResponse(list);
            }
            else if (request.equals("bye")) {
                doneChatting = true;
                printResponse("Bye, I'll be organizing your tasks until you come back.");
                sc.close();
            } else {
                Task TaskAdded = new Task(request);
                tasks.add(TaskAdded);
                printResponse("added: " + TaskAdded.toString());
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*__________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }
}

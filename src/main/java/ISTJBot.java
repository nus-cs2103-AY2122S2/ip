import java.util.Scanner;
import java.util.ArrayList;

public class ISTJBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean doneChatting = false;
        ArrayList<Task> tasks = new ArrayList<>();
        String list = "Here are the tasks in your list:\n";

        String welcomeMessage = "Hello! I'm ISTJBot. \n" + "What can I do for you?";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            String request = sc.nextLine();
            String[] requestInfo = request.split(" ");

            if (requestInfo[0].equals("mark")) {
                int taskNumber = Integer.parseInt(requestInfo[1]);
                tasks.get(taskNumber - 1).mark();
                printResponse("Good. I've marked this task as done: \n" + tasks.get(taskNumber - 1).toString());
            } else if (requestInfo[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(requestInfo[1]);
                tasks.get(taskNumber - 1).unmark();
                printResponse("I've marked this task as not done yet: \n" + tasks.get(taskNumber - 1).toString());
            } else if (requestInfo[0].equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    if (i == tasks.size()) {
                        list += i + ". " + tasks.get(i - 1).toString();
                    } else {
                        list += i + ". " + tasks.get(i - 1).toString() + "\n";
                    }
                }
                printResponse(list);
                list = "Here are the tasks in your list:\n";
            }
            else if (requestInfo[0].equals("bye")) {
                doneChatting = true;
                printResponse("Bye, I'll be organizing your tasks until you come back.");
                sc.close();
            } else {
                Task TaskAdded = new Task(request);
                tasks.add(TaskAdded);
                printResponse("added: " + request);
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*__________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }
}

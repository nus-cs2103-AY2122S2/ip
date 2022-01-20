import java.util.Scanner;
import java.util.ArrayList;

public class ISTJBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean doneChatting = false;
        ArrayList<Task> tasks = new ArrayList<>();
        String list = "Here are the task(s) in your list:";

        String welcomeMessage = "Hello! I'm ISTJBot. \n" + "What can I do for you?";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            String request = sc.nextLine();
            String[] requestInfo = request.split(" ");
            String command = requestInfo[0];

            if (command.equals("todo")) {
                String description = "";
                for (int i = 1; i < requestInfo.length; i++) {
                    description += requestInfo[i] + " ";
                }
                Task taskAdded = new Todo(description);
                tasks.add(taskAdded);
                String messageStart = "I will add this task right now. \n";
                String messageLast = "Now you have " + tasks.size() + " ";
                String plural = tasks.size() > 1 ? "tasks" : "task";
                messageLast += plural + " in the list. Let's go!";
                printResponse(messageStart + taskAdded.toString() + "\n" + messageLast);

            } else if (command.equals("deadline")) {
                String description = "";
                int byIndex = -1;
                String by = "";
                for (int i = 1; i < requestInfo.length; i++) {
                    if (requestInfo[i].equals("/by")) {
                        byIndex = i;
                        break;
                    }
                    description += requestInfo[i] + " ";
                }
                for (int i = byIndex + 1; i < requestInfo.length; i++) {
                    by += i == requestInfo.length - 1 ? requestInfo[i] : requestInfo[i] + " ";
                }
                Task taskAdded = new Deadline(description, by);
                tasks.add(taskAdded);
                String messageStart = "I will add this task right now. \n";
                String messageLast = "Now you have " + tasks.size() + " ";
                String plural = tasks.size() > 1 ? "tasks" : "task";
                messageLast += plural + " in the list. Let's go!";
                printResponse(messageStart + taskAdded.toString() + "\n" + messageLast);

            } else if (command.equals("event")) {
                String description = "";
                int atIndex = -1;
                String at = "";
                for (int i = 1; i < requestInfo.length; i++) {
                    if (requestInfo[i].equals("/at")) {
                        atIndex = i;
                        break;
                    }
                    description += requestInfo[i] + " ";
                }
                for (int i = atIndex + 1; i < requestInfo.length; i++) {
                    at += i == requestInfo.length - 1 ? requestInfo[i] : requestInfo[i] + " ";
                }
                Task taskAdded = new Event(description, at);
                tasks.add(taskAdded);
                String messageStart = "I will add this task right now. \n";
                String messageLast = "Now you have " + tasks.size() + " ";
                String plural = tasks.size() > 1 ? "tasks" : "task";
                messageLast += plural + " in the list. Let's go!";
                printResponse(messageStart + taskAdded.toString() + "\n" + messageLast);

            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(requestInfo[1]);
                tasks.get(taskNumber - 1).mark();
                printResponse("Good. I've marked this task as done: \n" + tasks.get(taskNumber - 1).toString());

            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(requestInfo[1]);
                tasks.get(taskNumber - 1).unmark();
                printResponse("Got it. I've unmarked this task: \n" + tasks.get(taskNumber - 1).toString());

            } else if (command.equals("list")) {
                if (tasks.size() != 0) {
                    list += "\n"; // for spacing purpose
                }
                for (int i = 1; i <= tasks.size(); i++) {
                    list += i != 1 ? "\n" + i + ". " + tasks.get(i - 1).toString() : i + ". " + tasks.get(i - 1).toString();
                }
                printResponse(list);
                list = "Here are the tasks in your list:";

            } else if (command.equals("bye")) {
                doneChatting = true;
                printResponse("Bye, I'll be organizing your tasks until you come back.");
                sc.close();

            } else {
                printResponse("Not a valid command");
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*__________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }
}

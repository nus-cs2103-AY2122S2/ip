import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bot = "Hello! I'm Duke\nWhat can I do for you?", response = "";
        ArrayList<Task> tasks = new ArrayList<Task>();

        printLines();
        System.out.println(bot);
        printLines();

        while(response.compareTo("bye") != 0) {
            response = reader.readLine();
            if (response.compareTo("bye") == 0) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                break;
            } else if (response.compareTo("list") == 0) {
                printLines();
                System.out.println("Here are the tasks in your list:");
                listAllTasks(tasks);
                printLines();
//                Handle Mark command
            } else {
                printLines();
                String[] split = response.split("\\s+");
                if (split[0].contains("mark")) {
                    int digit = Integer.parseInt(split[1]);
                    if (digit > 0 && digit <= tasks.size()) {
                        handleResponse(split[0]);
                        Task t = tasks.get(digit - 1);
                        t.unmark();
                        System.out.println(t.toString());
                    } else {
                        System.out.println("Invalid range! Try again.");
                    }
                } else {
                    handleResponse(split[0]);
                    Task newTask = null;
                    boolean valid = true;
                    switch (split[0]) {
                        case "todo":
                            newTask = new Todo(removeSubString(response, "todo "));
                            break;
                        case "deadline":
                            String[] deadlineSplit = response.split(" /by ");
                            newTask = new Deadline(deadlineSplit[0].replace("deadline ", ""), deadlineSplit[1]);
                            break;
                        case "event":
                            String[] eventSplit = response.split(" /at ");
                            newTask = new Event(eventSplit[0].replace("event ", ""), eventSplit[1]);
                            break;
                        default:
                            valid = false;
                    }
                    if (valid == true) {
                        tasks.add(newTask);
                        System.out.println(newTask.toString());
                        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
                    } else {
                        System.out.println("Invalid command! Please try again.");
                    }
                }
                printLines();
            }
        }
    }

// ____________________________________________________________
//                  START OF HELPER METHODS
// ____________________________________________________________
    public static void printLines() {
        System.out.println("____________________________________________________________");
    }

    public static void listAllTasks(ArrayList<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
    }

    public static String removeSubString(String response, String word) {
        return response.replace(word, "");
    }

// ____________________________________________________________
//                  START OF COMMAND HANDLER
// ____________________________________________________________
    public static void handleResponse(String res) {
        switch (res) {
            case "mark":
                System.out.println("Nice! I've marked this task as done:");
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:");
                break;
            case "todo":
                System.out.println("Got it. I've added this task:");
        }
    }
}


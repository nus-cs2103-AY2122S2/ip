import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bot = "Hello! I'm Duke\nHere is a list of commands for your reference!\n";
        String response = "";

        ArrayList<Task> tasks = new ArrayList<Task>();

        printLines();
        System.out.println(bot);
        printHelp();
        printLines();

        do {
            response = reader.readLine().trim();
            if (response.equals("bye")) {
                printLines();
                System.out.println("Bye. Hope to see you again soon!");
                printLines();
                break;
            } else if (response.equals("list")) {
                printLines();
                System.out.println("Here are the tasks in your list:");
                listAllTasks(tasks);
                printLines();
//                Handle Mark command
            } else {
                boolean valid = true;
                Task newTask = null;

                printLines();

                if (response.equals("")) {
                    valid = false;
                } else {
                    String[] split = response.split("\\s+");
                    if (split[0].contains("mark") || split[0].contains("delete")) {
                        int digit = Integer.parseInt(split[1]);
                        if (digit > 0 && digit <= tasks.size()) {
                            handleResponse(split[0]);
                            if (split[0].contains("mark")) {
                                Task t = tasks.get(digit - 1);
                                t.unmark();
                                System.out.println(t.toString());
                            } else {
                                System.out.println(tasks.get(digit - 1).toString());
                                tasks.remove(digit - 1);
                                printNumberOfTask(tasks);
                            }
                        } else {
                            System.out.println("Task does not exist! Try again.");
                        }
                    } else {
                        if (split.length > 1) {
                            String[] secondSplit;
                            if (split[0].equals("todo")) {
                                newTask = new Todo(removeSubString(response, "todo "));
                            } else if (split[0].equals("deadline")) {
                                secondSplit = response.split(" /by ");
                                if (secondSplit.length > 1) {
                                    newTask = new Deadline(removeSubString(secondSplit[0], "deadline "), secondSplit[1]);
                                } else { valid = false; }
                            } else if (split[0].equals("event")) {
                                secondSplit = response.split(" /at ");
                                if (secondSplit.length > 1) {
                                    newTask = new Event(removeSubString(secondSplit[0], "event "), secondSplit[1]);
                                } else { valid = false; }
                            } else { valid = false; }
                            if (valid) {
                                handleResponse(split[0]);
                                tasks.add(newTask);
                                System.out.println(newTask.toString());
                                printNumberOfTask(tasks);
                            }
                        } else {
                            valid = false;
                        }
                    }
                }
                if (valid == false) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                printLines();
            }
        } while(!response.equals("bye"));
    }

// ____________________________________________________________
//                  START OF HELPER METHODS
// ____________________________________________________________
    public static void printLines() {
        System.out.println("____________________________________________________________");
    }

    public static void printHelp() {
        System.out.println("1. todo [task]");
        System.out.println("2. deadline [task] /by [date and/or time]");
        System.out.println("3. event [task] /at [location]");
        System.out.println("4. list");
        System.out.println("5. mark X (mark X task as done");
        System.out.println("6. unmark X (mark X task as undone");
        System.out.println("7. delete X (delete X task from the list");
    }

    public static void listAllTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have 0 task at the moment. Start by adding these commands:\n");
            printHelp();
        } else {
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    public static void printNumberOfTask(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
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
            case "delete":
                System.out.println("Noted. I've removed this task:");
                break;
            case "todo":
            case "deadline":
            case "event":
                System.out.println("Got it. I've added this task:");
                break;
        }
    }
}


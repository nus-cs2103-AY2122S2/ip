import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Duke {
    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();
        Set<String> setOfAllTasks = new HashSet<>();
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while(true) {
            if (input.equalsIgnoreCase("bye")) { //bye, exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                if (input.equalsIgnoreCase("list")) { //list all tasks
                    if (taskCounter == 0) {
                            System.out.println("You are done for the day, or are you?");
                    } else {
                        for (int i = 0; i < taskCounter; i ++) {
                            System.out.println(i + 1 + "." + tasks[i]);
                        }
                    }
                } else if (input.toLowerCase().matches("mark \\d+")) { //mark task as done
                    int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
                    try {
                        tasks[index].markAsDone();
                    } catch (Exception e) {
                        System.out.println("No such task!");
                    }
                } else if (input.toLowerCase().matches("unmark \\d+")) { //mark task as undone
                    int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
                    try {
                        tasks[index].markAsUndone();
                    } catch (Exception e) {
                        System.out.println("No such task!");
                    }
                } else { //add task
                    if (input.toLowerCase().matches("^deadline .*")) { //deadline
                        try {
                            String actualTask = input.replaceAll("deadline ", "");
                            String[] sliced = actualTask.split("/by ");
                            String description = sliced[0];
                            String by = sliced[1];
                            Deadline deadline = new Deadline(description, by);
                            if (setOfAllTasks.contains(input.toLowerCase())) {
                                System.out.println("Task already exists!");
                            } else {
                                setOfAllTasks.add(input.toLowerCase());
                                tasks[taskCounter] = deadline;
                                taskCounter++;
                                System.out.println("Got it. I've added this task: \n" + " " + deadline +
                                        "\nNow you have " + taskCounter + " tasks in the list.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command, check your input!");
                        }
                    } else if (input.toLowerCase().matches("^event .*")) { //event
                        try {
                            String actualTask = input.replaceAll("event ", "");
                            String[] sliced = actualTask.split("/at ");
                            String description = sliced[0];
                            String at = sliced[1];
                            Event event = new Event(description, at);
                            if (setOfAllTasks.contains(input.toLowerCase())) {
                                System.out.println("Task already exists!");
                            } else {
                                setOfAllTasks.add(input.toLowerCase());
                                tasks[taskCounter] = event;
                                taskCounter++;
                                System.out.println("Got it. I've added this task: \n" + " " + event +
                                        "\nNow you have " + taskCounter + " tasks in the list.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command, check your input!");
                        }
                    } else if (input.toLowerCase().matches("^todo .*")) {
                        try {
                            String description = input.replaceAll("todo ", "");
                            Todo todo = new Todo(description);
                            if (setOfAllTasks.contains(input.toLowerCase())) {
                                System.out.println("Task already exists!");
                            } else {
                                setOfAllTasks.add(input.toLowerCase());
                                tasks[taskCounter] = todo;
                                taskCounter++;
                                System.out.println("Got it. I've added this task: \n" + " " + todo +
                                        "\nNow you have " + taskCounter + " tasks in the list.");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command, check your input!");
                        }
                    } else {
                        System.out.println("Invalid command, check your input!");
                    }
                }
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
        System.out.println("Hello from\n" + logo + "My name is Cortana, what can I do for you, master?");
        simpleTodo();
    }
}
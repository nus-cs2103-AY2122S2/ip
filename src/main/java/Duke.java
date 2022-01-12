import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Duke {
    private static final Set<String> setOfAllTasks = new HashSet<>();
    final static Task[] tasks = new Task[100];
    private static int taskCounter = 0;

    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();

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
                    try {
                        boolean isEmptyDeadline = input.replaceAll(" ", "").equalsIgnoreCase("deadline");
                        boolean isEmptyEvent = input.replaceAll(" ", "").equalsIgnoreCase("event");
                        boolean isEmptyTodo = input.replaceAll(" ", "").equalsIgnoreCase("todo");
                        String taskType = isEmptyDeadline ? "deadline" : isEmptyEvent ? "event" : "todo";
                        if (isEmptyDeadline || isEmptyEvent || isEmptyTodo ) {
                            throw new CortanaException(taskType);
                        } else {
                            boolean isNotEmptyDeadline = input.toLowerCase().matches("^deadline .*");
                            boolean isNotEmptyEvent = input.toLowerCase().matches("^event .*");
                            boolean isNotEmptyTodo = input.toLowerCase().matches("^todo .*");
                            boolean hasBy = Pattern.compile("/by .*").matcher(input).find();
                            boolean hasAt = Pattern.compile("/at .*").matcher(input).find();
                            //boolean noSlash = Pattern.compile(" \\w[by|at] ").matcher(input).find();
                            if (isNotEmptyDeadline & hasBy) {
                                String actualTask = input.replaceAll("deadline ", "");
                                String[] sliced = actualTask.split("/by ");
                                String description = sliced[0];
                                String by = sliced[1];
                                taskActions("deadline", input, description, by);
                            } else if (isNotEmptyEvent && hasAt) {
                                String actualTask = input.replaceAll("event ", "");
                                String[] sliced = actualTask.split("/at ");
                                String description = sliced[0];
                                String at = sliced[1];
                                taskActions("event", input, description, at);
                            } else if (isNotEmptyTodo) {
                                String description = input.replaceAll("todo ", "");
                                taskActions("todo", input, description, "");
                            } else if (isNotEmptyDeadline & !hasBy || isNotEmptyEvent & !hasAt) {
                                taskType = isNotEmptyDeadline ? "deadline without time" : "event without time";
                                throw new CortanaException(taskType);
                            } else {
                                taskType = "nothing";
                                throw new CortanaException(taskType);
                            }
                        }
                    } catch (CortanaException e) {
                        e.UnidentifiedCommandException();
                        e.EmptyDescriptionException();
                        e.DidNotSpecifyTimeException();
                    }
                }
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void taskActions(String taskType, String input, String description, String time) {
        switch (taskType) {
            case "deadline":
                if (setOfAllTasks.contains(input.toLowerCase())) {
                    System.out.println("Task already exists!");
                } else {
                    setOfAllTasks.add(input.toLowerCase());
                    Deadline deadline = new Deadline(description, time);
                    tasks[taskCounter] = deadline;
                    taskCounter++;
                    System.out.println("Got it. I've added this task: \n" + " " + deadline +
                            "\nNow you have " + taskCounter + " tasks in the list.");
                }
                break;
            case "event":
                if (setOfAllTasks.contains(input.toLowerCase())) {
                    System.out.println("Task already exists!");
                } else {
                    setOfAllTasks.add(input.toLowerCase());
                    Event event = new Event(description, time);
                    tasks[taskCounter] = event;
                    taskCounter++;
                    System.out.println("Got it. I've added this task: \n" + " " + event +
                            "\nNow you have " + taskCounter + " tasks in the list.");
                }
                break;
            case "todo":
                if (setOfAllTasks.contains(input.toLowerCase())) {
                    System.out.println("Task already exists!");
                } else {
                    setOfAllTasks.add(input.toLowerCase());
                    Todo todo = new Todo(description);
                    tasks[taskCounter] = todo;
                    taskCounter++;
                    System.out.println("Got it. I've added this task: \n" + " " + todo +
                            "\nNow you have " + taskCounter + " tasks in the list.");
                }
                break;
        }
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Duke {
    private static final Set<Task> taskSet = new HashSet<>();
    private final static ArrayList<Task> tasksArrayList = new ArrayList<>();
    private enum TaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();

        while(true) {
            if (input.replaceAll("[ |\\t]", "").equalsIgnoreCase("bye")) { //bye, exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                if (input.replaceAll("[ |\\t]", "").equalsIgnoreCase("list")) { //list all tasks
                    if (tasksArrayList.size() == 0) {
                            System.out.println("You are done for the day, or are you?");
                    } else {
                        for (int i = 0; i < tasksArrayList.size(); i ++) {
                            System.out.println(i + 1 + "." + tasksArrayList.get(i));
                        }
                    }
                } else if (input.toLowerCase().matches("^mark \\d+|^mark -\\d+")) { //mark task as done
                    int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
                    try {
                        tasksArrayList.get(index).markAsDone();
                    } catch (Exception e) {
                            System.out.println("No such task!");
                    }
                } else if (input.toLowerCase().matches("^unmark \\d+|^unmark -\\d+")) { //mark task as undone
                    int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
                    try {
                        tasksArrayList.get(index).markAsUndone();
                    } catch (Exception e) {
                        System.out.println("No such task!");
                    }
                } else if (input.toLowerCase().matches("^delete \\d+|^delete -\\d+")) { //delete a task
                    int index = Integer.parseInt(input.replaceAll("delete ", "")) - 1;
                    try {
                        Task taskDeleted = tasksArrayList.get(index);
                        tasksArrayList.remove(index);
                        taskSet.remove(taskDeleted);
                        String taskOrTasks = tasksArrayList.size() < 1 ? "task" : "tasks";
                        System.out.println("Noted. I've removed this task:\n" + " " + taskDeleted + "\n" +
                                "Now you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
                    } catch (Exception e) {
                        System.out.println("No such task!");
                    }
                } else { //add task
                    try {
                        //only input the task type and nothing else
                        boolean isEmptyDeadline = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("deadline");
                        boolean isEmptyEvent = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("event");
                        boolean isEmptyTodo = input.replaceAll("[ |\\t]", "").equalsIgnoreCase("todo");
                        String taskType = isEmptyDeadline ? "deadline" : isEmptyEvent ? "event" : "todo";
                        if (isEmptyDeadline || isEmptyEvent || isEmptyTodo ) {
                            throw new CortanaException("OOPS!!! The description of a " + taskType + " cannot be empty! \uD83E\uDD21");
                        } else {
                            //input the task type with at least one character after it
                            boolean isNotEmptyDeadline = input.toLowerCase().matches("^deadline .*");
                            boolean isNotEmptyEvent = input.toLowerCase().matches("^event .*");
                            boolean isNotEmptyTodo = input.toLowerCase().matches("^todo .*");
                            boolean hasBy = Pattern.compile("/by .*").matcher(input).find();
                            boolean hasAt = Pattern.compile("/at .*").matcher(input).find();
                            if (isNotEmptyDeadline && hasBy) { //valid deadline command
                                String actualTask = input.replaceAll("deadline ", "");
                                String[] sliced = actualTask.split("/by ");
                                String description = sliced[0];
                                String by = sliced[1];
                                taskActions(TaskTypes.DEADLINE, description, by);
                            } else if (isNotEmptyEvent && hasAt) { //valid event command
                                String actualTask = input.replaceAll("event ", "");
                                String[] sliced = actualTask.split("/at ");
                                String description = sliced[0];
                                String at = sliced[1];
                                taskActions(TaskTypes.EVENT, description, at);
                            } else if (isNotEmptyTodo) { //valid todo command
                                String description = input.replaceAll("todo ", "");
                                taskActions(TaskTypes.TODO, description, "");
                            } else if (isNotEmptyDeadline && hasAt) { //used /at for deadline
                                throw new CortanaException("Please use the /by keyword for deadline!");
                            } else if (isNotEmptyEvent && hasBy) { //used /by for event
                                throw new CortanaException("Please use the /at keyword for event!");
                            } else if (isNotEmptyDeadline) { //deadline without specifying time with /by
                                throw new CortanaException("Please specify the deadline time with the /by keyword! ⌚");
                            } else if (isNotEmptyEvent) { //event without specifying time with /at
                                throw new CortanaException("Please specify the event time with the /at keyword! ⌚");
                            } else { //invalid command
                                throw new CortanaException("I don't know what that means \uD83D\uDE05");
                            }
                        }
                    } catch (CortanaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void taskActions(TaskTypes taskType, String description, String time) {
        String taskOrTasks = tasksArrayList.size() < 1 ? "task" : "tasks";
        switch (taskType) {
            case DEADLINE:
                Deadline deadline = new Deadline(description, time);
                if (taskSet.contains(deadline)) {
                    System.out.println("Task already exists!");
                } else {
                    taskSet.add(deadline);
                    tasksArrayList.add(deadline);
                    System.out.println("Got it. I've added this task: \n" + " " + deadline +
                            "\nNow you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
                }
                break;
            case EVENT:
                Event event = new Event(description, time);
                if (taskSet.contains(event)) {
                    System.out.println("Task already exists!");
                } else {
                    taskSet.add(event);
                    tasksArrayList.add(event);
                    System.out.println("Got it. I've added this task: \n" + " " + event +
                            "\nNow you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
                }
                break;
            case TODO:
                Todo todo = new Todo(description);
                if (taskSet.contains(todo)) {
                    System.out.println("Task already exists!");
                } else {
                    taskSet.add(todo);
                    tasksArrayList.add(todo);
                    System.out.println("Got it. I've added this task: \n" + " " + todo +
                            "\nNow you have " + tasksArrayList.size() + " " + taskOrTasks + " in the list.");
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
        System.out.println("Hello from\n" + logo + "My name is Cortana, what can I do for you?");
        simpleTodo();
    }
}
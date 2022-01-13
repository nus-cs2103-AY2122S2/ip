import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String divider = "\n______________________________________\n";

    public static void addTask(String task) {
        System.out.println("Added as per your request: " + task);
        tasks.add(new Task(task));
    }

    public static void listTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i+1).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(listOfTasks);
    }

    public static void markTask(int taskId, boolean mark) {
        Task toSet = tasks.get(taskId - 1);
        if (mark) {
            toSet.markIsDone();
            tasks.set(taskId - 1, toSet);
            System.out.println("Sugoi! I have marked this task as done!\n" + tasks.get(taskId - 1).toString());
        } else {
            if (toSet.isDone) {
                toSet.markUndone();
                tasks.set(taskId - 1, toSet);
                System.out.println("No worries! I have unmarked this task for you!\n" + tasks.get(taskId - 1).toString());
            } else {
                System.out.println("This task has yet to be done!");
            }
        }
    }

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Konnichiwassup from\n" + logo);
        Scanner scanner = new Scanner(System.in);


        System.out.println(divider);
        System.out.println("What do you need help with?\n");
        String[] inputArray = scanner.nextLine().split(" ");


        while (!(inputArray[0].equalsIgnoreCase("bye") && inputArray.length == 1)) {

            // single command
            if (inputArray.length == 1) {
               if (inputArray[0].equalsIgnoreCase("list")) {
                   listTasks();
               } else {
                   addTask(inputArray[0]);
               }
            }

            // double command
            if (inputArray.length > 1) {
                if (inputArray[0].equalsIgnoreCase("mark")) {
                    try {
                        int taskId = Integer.parseInt(inputArray[1]);
                        if (taskId > 0 && taskId < (tasks.size() + 1)) {
                            markTask(taskId, true);
                        } else {
                            System.out.println("Task cannot be found within the task list! Please fix your machigai!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Task ID needs to be an integer");
                    }
                } else if (inputArray[0].equalsIgnoreCase("unmark")) {
                    try {
                        int taskId = Integer.parseInt(inputArray[1]);
                        if (taskId > 0 && taskId < tasks.size()) {
                            markTask(taskId, false);
                        } else {
                            System.out.println("Task cannot be found within the task list! Please fix your machigai!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Task ID has to be an integer!");
                    }
                }
                else {
                    StringBuilder input = new StringBuilder();
                    for (String item: inputArray) {
                        input.append(item).append(" ");
                    }
                    addTask(input.toString());
                }
            }

            System.out.println(divider);
            inputArray = scanner.nextLine().split(" ");
        }


        System.out.println(divider);
        System.out.println("Sayonara! Hope to see you again soon!");
        scanner.close();
    }
}

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Saitama");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String[] command = sc.nextLine().trim().toLowerCase().split(" ", 2);
            System.out.println("____________________________________________________________");
            try {
                if (command[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command[0].equals("list")) {
                    System.out.println(taskList);
                } else if (command[0].equals("mark")) {
                    Task task = taskList.get(Integer.parseInt(command[1]));
                    task.markAsDone();
                } else if (command[0].equals("unmark")) {
                    Task task = taskList.get(Integer.parseInt(command[1]));
                    task.markAsUndone();
                } else if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) {
                    try {
                        addTask(taskList, command);
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void addTask(TaskList taskList, String[] command) throws EmptyDescriptionException {
        if (command.length < 2) {
            throw new EmptyDescriptionException(command[0]);
        } else {
            Task newTask;
            String[] substring;
            switch (command[0]) {
                case "todo":
                    newTask = new ToDo(command[1]);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
                    break;

                case "deadline":
                    substring = command[1].split("/by ");
                    if (substring.length < 2) {
                        newTask = new Deadline(substring[0]);
                    } else {
                        newTask = new Deadline(substring[0], substring[1]);
                    }
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
                    break;

                case "event":
                    substring = command[1].split("/at ");
                    if (substring.length < 2) {
                        newTask = new Event(substring[0]);
                    } else {
                        newTask = new Event(substring[0], substring[1]);
                    }

                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
            }
        }
    }
}
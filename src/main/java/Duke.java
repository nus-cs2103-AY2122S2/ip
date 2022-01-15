import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = "███████╗ █████╗ ██╗████████╗ █████╗ ███╗   ███╗ █████╗\n" +
                "██╔════╝██╔══██╗██║╚══██╔══╝██╔══██╗████╗ ████║██╔══██╗\n" +
                "███████╗███████║██║   ██║   ███████║██╔████╔██║███████║\n" +
                "╚════██║██╔══██║██║   ██║   ██╔══██║██║╚██╔╝██║██╔══██║\n" +
                "███████║██║  ██║██║   ██║   ██║  ██║██║ ╚═╝ ██║██║  ██║\n";
        System.out.println(logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Saitama");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String str = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (str.equals("list")) {
                System.out.println(taskList);
            } else if (str.length() > 5 && str.substring(0,4).equals("mark")) {
                Task task = taskList.get(Integer.parseInt(str.substring(5)));
                task.markAsDone();
            } else if (str.length() > 7 && str.substring(0,6).equals("unmark")) {
                Task task = taskList.get(Integer.parseInt(str.substring(7)));
                task.markAsUndone();
            } else if (str.length() > 5 && str.substring(0,4).equals("todo")) {
                Task newTask = new ToDo(str.substring(5));
                taskList.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
            } else if (str.length() > 9 && str.substring(0,8).equals("deadline")) {
                String[] substring = str.split("/by ");
                Task newTask = new Deadline(substring[0].substring(9), substring[1]);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
            } else if (str.length() > 6 && str.substring(0,5).equals("event")) {
                String[] substring = str.split("/at ");
                Task newTask = new Event(substring[0].substring(6), substring[1]);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.");
            } else {
                System.out.println("Invalid command!");
            }

            System.out.println("____________________________________________________________");
        }
    }
}
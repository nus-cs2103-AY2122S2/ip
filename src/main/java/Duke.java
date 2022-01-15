import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    Hello! I'm Duke\n" + "    What can I do for you?");
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String comm = sc.nextLine();
            String[] processedCommand = comm.split(" ", 2);
            switch (processedCommand[0]) {
                case "bye":
                    System.out.println("    Bye. Hope to see you again soon!");
                    isExit = true;
                    break;
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        int indexToPrint = i + 1;
                        if (tasks[i].getIsDone()) {
                            System.out.println("    " + indexToPrint + ".[X] " + tasks[i].getDescription());
                        } else {
                            System.out.println("    " + indexToPrint + ".[ ] " + tasks[i].getDescription());
                        }
                    }
                    break;
                case "mark":
                    int taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    tasks[taskIndex].setIsDone(true);
                    System.out.println("    Nice! I've marked this task as done:" + "\n"
                            + "      [X] " + tasks[taskIndex].getDescription());
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    tasks[taskIndex].setIsDone(false);
                    System.out.println("    Ok! I've marked this task as not done yet:" + "\n"
                            + "      [ ] " + tasks[taskIndex].getDescription());
                    break;
                default:
                    System.out.println("    added: " + comm);
                    Task newTask = new Task(comm);
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
            }
        }
    }
}

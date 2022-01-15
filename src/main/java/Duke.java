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
                        System.out.println("    " + indexToPrint + "." + tasks[i].identify());
                    }
                    break;
                case "mark":
                    int taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    tasks[taskIndex].setIsDone(true);
                    System.out.println("    Nice! I've marked this task as done:" + "\n"
                            + "      " + tasks[taskIndex].identify());
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    tasks[taskIndex].setIsDone(false);
                    System.out.println("    Ok! I've marked this task as not done yet:" + "\n"
                            + "      " + tasks[taskIndex].identify());
                    break;
                case "deadline":
                    String[] inputForDeadlineConstructor = processedCommand[1].split("/", 2);
                    inputForDeadlineConstructor[0] = inputForDeadlineConstructor[0].subSequence(0,
                            inputForDeadlineConstructor[0].length() - 1).toString();
                    Deadline deadline = new Deadline(inputForDeadlineConstructor[0], inputForDeadlineConstructor[1]);
                    System.out.println("    Got it. I've added this task: \n" + "      " + deadline.identify());
                    tasks[numOfTasks] = deadline;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
                    break;
                case "todo":
                    ToDo todo = new ToDo(processedCommand[1]);
                    System.out.println("    Got it. I've added this task: \n" + "      " + todo.identify());
                    tasks[numOfTasks] = todo;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
                    break;
                case "event":
                    String[] inputForEventConstructor = processedCommand[1].split("/", 2);
                    inputForEventConstructor[0] = inputForEventConstructor[0].subSequence(0,
                            inputForEventConstructor[0].length() - 1).toString();
                    Event event = new Event(inputForEventConstructor[0], inputForEventConstructor[1]);
                    System.out.println("    Got it. I've added this task: \n" + "      " + event.identify());
                    tasks[numOfTasks] = event;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
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

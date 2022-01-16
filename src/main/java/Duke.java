import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String comm = sc.nextLine();
            String[] processedCommand = comm.split(" ", 2);
            Integer taskIndex = null;
            switch (processedCommand[0]) {
                case "bye":
                    System.out.println("    Bye. Hope to see you again soon!");
                    isExit = true;
                    break;
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        int indexToPrint = i + 1;
                        System.out.println(String.format(
                                "    %d.%s", indexToPrint, tasks[i].identify()));
                    }
                    break;
                case "mark":
                    //Exception catching
                    if (processedCommand.length <= 1) {
                        new DukeException();
                        break;
                    }
                    try {
                        taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    } catch (NumberFormatException e) {
                        new DukeException();
                        break;
                    }

                    //Mark task
                    tasks[taskIndex].setIsDone(true);
                    System.out.println(String.format(
                            "    Nice! I've marked this task as done:\n    %s", tasks[taskIndex].identify()));
                    break;
                case "unmark":
                    //Exception catching
                    if (processedCommand.length <= 1) {
                        new DukeException();
                        break;
                    }
                    try {
                        taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                    } catch (NumberFormatException e) {
                        new DukeException();
                        break;
                    }

                    //Unmark task
                    tasks[taskIndex].setIsDone(false);
                    System.out.println("    Ok! I've marked this task as not done yet:" + "\n"
                            + "      " + tasks[taskIndex].identify());
                    break;
                case "deadline":
                    //Exception catching
                    if (processedCommand.length <= 1) {
                        new DukeException();
                        break;
                    } else if (!processedCommand[1].contains("/") || processedCommand[1].startsWith(("/"))) {
                        new DukeException();
                        break;
                    }


                    //Processing input
                    String[] inputForDeadlineConstructor = processedCommand[1].split("/", 2);
                    inputForDeadlineConstructor[0] = inputForDeadlineConstructor[0].subSequence(0,
                            inputForDeadlineConstructor[0].length() - 1).toString();

                    //Create deadline
                    Deadline deadline = new Deadline(inputForDeadlineConstructor[0], inputForDeadlineConstructor[1]);
                    System.out.println(String.format(
                            "    Got it. I've added this task: \n    %s", deadline.identify()));
                    tasks[numOfTasks] = deadline;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
                    break;
                case "todo":
                    //Exception catching
                    if (processedCommand.length <= 1) {
                        new DukeException();
                        break;
                    }

                    //Create Todo
                    ToDo todo = new ToDo(processedCommand[1]);
                    System.out.println(String.format(
                            "    Got it. I've added this task: \n      %s", todo.identify()));
                    tasks[numOfTasks] = todo;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
                    break;
                case "event":
                    //Exception catching
                    if (processedCommand.length <= 1) {
                        new DukeException();
                        break;
                    } else if (!processedCommand[1].contains("/") || processedCommand[1].startsWith(("/"))) {
                        new DukeException();
                        break;
                    }

                    //Input processing
                    String[] inputForEventConstructor = processedCommand[1].split("/", 2);
                    inputForEventConstructor[0] = inputForEventConstructor[0].subSequence(0,
                            inputForEventConstructor[0].length() - 1).toString();

                    //Create event
                    Event event = new Event(inputForEventConstructor[0], inputForEventConstructor[1]);
                    System.out.println(String.format(
                            "    Got it. I've added this task: \n      %s", event.identify()));
                    tasks[numOfTasks] = event;
                    numOfTasks++;
                    System.out.println(String.format("    Now you have %d tasks in the list.", numOfTasks));
                    break;
                default:
                    System.out.println(String.format("    added: %s", comm));
                    Task newTask = new Task(comm);
                    tasks[numOfTasks] = newTask;
                    numOfTasks++;
            }
        }
        sc.close();
    }
}

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        System.out.println(WELCOME_MESSAGE);
        while (!isExit) {
            String userCommand = sc.next();
            switch (userCommand) {
                case "bye":
                    isExit = true;
                    System.out.println(GOODBYE_MESSAGE);
                    break;
                case "list":
                    for (int i = 0; i < Task.getTasks().size(); i++) {
                        System.out.printf("%d.%s%n", i + 1, Task.getTasks().get(i));
                    }
                    break;
                case "mark":
                    int markIndex = sc.nextInt() - 1;
                    Task markTask = Task.getTasks().get(markIndex);
                    markTask.setIsDone(true);
                    System.out.printf("Nice! I've marked this task as done: \n"
                            + "   %s\n", markTask);
                    break;
                case "unmark":
                    int unmarkIndex = sc.nextInt() - 1;
                    Task unmarkTask = Task.getTasks().get(unmarkIndex);
                    unmarkTask.setIsDone(false);
                    System.out.printf("Ok, I've marked this task as not done yet: \n"
                            + "   %s\n", unmarkTask);
                    break;
                case "todo":
                    String toDoDescription = sc.nextLine().trim();
                    Task newToDo = new ToDo(toDoDescription);
                    Task.addTask(newToDo);
                    System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                            newToDo, Task.taskCountToString());
                    break;
                case "deadline":
                    String deadlineTempInput = sc.nextLine();
                    String deadlineDescription = deadlineTempInput.split("/by", 2)[0].trim();
                    String deadlineDueDate = deadlineTempInput.split("/by", 2)[1].trim();
                    Task newDeadline = new Deadline(deadlineDescription, deadlineDueDate);
                    Task.addTask(newDeadline);
                    System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                            newDeadline, Task.taskCountToString());
                    break;
                case "event":
                    String eventTempInput = sc.nextLine();
                    String eventDescription = eventTempInput.split("/at", 2)[0].trim();
                    String eventDateTime = eventTempInput.split("/at", 2)[1].trim();
                    Task newEvent = new Event(eventDescription.toString(), eventDateTime.toString());
                    Task.addTask(newEvent);
                    System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                            newEvent, Task.taskCountToString());
                    break;
                default:
                    System.out.printf("Unknown command.\n");
                    break;
            }
        }
    }
}

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) throws DukeException {
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
                    try {
                        Task markTask = Task.getTasks().get(markIndex);
                        markTask.setIsDone(true);
                        System.out.printf("Nice! I've marked this task as done: \n"
                                + "    %s\n", markTask);
                        break;
                    } catch (IndexOutOfBoundsException indexErr) {
                        DukeException e = new DukeException("☹ OOPS!!! Invalid index.");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    int unmarkIndex = sc.nextInt() - 1;
                    try {
                        Task unmarkTask = Task.getTasks().get(unmarkIndex);
                        unmarkTask.setIsDone(false);
                        System.out.printf("Ok, I've marked this task as not done yet: \n"
                                + "    %s\n", unmarkTask);
                        break;
                    } catch (IndexOutOfBoundsException indexErr) {
                        DukeException e = new DukeException("☹ OOPS!!! Invalid index.");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int deleteIndex = sc.nextInt() - 1;
                    try {
                        Task deleteTask = Task.getTasks().get(deleteIndex);
                        Task.deleteTask(deleteIndex);
                        System.out.printf("Noted. I've removed this task: \n" + "    %s\n"
                                + "%s\n", deleteTask, Task.taskCountToString());
                        break;
                    } catch (IndexOutOfBoundsException indexErr) {
                        DukeException e = new DukeException("☹ OOPS!!! Invalid index.");
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    String toDoDescription = sc.nextLine().trim();
                    if (toDoDescription.equals("")) {
                        DukeException e = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(e.getMessage());
                    } else {
                        Task newToDo = new ToDo(toDoDescription);
                        Task.addTask(newToDo);
                        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                                newToDo, Task.taskCountToString());
                    }
                    break;
                case "deadline":
                    String deadlineTempInput = sc.nextLine();
                    if(!deadlineTempInput.contains("/by")) {
                        DukeException e = new DukeException("The additional info doesn't fit the input format.");
                        System.out.println(e.getMessage());
                        break;
                    }
                    String deadlineDescription = deadlineTempInput.split("/by", 2)[0].trim();
                    String deadlineDueDate = deadlineTempInput.split("/by", 2)[1].trim();
                    if (deadlineDescription.equals("")) {
                        DukeException e = new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(e.getMessage());
                    } else if (deadlineDueDate.equals("")) {
                        DukeException e = new DukeException("☹ OOPS!!! The due date of a deadline cannot be empty.");
                        System.out.println(e.getMessage());
                    } else {
                        Task newDeadline = new Deadline(deadlineDescription, deadlineDueDate);
                        Task.addTask(newDeadline);
                        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                                newDeadline, Task.taskCountToString());
                    }
                    break;
                case "event":
                    String eventTempInput = sc.nextLine();
                    if(!eventTempInput.contains("/at")) {
                        DukeException e = new DukeException("The additional info doesn't fit the input format.");
                        System.out.println(e.getMessage());
                        break;
                    }
                    String eventDescription = eventTempInput.split("/at", 2)[0].trim();
                    String eventDateTime = eventTempInput.split("/at", 2)[1].trim();
                    if (eventDescription.equals("")) {
                        DukeException e = new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(e.getMessage());
                    } else if (eventDateTime.equals("")) {
                        DukeException e = new DukeException("☹ OOPS!!! The date/time of an event cannot be empty.");
                        System.out.println(e.getMessage());
                    } else {
                        Task newEvent = new Event(eventDescription, eventDateTime);
                        Task.addTask(newEvent);
                        System.out.printf("Got it. I've added this task:\n" + "%s\n" + "%s\n",
                                newEvent, Task.taskCountToString());
                    }
                    break;
                default:
                    DukeException e = new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(e.getMessage());
                    break;
            }
        }
    }
}

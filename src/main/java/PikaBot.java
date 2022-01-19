import java.util.Scanner;
import java.util.ArrayList;


public class PikaBot {

    static String line = "_________________________________";
    static String indentation = "     ";

    public static Todo addTodo(String[] todoArray) throws TodoException {
        if (todoArray.length == 1) {
            throw new TodoException();
        } else {
            String description = todoArray[1];
            return new Todo(description);
        }
    }

    public static Deadline addDeadline(String[] deadlineArray) throws DeadlineException {
        if (deadlineArray.length == 1) {
            throw new DeadlineException("The description of a deadline cannot be empty.");
        } else {
            String[] deadlineDetails = deadlineArray[1].split("/by ", 2);
            if (deadlineDetails.length == 1) {
                throw new DeadlineException("The description of a deadline must contain a deadline");
            } else {
                return new Deadline(deadlineDetails[0], deadlineDetails[1]);
            }
        }
    }

    public static Event addEvent(String[] eventArray) throws EventException {
        if (eventArray.length == 1) {
            throw new EventException("The description of an event cannot be empty.");
        } else {
            String[] eventDetails = eventArray[1].split("/at ", 2);
            if (eventDetails.length == 1) {
                throw new EventException("The description of an event must contain a specific time");
            } else {
                return new Event(eventDetails[0], eventDetails[1]);
            }
        }
    }

    public static void addedTask(Task task, ArrayList<Task> listOfTasks) {
        System.out.println(indentation + line + "\n" +
                indentation + "Got it. I've added this task:" + "\n" +
                indentation + "  " + task + "\n" +
                indentation + "Now you have " + listOfTasks.size() + " tasks in the list." +
                "\n" +
                indentation + line);
    }

    public static void invalidTask() throws InvalidTaskCommandException {
        throw new InvalidTaskCommandException();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> inputArr = new ArrayList<>();

        System.out.println(indentation + line + "\n" + indentation + "Hello! I'm PikaBot" + "\n" + indentation +
                "What can I do for you? シ\n" + indentation + line);

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ", 2);


        while (!strInputArr[0].equals("bye")) {

            switch (strInputArr[0]) {
                case "list": {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Here are the tasks in your list:");

                    int taskNumber = 1;
                    int length = inputArr.size();

                    while (taskNumber <= length) {
                        System.out.println(indentation + taskNumber + "." +
                                inputArr.get(taskNumber - 1));
                        taskNumber++;
                    }

                    System.out.println(indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);

                    break;
                }

                case "mark": {
                    int taskToMark = Integer.parseInt(strInputArr[1]);
                    Task currTask = inputArr.get(taskToMark - 1);
                    currTask.markAsDone();

                    System.out.println(indentation + line);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + currTask);
                    System.out.println(indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "unmark": {
                    int taskToUnmark = Integer.parseInt(strInputArr[1]);
                    Task currTask = inputArr.get(taskToUnmark - 1);
                    currTask.markAsUndone();

                    System.out.println(indentation + line);
                    System.out.println(indentation + "OK, I've marked this task as not done yet:");
                    System.out.println(indentation + currTask);
                    System.out.println(indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }
                case "todo": {
                    try {
                        Todo currTodo = addTodo(strInputArr);
                        inputArr.add(currTodo);
                        addedTask(currTodo, inputArr);
                    } catch (TodoException e) {
                        System.out.println(indentation + line);
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentation + line);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "deadline": {
                    try {
                        Deadline currDeadline = addDeadline(strInputArr);
                        inputArr.add(currDeadline);
                        addedTask(currDeadline, inputArr);
                    } catch (DeadlineException e) {
                        System.out.println(indentation + line);
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentation + line);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "event": {
                    try {
                        Event currEvent = addEvent(strInputArr);
                        inputArr.add(currEvent);
                        addedTask(currEvent, inputArr);
                    } catch (EventException e) {
                        System.out.println(indentation + line);
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentation + line);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }

                case "delete": {
                    int taskNumberToDelete = Integer.parseInt(strInputArr[1]);
                    Task taskToDelete = inputArr.get(taskNumberToDelete - 1);
                    inputArr.remove(taskNumberToDelete - 1);
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Noted. I've removed this task:");
                    System.out.println(indentation + "  " + taskToDelete);
                    System.out.println(indentation + "Now you have " + inputArr.size() + " tasks in the list");
                    System.out.println(indentation + line);


                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                default: {
                    try {
                        invalidTask();
                    } catch (InvalidTaskCommandException e) {
                        System.out.println(indentation + line);
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentation + line);
                    } finally {
                        input = sc.nextLine();
                        strInputArr = input.split(" ", 2);
                    }
                    break;
                }
            }
        }

        System.out.println(indentation + line + "\n" + indentation + "Bye. Hope to see you again!" + "\n" + indentation
                + line);

        sc.close();
    }
}

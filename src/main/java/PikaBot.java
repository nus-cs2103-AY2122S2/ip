import java.util.Scanner;
import java.util.ArrayList;


public class PikaBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> inputArr = new ArrayList<>();

        String line = "_________________________________";
        String indentation = "     ";

        System.out.println(indentation + line + "\n" + indentation + "Hello! I'm PikaBot" + "\n" + indentation +
                "What can I do for you?\n" + indentation + line);

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
                    strInputArr = input.split(" ",2);
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
                    String description = strInputArr[1];
                    Todo currTodo = new Todo(description);
                    inputArr.add(currTodo);
                    System.out.println(indentation + line + "\n" +
                            indentation + "Got it. I've added this task:" + "\n" +
                            indentation + "  " + currTodo + "\n" +
                            indentation + "Now you have " + inputArr.size() + " tasks in the list." +
                            "\n" +
                            indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "deadline": {
                    String[] details = strInputArr[1].split("/by ");
                    String description = details[0];
                    String by = details[1];
                    Deadline currDeadline = new Deadline(description, by);
                    inputArr.add(currDeadline);
                    System.out.println(indentation + line + "\n" +
                            indentation + "Got it. I've added this task:" + "\n" +
                            indentation + "  " + currDeadline + "\n" +
                            indentation + "Now you have " + inputArr.size() + " tasks in the list." +
                            "\n" +
                            indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }

                case "event": {
                    String[] details = strInputArr[1].split("/at ");
                    String description = details[0];
                    String at = details[1];
                    Event currEvent = new Event(description, at);
                    inputArr.add(currEvent);
                    System.out.println(indentation + line + "\n" +
                            indentation + "Got it. I've added this task:" + "\n" +
                            indentation + "  " + currEvent + "\n" +
                            indentation + "Now you have " + inputArr.size() + " tasks in the list." +
                            "\n" +
                            indentation + line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ", 2);
                    break;
                }
            }
        }

        System.out.println(indentation + line + "\n" + indentation + "Bye. Hope to see you again!" + "\n" + indentation
                + line);

        sc.close();
    }
}

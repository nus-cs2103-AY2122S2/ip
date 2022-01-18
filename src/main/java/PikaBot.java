import java.util.Scanner;
import java.util.ArrayList;

public class PikaBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> inputArr = new ArrayList<>();

        String line = "_________________________________";
        String indentation = "     ";

        System.out.println(indentation + line + "\n" + indentation + "Hello! I'm PikaBot " + "\n" + indentation +
                "What can I do for you? \n" + indentation + line);

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ");


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
                    strInputArr = input.split(" ");

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
                    strInputArr = input.split(" ");
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
                    strInputArr = input.split(" ");
                    break;
                }
                default: {
                    inputArr.add(new Task(input));
                    System.out.println(indentation + line + "\n" + indentation + "added: " + input + "\n" + indentation +
                            line);

                    input = sc.nextLine();
                    strInputArr = input.split(" ");
                }
            }
        }

        System.out.println(indentation + line + "\n" + indentation + "Bye. Hope to see you again!" + "\n" + indentation
                + line);

        sc.close();
    }
}

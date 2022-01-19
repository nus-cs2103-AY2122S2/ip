import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(lines);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] split = input.split(" ");
            if (input.equals("bye")) {
                System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
                break;
            }
            else if (input.equals("list")) {
                System.out.println(lines);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0;i < counter;i++) {
                    System.out.println(Integer.toString(i + 1) + "." + tasks[i].toString());
                }
                System.out.println(lines);

            }
            else if (split[0].equals("mark")) {
                int number = Integer.parseInt(split[1]) - 1;
                if (number < counter) {
                    tasks[number].markAsDone();
                    System.out.println(lines + "\nNice! I've marked this task as done:\n" +
                            tasks[number].toString() + "\n" + lines);
                }
                else {
                    System.out.println(lines + "\nNo such task exists. \n" + lines);
                }
            }
            else if (split[0].equals("unmark")) {
                int number = Integer.parseInt(split[1]) - 1;
                if (number < counter) {
                    tasks[number].markAsNotDone();
                    System.out.println(lines + "\nOk, I've marked this task as not done yet:\n" +
                            tasks[number].toString() + "\n" + lines);
                }
                else {
                    System.out.println(lines + "\nNo such task exists. \n" + lines);
                }
            }
            else if (split[0].equals("todo")){
                tasks[counter++] = new Todo(input.replace("todo ",""));
                System.out.println(lines + "\nGot it. I've added this task:\n" + tasks[counter - 1].toString()
                        + "\n" + "Now you have " + counter + " tasks in the list.\n" + lines);
            }
            else if (split[0].equals("deadline")){
                String[] newSplit = input.split("/by ");
                if (newSplit.length > 1) {
                    tasks[counter++] = new Deadline(newSplit[0].replace("deadline ",""),newSplit[1]);
                    System.out.println(lines + "\nGot it. I've added this task:\n" + tasks[counter - 1].toString()
                            + "\n" + "Now you have " + counter + " tasks in the list.\n" + lines);
                }
                else {
                    System.out.println(lines + "\nInvalid input \n" + lines);
                }
            }
            else if (split[0].equals("event")){
                String[] newSplit = input.split("/at ");
                if (newSplit.length > 1) {
                    tasks[counter++] = new Event(newSplit[0].replace("event ", ""), newSplit[1]);
                    System.out.println(lines + "\nGot it. I've added this task:\n" + tasks[counter - 1].toString()
                            + "\n" + "Now you have " + counter + " tasks in the list.\n" + lines);
                }
                else {
                    System.out.println(lines + "\nInvalid input \n" + lines);
                }
            }
        }
    }
}
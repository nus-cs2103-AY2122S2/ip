import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskNum = 0;

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";
        String conformation = "Got it. I've added this task:";

        System.out.println(greet);

        while (true) {
            Scanner sc = new Scanner(System.in);

            String first_word = sc.next();
            String remaining_word = sc.nextLine();

            if (first_word.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else if (first_word.equals("list")) {
                if (taskNum == 0) {
                    System.out.println("No task");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 1; i < taskNum + 1; i++){
                        Task currTask = taskList[i - 1];
                        System.out.println(i + ". " + currTask);
                    }
                }
            } else if (first_word.equals("deadline")) {
                System.out.println(conformation);
                String[] actions = remaining_word.trim().split("/by", 2);
                Deadline currTask = new Deadline(actions[0].trim(), actions[1].trim());
                taskList[taskNum] = currTask;
                taskNum++;
                System.out.println(currTask + "\n" + "Now you have " + taskNum
                        + " tasks in the list.");
            } else if (first_word.equals("event")) {
                System.out.println(conformation);
                String[] actions = remaining_word.trim().split("/at", 2);
                Event currTask = new Event(actions[0].trim(), actions[1].trim());
                taskList[taskNum] = currTask;
                taskNum++;
                System.out.println(currTask + "\n" + "Now you have " + taskNum
                        + " tasks in the list.");
            } else if (first_word.equals("todo")) {
                System.out.println(conformation);
                ToDo currTask = new ToDo(remaining_word.trim());
                taskList[taskNum] = currTask;
                taskNum++;
                System.out.println(currTask + "\n" + "Now you have " + taskNum
                        + " tasks in the list.");
            } else if (first_word.equals("mark")) {
                int currTaskNum = Integer.parseInt(remaining_word.trim());
                Task currTask = taskList[currTaskNum - 1];
                currTask.setChecked();
                System.out.println("Nice! I've marked this task as done:\n"
                        + currTask);
            } else if (first_word.equals("unmark")) {
                int currTaskNum = Integer.parseInt(remaining_word.trim());
                Task currTask = taskList[currTaskNum - 1];
                currTask.setUnchecked();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + currTask);
            } else {
                String curr_word = first_word + remaining_word;
                Task curr_task = new Task(curr_word);
                taskList[taskNum] = curr_task;
                taskNum++;
                System.out.println("added: " + curr_task.getDescription());
            }
        }
    }
}
import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Task[] Tasks;
        Tasks = new Task[100];
        int count = 0;

        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);

        String input = " ";
        while (!input.equals("bye")) {

            input = br.readLine();
            String toCheck = firstN(input, 8);

            switch(input) {
                case "list":
                    if (count == 0) {
                        System.out.println("empty list!");
                    } else {
                        System.out.println("Here are the tasks in your list: ");
                        for (int i = 0; i < count; i++) {

                            System.out.println((i + 1) + ". " + Tasks[i].getStatus() +
                                   " " + Tasks[i].getTask());
                        }
                    }
                    System.out.println("\n");
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    String[] splitInput = input.split(" ");
                    switch(splitInput[0]) {
                        case "mark":
                            int taskID = Integer.parseInt(splitInput[1]) - 1;
                            Tasks[taskID].setDone();
                            System.out.println("Nice! I've marked this task as done: \n" +
                                    Tasks[taskID].getStatus() + " " + Tasks[taskID].getTask() + "\n");
                            break;
                        case "unmark":
                            int taskIDu = Integer.parseInt(splitInput[1]) - 1;
                            Tasks[taskIDu].setUnDone();
                            System.out.println("OK, I've marked this task as not done yet: \n" +
                                    Tasks[taskIDu].getStatus() + " " + Tasks[taskIDu].getTask() + "\n");
                            break;
                        default:
                            Task task = new Task(input);
                            System.out.println("added: " + input + "\n");
                            Tasks[count] = task;
                            count++;
                    }
            }
        }
    }

    public static String firstN(String string, int n) {
        return string.length() < n ? string : string.substring(0, n);
    }
}

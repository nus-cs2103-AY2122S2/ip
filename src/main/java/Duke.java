import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private ArrayList<Task> arr;

    public Duke(){
        this.arr = new ArrayList<Task>();
    }

    private void addTask(Task t) {
        this.arr.add(t);
    }

    private void answer(String input) {
        String ans = "\t";
        if (input.equals("bye")) {
            ans += "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            ans += "Here are the tasks in your list: \n";
            for (int i = 0; i < arr.size(); i++) {
                Task t = arr.get(i);
                if (i == arr.size() - 1) {
                    ans += String.format("\t%d.%s", i + 1, t.toString());
                } else {
                    ans += String.format("\t%d.%s \n", i + 1, t.toString());
                }
            }
        } else if (Pattern.matches("mark \\d+|unmark \\d+", input)) {
            String[] strArr = input.split(" ");
            int index = Integer.valueOf(strArr[1]) - 1;
            if (index >= 0 && index < arr.size()) {
                Task t = arr.get(index);
                if (strArr[0].equals("mark")) {
                    t.markDone();
                    ans += "Nice! I've marked this task as done: \n\t\t" + t.toString();
                } else {
                    t.markUndone();
                    ans += "OK, I've marked this task as not done yet: \n\t\t" + t.toString();
                }
            } else {
                ans = "\tInvalid index given!";
            }
        } else {
            Task task = new Task(input);
            addTask(task);
            ans += "added: " + input;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            duke.answer(userInput);
        }
        sc.close();

    }
}


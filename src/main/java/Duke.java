import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> arr;
    private int numOfTasks;

    public Duke(){
        this.arr = new ArrayList<String>(0);
        this.numOfTasks = 0;
    }

    private void update(String s) {
        this.arr.add(s);
    }

    private void answer(String input) {
        String ans = "";
        if (input.equals("bye")) {
            ans = "Bye. Hope to see you again soon!";
        } else if (input.equals("list")) {
            for (String s : arr) {
                ans += s + ('\n');
            }
            ans = ans.trim();
        } else {
            numOfTasks++;
            String task = numOfTasks + ". " + input;
            update(task);
            ans = "added: " + input;
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


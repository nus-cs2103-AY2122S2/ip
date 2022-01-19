import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        String userInput = "";
        ArrayList<Task> tasks= new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(!userInput.equals("bye")){
            userInput = sc.nextLine();
            StringTokenizer st = new StringTokenizer(userInput, " ");
            String curr = st.nextToken();
            if(curr.equals("list")){
                for(int x = 0; x < tasks.size(); x++){
                    System.out.println((x+1) + ". " + tasks.get(x).toString());
                }
            } else if(curr.equals("mark")){
                int toMark = Integer.parseInt(st.nextToken());
                tasks.get(toMark - 1).markCompleted();
            } else if(curr.equals("unmark")){
                int toMark = Integer.parseInt(st.nextToken());
                tasks.get(toMark - 1).markNotCompleted();
            } else {
                System.out.println("Added: " + userInput);
                tasks.add(new Task(userInput, false));
            }
        }

        System.out.println("Bye! Hope to see you again soon");
}
    }

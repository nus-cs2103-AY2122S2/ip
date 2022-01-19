import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        String input = "";
        ArrayList<String> tasks= new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(!input.equals("bye")){
            input = sc.nextLine();
            if(!input.equals("list")){
                System.out.println("Added: " + input);
                tasks.add(input);
            } else {
                for(int x = 0; x < tasks.size(); x++){
                    System.out.println((x+1) + ". " + tasks.get(x));
                }
            }
        }

        System.out.println("Bye! Hope to see you again soon");
}
    }

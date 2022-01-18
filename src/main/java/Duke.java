import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";

        String separation = "\n******************************\n";
        System.out.println(separation + greeting + separation);
        List list_of_inputs = new ArrayList<String>();
        while(true) {
            Scanner input = new Scanner(System.in);
            String inp = input.nextLine();

            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) {
                for (int i = 0; i < list_of_inputs.size(); i++) {
                    System.out.println(Integer.toString(i+1) + ". " + list_of_inputs.get(i));
                }
            } else {
                System.out.println("added:" + inp);
                list_of_inputs.add(inp);
            }
        }

    }


}

import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        int counter = 0;

        List list = new ArrayList<String>();

        while(sc.hasNext()) {
            String value = sc.nextLine();
            if (value.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (value.equals("list")) {
                for (int i=0; i<list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                System.out.println("added: "+ value);
                list.add(value);
            }
        }

    }
}

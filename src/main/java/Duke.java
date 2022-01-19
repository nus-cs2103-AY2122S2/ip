import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \n" + "What do you need me to note down for you? Type it below!");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        while(true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope your memory gets better!!");
                break;
            }
            else if (input.equals("list")) {
                int count = 0;
                while (count < list.size()) {
                    int t = count + 1;
                    System.out.println(t + ". " + list.get(count));
                    count++;
                }
            }
            else {
                list.add(input);
                System.out.println("NOTED DOWN: " + input);
            }
        }
    }
}

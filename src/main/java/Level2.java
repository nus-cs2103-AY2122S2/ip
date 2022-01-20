import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Level2 {

    public static void main(String[] args) {
        
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
       
        List<String> library = new ArrayList<>();
        
        while(sc.hasNext()) {
            String s = sc.nextLine();
            
            if(s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(s.equals("list")) {
                int count = 1;
                for (String book  : library) {
                    System.out.println(count + ". " + book);
                    count++;
                }
            }
            else {
                System.out.println("added: " + s);
                library.add(s);
            }
        }
    }
}
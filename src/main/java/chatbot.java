import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class chatbot {
    public static void main(String[] args) {
        String intro = "Hello! I'm Duke \n" +
                    "What can I do for you? \n";
    System.out.println(intro);
    
    List<String> ls = new ArrayList<>();
    while(true) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        if(n.compareToIgnoreCase("bye") == 0) {
            break;
        } else if (n.compareToIgnoreCase("list") != 0) {
            System.out.println("added: " + n);
            ls.add(n);
        } else {
            for(int i = 1; i <= ls.size(); i++) {
                System.out.println(i + " " + ls.get(i -1));
            }
        }
    } 
    System.out.println("Bye. Hope to see you again");
    
    }
}

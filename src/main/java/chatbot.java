import java.util.Scanner;
public class chatbot {
    public static void main(String[] args) {
        String intro = "Hello! I'm Duke \n" +
                    "What can I do for you? \n";
    System.out.println(intro);
    
    while(true) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        if(n.compareToIgnoreCase("bye") == 0) {
            break;
        } else {
            System.out.println(n);
        }
    } 
    System.out.println("Bye. Hope to see you again");
    
    }
}

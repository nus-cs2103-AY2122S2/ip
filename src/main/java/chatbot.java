import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class chatbot {
    public static void main(String[] args) {
        String intro = "Hello! I'm Duke \n" +
                    "What can I do for you? \n";
    System.out.println(intro);
    
    List<Task> ls = new ArrayList<>();
    while(true) {
        Scanner sc = new Scanner(System.in);
        Task n = new Task(sc.nextLine());
        if(n.description.equals("bye")) {
            break;
        } else if (n.description.equals("list")) {
            System.out.println("Here are the things you've tried to do\n");
            for(int i = 1; i <= ls.size(); i++) {
                System.out.println(i + " " + ls.get(i -1));
            }
        } else if(n.description.startsWith("mark")) {
            int x = Integer.parseInt(n.description.substring(5));
            Task edited = ls.remove(x -1);
            edited.done();
            ls.add(x-1, edited);
            for(int i = 1; i <= ls.size(); i++) {
                System.out.println(i + " " + ls.get(i -1));
            }
        } else if (n.description.startsWith("unmark")) {
            int x = Integer.parseInt(n.description.substring(7));
            Task edited = ls.remove(x -1);
            edited.undone();
            ls.add(x-1, edited);
            for(int i = 1; i <= ls.size(); i++) {
                System.out.println(i + " " + ls.get(i -1));
            }
        } else {
            ls.add(n);
            System.out.println("added: " + n);
        }
    } 
    System.out.println("Bye. Hope to see you again");
    
    }
}

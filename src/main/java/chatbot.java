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
        try { 
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
            } else if(n.description.startsWith("todo")) {
                todo a = new todo(n.description.substring(5));
                if(a.description.equals("")) {
                    throw new DukeException("Nothings here");
                } else {
                    ls.add(a);
                    System.out.println("Added the following todo");
                    System.out.println("added: " + a);
                    System.out.println("New list size is " + ls.size());
                }
            } else if(n.description.startsWith("deadline")) {
                String[] parts = n.description.substring(9).split(" /by ");
                Deadline d = new Deadline(parts[0], parts[1]);
                ls.add(d);
                System.out.println("Added the following todo");
                System.out.println("added: " + d);
                System.out.println("New list size is " + ls.size());
            } else if(n.description.startsWith("event")) {
                String[] parts = n.description.substring(6).split(" /at ");
                Event e = new Event(parts[0], parts[1]);
                ls.add(e);
                System.out.println("added: " + e);
                System.out.println("New list size is " + ls.size());
            } else {
                throw new DukeException("I dont know what you want but this aint it");
            }
        } catch (DukeException error){
            System.out.println("OOPS! " + error.getMessage());
        } catch (IndexOutOfBoundsException a) {
            System.out.println("Nothing inside");
        }

    } 
    System.out.println("Bye. Hope to see you again");
    
    }
}

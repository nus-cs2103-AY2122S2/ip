import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Duke {
    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke\n      What can I do for you?");
        String inp = "";
        DukeList a = new DukeList();
        while (!inp.equals("bye")) {
            inp = s.next();
            if (inp.equals("bye")) {
                System.out.println("\nDuke:  Bye. Hope to see you again soon!");
            }
            else if(inp.equals("list")){
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
                w.write("\nDuke:\nHere are the tasks in your list:\n");
                w.write(a.print());
                w.newLine();
                w.flush();
            }
            else if (inp.equals("mark")){
                try {
                    String n = s.nextLine();
                    if (!n.isEmpty()) {
                        a.mark(n);
                    } else {
                        System.out.println("Duke: Please input the index of the task to mark!\n");
                    }
                } catch(WrongMarkingFormat e){
                    System.out.println(e.getMessage());
                }
            }
            else if (inp.equals("unmark")){
                try {
                    String n = s.nextLine();
                    if (!n.isEmpty()) {
                        a.unmark(n);
                    } else {
                        System.out.println("Duke: Please input the index of the task to unmark!\n");
                    }
                } catch(WrongMarkingFormat e){
                    System.out.println(e.getMessage());
                }
            }
            else if(inp.equals("deadline") || inp.equals("todo") || inp.equals("event")){
                try{
                    String n = s.nextLine();
                    a.addTask(inp,n);
                } catch(DukeTaskException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(inp.equals("delete")){
                try {
                    String n = s.nextLine();
                    a.delete(n);
                } catch(DukeTaskException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(inp.equals("today")){
                a.todayTask();
            }
            else {
                System.out.println("\nDuke: OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }
}

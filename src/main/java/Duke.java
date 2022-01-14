import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: Hello! I'm Duke\n      What can I do for you?");
        String inp = "";
        List<Task> a = new ArrayList<Task>();
        while (!inp.equals("bye")) {
            inp = s.next();
            if (inp.equals("bye")) {
                System.out.println("\nDuke:  Bye. Hope to see you again soon!");
            }
            else if(inp.equals("list")){
                int j = 0;
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
                for(Task k:a){
                    j++;
                    w.write(j+". "+k.show());
                    w.newLine();
                }
                w.newLine();
                w.flush();
            }
            else if (inp.equals("mark")){
                if(s.hasNext()){
                    int x = Integer.parseInt(s.next());
                    a.get(x-1).mark();
                }
            }
            else if (inp.equals("unmark")){
                if(s.hasNext()){
                    int y = Integer.parseInt(s.next());
                    a.get(y-1).unmark();
                }
            }
            else {
                Task k = new Task(inp);
                a.add(k);
                System.out.println("\nDuke: Added \"" + inp + "\"\n");
            }
        }
    }
}

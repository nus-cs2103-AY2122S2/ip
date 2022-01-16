import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

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
                if(s.hasNext()){
                    int x = Integer.parseInt(s.next());
                    a.mark(x);
                }
            }
            else if (inp.equals("unmark")){
                if(s.hasNext()){
                    int y = Integer.parseInt(s.next());
                    a.unmark(y);
                }
            }
            else if(inp.equals("deadline")){
                if(s.hasNext()){
                    String n = s.nextLine();
                    if(Pattern.matches(".+/by.+" , n)){
                        String[] m = n.split("/by");
                        Deadlines e = new Deadlines(m[0],m[1]);
                        a.add(e);
                    }
                    else{
                        inp += n;
                    }
                }
            }
            else if(inp.equals("event")){
                if(s.hasNext()){
                    String l = s.nextLine();
                    if(Pattern.matches(".+/at.+" , l)){
                        String[] j = l.split("/at");
                        Events f = new Events(j[0],j[1]);
                        a.add(f);
                    }
                    else{
                        inp += l;
                    }
                }
            }
            else if(inp.equals("todo")){
                String h = s.nextLine();
                ToDos g = new ToDos(h);
                a.add(g);
            }
            else {
                Task k = new Task(inp);
                a.add(k);
            }
        }
    }
}

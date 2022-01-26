import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        String start = "              Greetings! Mike here!\n" +
                       "               How can I help you?\n" ;
        String gotit = "Got it. I've added this task:\n ";
        String mark = "Nice! I've marked this task as";
        String deleted = "Noted. I've removed this task:\n";
        String now1 = "Now you have ";
        String now2 = " tasks in the list.";
        String gnum = "Give me a Number.";
        String gdes = "Description is empty, give me a description.";
        String logo =
                  "||======||==||======|| !!!! ||====||    //===//======||\n"
                + "||                  || !!!! ||    ||   //   //       ||\n"
                + "||  ||==||  ||==||  ||======||    ||==||   //  ||====||\n"
                + "||  ||  ||  ||  ||  ||      ||            |||  ||====||\n"
                + "||  ||  ||  ||  ||  ||      ||    ||==||   \\\\  ||====||\n"
                + "||  ||  ||  ||  ||  ||      ||    ||   \\\\   \\\\       ||\n"
                + "||==||  ||==||  ||==||======||====||    \\\\===\\\\======||\n";

        System.out.println("\n" + logo + line);
        System.out.println(start+ line);

        ArrayList<Task> list = new ArrayList<>();
        int n = 0;
        String cmd = br.readLine();

        while(!cmd.equals("bye")){
            String[] c = cmd.split(" ");
            try {
                if (c[0].equals("list")) {
                    int m = 1;
                    int k = n;
                    System.out.println(line);
                    while(k > 0) {
                        System.out.println(m + "." + list.get(m-1).toString());
                        m+=1;
                        k-=1;
                    }
                    System.out.println(line);
                } else if (c[0].equals("mark")) {
                    if (cmd.equals("mark")) {
                        throw new DukeException(gnum);
                    }
                    int no = Integer.parseInt(c[1]) - 1;
                    list.get(no).markAsDone();
                    System.out.println(line + mark + " as done:\n" + list.get(no).toString() + line);
                } else if (c[0].equals("unmark")) {
                    if (cmd.equals("unmark")) {
                        throw new DukeException(gnum);
                    }
                    int no = Integer.parseInt(c[1]) - 1;
                    list.get(no).markAsUnDone();
                    System.out.println(line + mark + " as not done yet:\n" + list.get(no).toString() + line);
                } else if (c[0].equals("todo")) {
                    if (cmd.equals("todo")) {
                        throw new DukeException(gdes);
                    }
                    list.add(new Todo(cmd.substring(4)));
                    System.out.println(line + gotit + list.get(n).toString() + "\n" + now1 + (n+1) + now2 + line);
                    n+=1;
                } else if (c[0].equals("deadline")) {
                    if (cmd.equals("deadline")) {
                        throw new DukeException(gdes);
                    }
                    String[] x = cmd.substring(8).split("/by ");
                    DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
                    String formatedDate = LocalDateTime.parse(x[1],formatIn).format(formatOut);
                    list.add(new Deadline(x[0], formatedDate));
                    System.out.println(line + gotit + list.get(n).toString() + "\n" + now1 + (n+1) + now2 + line);
                    n+=1;
                } else if (c[0].equals("event")) {
                    if (cmd.equals("event")) {
                        throw new DukeException(gdes);
                    }
                    String[] x = cmd.substring(5).split("/at ");
                    list.add(new Event(x[0],x[1]));
                    System.out.println(line + gotit + list.get(n).toString() + "\n" + now1 + (n+1) + now2 + line);
                    n+=1;
                } else if (c[0].equals("delete")) {
                    if (cmd.equals("delete")) {
                        throw new DukeException(gnum);
                    }
                    n-=1;
                    int no = Integer.parseInt(c[1]) - 1;
                    Task removed = list.remove(no);
                    System.out.println(line + deleted + removed.toString() + line);
                } else {
                    throw new DukeException("INVALID Entry man, Try again :-( ");
                }
            } catch (DukeException e) {
                System.out.println(line + e.getMessage() + line);
            }
            cmd = br.readLine();
        }
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }
}

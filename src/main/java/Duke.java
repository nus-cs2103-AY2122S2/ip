import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        String logo =
                  "||======||==||======|| !!!! ||====||    //===//======||   \n"
                + "||                  || !!!! ||    ||   //   //       ||\n"
                + "||  ||==||  ||==||  ||======||    ||==||   //  ||====||\n"
                + "||  ||  ||  ||  ||  ||      ||            |||  ||====|| \n"
                + "||  ||  ||  ||  ||  ||      ||    ||==||   \\\\  ||====|| \n"
                + "||  ||  ||  ||  ||  ||      ||    ||   \\\\   \\\\       || \n"
                + "||==||  ||==||  ||==||======||====||    \\\\===\\\\======|| \n";

        System.out.println("\n" +logo + line);
        System.out.println("Greetings! Mike here! \nHow can I help you?" + line);

        Task list[] = new Task[100];
        int n = 0;
        String cmd = br.readLine();

        while(!cmd.equals("bye")){
            String[] c = cmd.split(" ");
            if (c[0].equals("list")) {
                int m = 1;
                int k = n;
                System.out.println(line);
                while(k > 0) {
                    System.out.println(m + "." + list[m-1].toString());
                    m+=1;
                    k-=1;
                }
                System.out.println(line);
            } else if (c[0].equals("mark")) {
                int no = Integer.parseInt(c[1]) - 1;
                list[no].markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:\n " + list[no].toString() + line);
            } else if (c[0].equals("unmark")) {
                int no = Integer.parseInt(c[1]) - 1;
                list[no].markAsUnDone();
                System.out.println(line + "Nice! I've marked this task as not done yet:\n " + list[no].toString() + line);
            } else {
                System.out.println(line + "added: " + cmd + line);
                list[n] = new Task(cmd);
                n+=1;
            }
            cmd = br.readLine();
        }
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }
}

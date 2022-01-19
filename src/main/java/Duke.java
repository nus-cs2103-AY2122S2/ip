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

        String list[] = new String[100];
        int n = 0;
        String cmd = br.readLine();

        while(!cmd.equals("bye")){
            if (cmd.equals("list")) {
                int m = 1;
                int k = n;
                System.out.println(line);
                while(k > 0) {
                    System.out.println(m + ". " + list[m-1]);
                    m+=1;
                    k-=1;
                }
                System.out.println(line);
            } else {
                System.out.println(line + "added: " + cmd + line);
                list[n] = cmd;
                n+=1;
            }
            cmd = br.readLine();
        }
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }
}

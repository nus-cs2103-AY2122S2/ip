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
        String cmd = br.readLine();
        while(!cmd.equals("bye")){
            System.out.println(line + cmd + line);
            cmd = br.readLine();
        }
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }
}

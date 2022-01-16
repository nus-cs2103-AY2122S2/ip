import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo =
                "U _____ u    ____    _   _      U  ___ u \n" +
                        "\\| ___\"|/ U /\"___|  |'| |'|      \\/\"_ \\/ \n" +
                        " |  _|\"   \\| | u   /| |_| |\\     | | | | \n" +
                        " | |___    | |/__  U|  _  |u .-,_| |_| | \n" +
                        " |_____|    \\____|  |_| |_|   \\_)-\\___/  \n" +
                        " <<   >>   _// \\\\   //   \\\\        \\\\    \n" +
                        "(__) (__) (__)(__) (_\") (\"_)      (__)";

        System.out.println("Hello I'm\n" + logo);

        Echo echo = new Echo();

        String s = sc.nextLine();
        while (!s.equals("bye")) {
            try {
                echo.read(s);
            } catch (EchoException e) {
                System.out.println("        ☹ OOPS!!! " + e.getMessage());
            }
            s = sc.nextLine();
        }
        echo.exit();
    }
}

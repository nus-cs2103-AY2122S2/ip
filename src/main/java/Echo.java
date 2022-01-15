import java.util.Scanner;

public class Echo {
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
        String s = sc.nextLine();
        while(!s.equals("bye")) {
            echo(s);
            s = sc.nextLine();
        }
        exit();
    }

    public static void echo(String s) {
        System.out.println("        " + s);
    }

    public static void exit() {
        System.out.println("        Goodbye!");
    }
}

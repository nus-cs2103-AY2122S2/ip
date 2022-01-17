import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " _       _   _   __  __   _   _ \n"
                    + "| |     | | | | |  \\/  | | | | |\n"
                    + "| |     | | | | | |\\/| | | | | |\n"
                    + "| |___  | |_| | | |  | | | |_| |\n"
                    + "|_____|  \\___/  |_|  |_|  \\___/ \n";

        String linebreak = "\n－－－－－－－－－－（・Ａ・）－－－－－－－－－\n";

        System.out.println(linebreak + "Hello I'm\n" + logo + linebreak);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();

            if (str.compareToIgnoreCase("bye") == 0) {
                System.out.println("LUMU: Goodbye. Hope to see you again soon");
                System.out.println(linebreak);
                sc.close();
                return;
            } else {
                System.out.println("LUMU: " + str);
                System.out.println(linebreak);
            }
        }
    }
}

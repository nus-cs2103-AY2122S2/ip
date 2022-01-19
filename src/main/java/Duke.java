import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey there! I'm Mickey, your personal mouse assistant.\nWhat can I do for you today?\n");
        for (String in = sc.next(); !in.equals("bye"); in = sc.next()) {
                System.out.println("\t" + in);
        }
        System.out.println("\tToodles! See ya real soon!");
    }
}

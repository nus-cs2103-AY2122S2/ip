import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String bar = "-------------------------------------------------"; //Reusable horizontal bar display
        String indent = "      "; //Reusable indentation display 
        System.out.println(bar);
        System.out.println("Hi I'm Zen!\n" +
                "How can I help ?");
        System.out.println(bar);
        System.out.println();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(bar);
            System.out.println(indent + input);
            System.out.println(bar);
            System.out.println();
            input = sc.nextLine();
        }
        System.out.println(bar);
        System.out.println(indent + "Bye! See you soon !");
        System.out.println(bar);
    }
}

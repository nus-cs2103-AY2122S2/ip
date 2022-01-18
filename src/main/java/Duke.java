import java.util.Scanner;  // Import the Scanner class
public class Duke {
    public static void main(String[] args) {
        String lineDivider = "____________________________________________________________\n";
        String greet = lineDivider
                + "Hello! I'm Mum!\n"
                + "What can I do for you?\n"
                + lineDivider;
        String goodBye = "Bye. Hope to see you again soon!";

        System.out.println(greet);
        
        while (true) {
            Scanner cmd = new Scanner(System.in);  // Create a Scanner object
            String echo = cmd.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            System.out.println(lineDivider + "\n" + echo + "\n" + lineDivider);
        }
        System.out.println(lineDivider + "\n" + goodBye + "\n" + lineDivider);
    }
}

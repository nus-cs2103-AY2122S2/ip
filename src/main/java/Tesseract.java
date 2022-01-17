import java.util.*;

public class Tesseract {
    public static void main(String[] args) {
        String INDENT = "    ";
        String BREAKER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        Scanner sc = new Scanner(System.in);

        String greetingMsg = INDENT + "Hi fellow! I am Tesseract\n" +
                INDENT + "I can bring you to wherever you want in the universe\n" +
                INDENT + "How can I help you?";

        String farewellMsg = BREAKER + "\n" +
                INDENT + "Ok I'm gonna travel to another planet now\n" +
                INDENT + "Hope to see you again when I'm back :P\n"
                + BREAKER;

        System.out.println(BREAKER);
        System.out.println(greetingMsg);
        System.out.println(BREAKER);

        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            String out = BREAKER + "\n" + INDENT + cmd + "\n" + BREAKER + "\n";
            System.out.println(out);
            cmd = sc.nextLine();
        }
        System.out.println(farewellMsg);
    }
}

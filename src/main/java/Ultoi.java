import java.util.Scanner;

public class Ultoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String lineBreaker = "======U======L======T======O======I======\n";

        String greetingMessage =
                lineBreaker +
                "    Hello! I am Ultoi [ uhl-twah ].\n" +
                "    What can I do for you? <O_O>\n" +
                lineBreaker;

        String byeMessage =
                lineBreaker +
                "     Bye. See you. <O_O>\n" +
                lineBreaker;

        System.out.print(greetingMessage);

        for ( ; ; ) {
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) { // end the session
                System.out.print(byeMessage);
                break;
            } else {
                String generatedMessage =
                        lineBreaker +
                        "     " + cmd + "\n" +
                        lineBreaker;
                System.out.print(generatedMessage);
            }
        }
    }
}

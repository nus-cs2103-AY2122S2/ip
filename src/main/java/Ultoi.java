import java.util.Scanner;
import java.util.ArrayList;

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

        ArrayList<Task> logs = new ArrayList<Task>();

        for ( ; ; ) {
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) { // end the session
                System.out.print(byeMessage);
                break;
            } else if (cmd.equals("list")) {
                System.out.print(lineBreaker);

                for (int i = 0; i < logs.size(); i++) {
                    Task curr = logs.get(i);
                    System.out.println((i + 1) + ". " + curr.toString());
                }

                System.out.print(lineBreaker);
            } else {
                logs.add(new Task(cmd));

                String generatedMessage =
                        lineBreaker +
                        "     added: " + cmd + "\n" +
                        lineBreaker;

                System.out.print(generatedMessage);
            }
        }
    }
}

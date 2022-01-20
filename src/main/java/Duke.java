import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lst = new String[100];
        Action act = new Action();

        act.greet();

        while (sc.hasNext()) {
            String word = sc.next();
            if (word.equals("bye")) {
                act.bye();
                break;
            } else {
                act.echo(word);
            }
        }
    }
}

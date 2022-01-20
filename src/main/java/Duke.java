import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arrlst = new ArrayList<>();
        String[] lst = new String[100];
        Action act = new Action();

        act.greet();

        while (sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                act.bye();
                break;
            } else if (word.equals("list")) {
                act.list(arrlst);
            } else if (word.equals("read book")) {
                act.readBook(arrlst);
                counter++;
            } else if (word.equals("return book")) {
                act.returnBook(arrlst);
                counter++;
            } else {
                act.echo(word);
            }
        }
    }
}

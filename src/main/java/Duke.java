import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrlst = new ArrayList<>();
        final String DASH = "____________________________________________________________";
        Action act = new Action();

        act.greet();

        while (sc.hasNext()) {
            String phrase = sc.nextLine();
            System.out.println(DASH);

            if (phrase.equals("list")) {
                act.showList(arrlst);
            } else if (phrase.equals("bye")) {
                act.bye();
                System.out.println(DASH);
                break;
            } else {
                String[] arrWords = phrase.split(" ");
                try { // mark or unmark
                    int num = Integer.valueOf(arrWords[1]);
                    if (arrWords[0].equals("mark")) {
                        arrlst.get(num - 1).markAsDone();
                    } else if (arrWords[0].equals("unmark")) {
                        arrlst.get(num - 1).markAsNotDone();
                    } else {
                        System.out.println("siao eh");
                    }
                } catch (NumberFormatException nfe) { // add new task like read book, return book
                    Task t = new Task(phrase);
                    System.out.println(t);
                    arrlst.add(t);
                } catch (ArrayIndexOutOfBoundsException aioobe) { // echo
                    act.echo(phrase);
                    System.out.println("sehh, what is this?");
                }
            }
            System.out.println(DASH);
        }
    }
}

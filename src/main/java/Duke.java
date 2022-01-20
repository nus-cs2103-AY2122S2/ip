import Exceptions.DukeException;
import Exceptions.TaskIndexException;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.nextLine();
            s = s.stripLeading();
            s = s.stripTrailing();
            String[] spliced = s.split("\\s+", 2);
            try {
                tm.execute(spliced);
            } catch (DukeException e) {
                tm.Line();
                System.out.println("☹ OOPS!!! You gave an invalid command!");
                tm.Line();
            } catch (TaskIndexException e) {
                tm.Line();
                System.out.println(e.toString());
                tm.Line();
            }
        } while (tm.open());
        sc.close();

    }
}

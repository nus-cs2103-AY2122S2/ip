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
            tm.execute(spliced);
        } while (tm.open());
        sc.close();

    }
}

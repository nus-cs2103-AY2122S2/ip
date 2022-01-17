import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "-------------------------------------------\n";
        String start = "Hello! I'm Dukey \n"+"What can I do for you?\n";
        System.out.println(line+start+line);

        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.next();
            if (s.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);

            }
            else {
                System.out.println(line + s + "\n" + line);
            }
        }
        while (!s.equals("bye"));
        sc.close();
    }
}

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "-------------------------------------------";
        String start = "Hello! I'm \n" +
                " __          ___      .______      .______     ____    ____ \n" +
                "|  |        /   \\     |   _  \\     |   _  \\    \\   \\  /   / \n" +
                "|  |       /  ^  \\    |  |_)  |    |  |_)  |    \\   \\/   /  \n" +
                "|  |      /  /_\\  \\   |      /     |      /      \\_    _/   \n" +
                "|  `----./  _____  \\  |  |\\  \\----.|  |\\  \\----.   |  |     \n" +
                "|_______/__/     \\__\\ | _| `._____|| _| `._____|   |__|     \n" +
                "                                                            \n"+
                "What can I do for you?";
        System.out.println(line);
        System.out.println(start);
        System.out.println(line);

        Task[] manager = new Task[100];
        int i = 0;
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            s = sc.nextLine();
            if (s.equals("bye") || s.equals("Bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);

            }
            else if (s.equals("list")) {
                System.out.println(line);
                for (int j = 0; j < i; j++) {
                    System.out.println(j+1 + ". " + manager[j]);
                }
                System.out.println(line);
            }
            else if (s.equals(" "));
            else {
                Task t = new Task(s);
                System.out.println(line);
                System.out.println("added: " + t);
                System.out.println(line);
                manager[i] = t;
                i++;

            }
        }
        while (!s.equals("bye") && !s.equals("Bye"));
        sc.close();
    }
}

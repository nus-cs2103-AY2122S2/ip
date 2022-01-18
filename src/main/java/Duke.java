import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "-------------------------------------------";
        String start = "Hello there! I'm \n" +
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
            String[] spliced = s.split("\\s+", 2);
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
            else if (spliced[0].equals("mark")) {
               Integer index = Integer.parseInt(spliced[1]);
               if (manager[index - 1] != null) {
                   manager[index - 1].mark();
               }
            }
            else if (spliced[0].equals("unmark")) {
                Integer index = Integer.parseInt(spliced[1]);
                if (manager[index - 1] != null) {
                    manager[index - 1].unmark();
                }
            }
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

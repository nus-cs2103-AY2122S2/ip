import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                            "Nice to meet you dear:>\n" +
                            "What can I do for you?");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input
        List<String> all = new ArrayList<String>();

        while (!(input = myObj.nextLine()).equals("bye")) { //check input not "bye"
            if (!input.equals("list")) {
                all.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 1; i <= all.size(); i++) {
                    System.out.println(i + ". " + all.get(i-1));
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!"); //ending sentence
    }
}

import java.util.Scanner; //Import the Scanner class

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
                            "I'll just repeat what you say for now");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input

        while (!(input = myObj.nextLine()).equals("bye")) { //check input not "bye"
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!"); //ending sentence
    }
}

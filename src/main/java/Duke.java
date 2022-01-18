import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = ""; 

        myPrint("Hello! I'm Duke\nWhat can I do for you?");

        input = sc.next();

        while(!input.equals("bye")) {
            myPrint(input);

            input = sc.next();

        }

        myPrint("Bye. Hope to see you again soon!");

        sc.close();

    }

    public static void myPrint(String toPrint){
        System.out.println("________________________________________________________________");
        System.out.println(toPrint);
        System.out.println("________________________________________________________________\n");

    }

}

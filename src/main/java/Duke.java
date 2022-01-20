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

        while (true) {
            String instruction = sc.nextLine();
            switch(instruction){
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!\n");
                    return;

                default:
                    System.out.println(instruction);
            }
            System.out.println("");
        }
    }
}

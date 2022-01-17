import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            String input = sc.nextLine();
            if (input.equals("Bye")){
                loop = false;
                System.out.println("Bye. Hope to see you soon.");
            } else {
                System.out.println("--------------------------------------------------");;
                System.out.println(input);
            }
        }
    }
}


import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while(!isBye){
            isBye = echo(sc.nextLine());
        }
    }

    public static boolean echo(String s){
        if (s.equals("bye")){
            System.out.println("Bye. I don't want to see you anytime soon! :)");
            return true;
        } else {
            System.out.println(s);
            return false;
        }
    }
}

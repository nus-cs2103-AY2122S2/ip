import java.util.Scanner;

class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static ItemList<Task> list = new ItemList<Task>();

    static void run(){
        System.out.println("=========================");
        System.out.println("   Harro! I am Bob!    ");
        System.out.println(" How can I be of service ");
        System.out.println("=========================");
        while(true) {
            System.out.print(">>> ");
            Command.runCommand(sc.nextLine(), list);
        }
    }
}

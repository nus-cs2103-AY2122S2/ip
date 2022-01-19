import java.util.Scanner;

class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static ItemList list = new ItemList();

    static void run(){
        System.out.println("=========================");
        System.out.println("   Harro! I am Bob!    ");
        System.out.println(" How can I be of service ");
        System.out.println("=========================");
        while(true) {
            Command.runCommand(sc.nextLine(), list);
        }
    }
}

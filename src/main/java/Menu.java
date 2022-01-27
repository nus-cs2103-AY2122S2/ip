import java.util.Scanner;

class Menu {
    private static Scanner sc = new Scanner(System.in);

    private TaskList list;

    Menu(TaskList taskList){
        this.list = taskList;
    }

    void run() {
        System.out.println("=========================");
        System.out.println("   Harro! I am Bob!    ");
        System.out.println(" How can I be of service ");
        System.out.println("=========================");
        while (true) {
            try {
                Command.runCommand(sc.nextLine(), list);
            } catch (DukeExceptions e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

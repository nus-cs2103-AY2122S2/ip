import java.util.Scanner;

public class Duke {
    private int repeatListSize = 0;
    private Task[] repeatList = new Task[100];
    private Scanner scanner;

    public Duke(Scanner sc) {
        this.scanner = sc;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke Lumu = new Duke(sc);

        Lumu.botInitialize();
        Lumu.botResponse();
    }

    private String lineBreak() {
        return "\n－－－－－－－－－－（・Ａ・）－－－－－－－－－\n";
    }

    private void botInitialize() {
        String logo = " _       _   _   __  __   _   _ \n"
            + "| |     | | | | |  \\/  | | | | |\n"
            + "| |     | | | | | |\\/| | | | | |\n"
            + "| |___  | |_| | | |  | | | |_| |\n"
            + "|_____|  \\___/  |_|  |_|  \\___/ \n";

        System.out.println(lineBreak() + "Hello I'm\n" + logo);
        System.out.println("What can I do for you?" + lineBreak());
    }

    private void botResponse() {
        while (true) {
            String str = scanner.nextLine();

            if (str.compareToIgnoreCase("bye") == 0) {
                System.out.println("LUMU: Goodbye. Hope to see you again soon");
                System.out.println(lineBreak());
                scanner.close();
                return;
            } else if (str.compareToIgnoreCase("list") == 0) {
                displayList();
                System.out.println(lineBreak());
            } else if (str.startsWith("mark ")) {
                markHandler(str);
                System.out.println(lineBreak());
            } else if (str.startsWith("unmark ")) {
                unmarkHandler(str);
                System.out.println(lineBreak());
            } else {
                System.out.println("added: " + str);
                addToList(str);
                System.out.println(lineBreak());
            }
        }
    }

    private void addToList(String str) {
        repeatList[repeatListSize] = new Task(str);
        repeatListSize++;
    }

    private void displayList() {
        if (repeatListSize == 0) {
            System.out.println("LUMU: Your list is empty!");
        } else {
            for (int i = 0; i < repeatList.length && i < repeatListSize; i++) {
                Task currTask = repeatList[i];
                System.out.println(String.valueOf(i + 1) + ". " + "[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            }
        }
    }

    private void markHandler(String str) {
        if (str.length() < 6) {
            System.out.println("Invalid task!");
        } else {
            String listNumber = str.substring(5);
            if ((listNumber.chars().allMatch(Character::isDigit)) && (Integer.parseInt(listNumber) <= repeatListSize)) {
                int num = Integer.parseInt(listNumber);
                Task currTask = repeatList[num - 1];
                currTask.setStatus(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            } else {
                System.out.println("Invalid task!");
            }
        }
    }

    private void unmarkHandler(String str) {
        if (str.length() < 8) {
            System.out.println("Invalid task!");
        } else {
            String listNumber = str.substring(7);
            if ((listNumber.chars().allMatch(Character::isDigit)) && (Integer.parseInt(listNumber) <= repeatListSize)) {
                int num = Integer.parseInt(listNumber);
                Task currTask = repeatList[num - 1];
                currTask.setStatus(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            } else {
                System.out.println("Invalid task!");
            }
        }
    }


}

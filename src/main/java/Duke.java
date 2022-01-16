import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hr = "\t____________________________________________________________";
        String logo = "\t  ____       _       _____   _____  \n" +
                "\t |  _\"\\  U  /\"\\  u  |\"_  / u |\"_  / u \n" +
                "\t/| | | |  \\/ _ \\/   U / / /  U / / /  \n" +
                "\tU| |_| |\\ / ___ \\   \\/ /_   \\/ /_   \n" +
                "\t |____/ u/_/   \\_\\  /____|  /____|  \n" +
                "\t  |||_    \\\\    >>  _//<<,- _//<<,- \n" +
                "\t (__)_)  (__)  (__)(__) (_/(__) (_/ ";
        System.out.println(hr + "\n\tHello from\n" + logo + "\n\n"
                + "\tGood day!\n\tWhat can I do for you? \n" + hr);
        Reminder task = new Reminder();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) { // bye
                System.out.println(hr + "\n\tBye. Hope to see you again soon!\n" + hr + "\n");
                break;
            } else if (command.equals("list")) { // list
                System.out.println(hr);
                task.list();
                System.out.println(hr);
            } else { // add
                System.out.println(hr + "\n\t" + task.add(command) + "\n" + hr);
            }
        }
        scanner.close();
    }
}


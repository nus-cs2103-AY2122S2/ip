import java.util.Scanner;

public class Duke {
    private ArrayList<String> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        list = new ArrayList<String>();
        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strArr = str.split(" ");
            if (str.equals("bye")) {
                System.out.println("Bye for now!");
                return;
            }
            else if (str.equals("list")) showList();
            else if (strArr[0].equals("mark")) {
                int taskNum = String.parseInt(strArr[1]);
                list.get(taskNum).setStatus(true);
            }
            else if (strArr[0].equals("unmark")) {
                int taskNum = String.parseInt(strArr[1]);
                list.get(taskNum).setStatus(false);
            }
            else {
                list.add(new Task(str));
                System.out.println(String.format("added: %s", str));
            }
        }
    }

    public showList() {
        for(int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus() ? "X" : " ";
            System.out.println(String.format("%d. [%s] %s", i, status, task.getStr()));
        }
    }
}

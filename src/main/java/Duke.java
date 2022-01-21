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
            if (str.equals("bye")) {
                System.out.println("Bye for now!");
                return;
            }
            if (str.equals("list")) showList();
            list.add(str);
            System.out.println(String.format("added: %s", str));
        }
    }

    public showList() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s", i, list.get(i)));
        }
    }
}

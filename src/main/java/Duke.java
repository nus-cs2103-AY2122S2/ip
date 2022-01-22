import java.util.Scanner;

public class Duke {
    private ArrayList<String> list;

    public static void main(String[] args) {
        System.out.println("Hello from Duke!");

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
            else if (strArr[0].equals("deadline")) { {
                String[] strParts = str.split("/by ");
                list.add(new Deadline(str, strParts[1]));
                System.out.println(String.format("added: %s", str));
            }
            else if (strArr[0].equals("event")) { {
                String[] strParts = str.split("/at ");
                list.add(new Event(str, strParts[1]));
                System.out.println(String.format("added: %s", str));
            }
            else if (strArr[0].equals("todo")) { {
                list.add(new Todo(str));
                System.out.println(String.format("added: %s", str));
            }
        }
    }

    public showList() {
        for(int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus() ? "X" : " ";
            System.out.println(String.format("%d. %s", i, task.toString()));
        }
    }
}

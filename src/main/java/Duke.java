import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("========================");

        ArrayList<Task> list = new ArrayList<>(100);

        while(true) {
            System.out.print("Me   : ");
            String message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (message.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.print(num + ". ");
                    list.get(i).getStatus();
                }

            } else {
                String[] splitStr = message.split(" ");
                if (splitStr[0].equals("mark")) {
                    try{
                        int index = Integer.parseInt(splitStr[1]);
                        index--;
                        if (index < list.size() && index > -1) {
                            Task updateT = list.get(index);
                            updateT.status = 1;
                            list.set(index, updateT);
                            updateT.taskDone();
                        } else {
                            System.out.println("Invalid mark index");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark index");
                    }
                } else if (splitStr[0].equals("unmark")) {
                    try{
                        int index = Integer.parseInt(splitStr[1]);
                        index--;
                        if (index < list.size() && index > -1) {
                            Task updateT = list.get(index);
                            updateT.status = 0;
                            list.set(index, updateT);
                            updateT.taskUndo();
                        } else {
                            System.out.println("Invalid mark index");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark index");
                    }
                } else {
                    Task newT = new Task(message, 0);
                    list.add(newT);
                    System.out.println("Duke has added :" + message);
                }
            }
        }
        sc.close();
    }
}

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
            String[] splitStr = message.split(" ");
            boolean exitLoop = false;

            switch(splitStr[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    exitLoop = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        int num = i + 1;
                        System.out.print(num + ". ");
                        list.get(i).getStatus();
                    }
                    break;
                case "mark":
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
                    break;
                case "unmark":
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
                    break;
                default:
                    Task newT = new Task(message);
                    list.add(newT);
                    System.out.println("Duke has added :" + message);
                    break;

            }
            if (exitLoop) {
                break;
            }
        }
        sc.close();
    }
}

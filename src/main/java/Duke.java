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
            String[] splitStr = message.split(" ", 2);
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
                            System.out.println("Nice! I've marked this task as done");
                            updateT.getStatus();
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
                            System.out.println("Ooof! I've marked this task as undone");
                            updateT.getStatus();
                        } else {
                            System.out.println("Invalid mark index");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark index");
                    }
                    break;
                case "todo":
                    Task newT = new Todo(splitStr[1], "T");
                    list.add(newT);
                    newT.addedTask();
                    newT.getStatus();
                    System.out.println("Now you've got " + list.size() +" tasks in the list.");
                    break;
                case "deadline":
                    String[] tempStr = splitStr[1].split(" /by ");
                    Task newD = new Deadline(tempStr[0], "D", tempStr[1]);
                    list.add(newD);
                    newD.addedTask();
                    newD.getStatus();
                    System.out.println("Now you've got " + list.size() +" tasks in the list.");
                    break;
                case "event":
                    String[] tempStr2 = splitStr[1].split(" /at ");
                    Task newE = new Event(tempStr2[0], "E", tempStr2[1]);
                    list.add(newE);
                    newE.addedTask();
                    newE.getStatus();
                    System.out.println("Now you've got " + list.size() +" tasks in the list.");
                    break;
                default:
                    System.out.println("Invalid Task.\nValid tasks are: \"todo\", \"deadline\" and \"event\"");
                    break;

            }
            if (exitLoop) {
                break;
            }
        }
        sc.close();
    }
}

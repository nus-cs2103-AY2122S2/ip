import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "       __  \n"
                + "(____()'`; \n"
                + "/,    /` \n"
                + "\\\\\"--\\\\\n";

        System.out.println("Woof, I am (supposed to look like) a dog bot. \n" + logo + "\n" +  "What do you want from me?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Task[] tasks = new Task[100];
        int size = 1;
        while (!str.equals("bye")){
            if (str.equals("list")){
                for (int i = 1; i < size; i++) {
                    Task task = tasks[i];
                    System.out.println(i + "." + task);
                }
                str = sc.nextLine();
            }
            else {
                String[] temp = str.split(" ");
                if (temp[0].equals("unmark") || temp[0].equals("mark")) {
                    try {
                        int taskNumber = Integer.parseInt(temp[1]);
                        if (temp[0].equals("mark")) {
                            Task currTask = tasks[taskNumber];
                            currTask.setDone();
                            System.out.println("Nice! I've marked this task as done: \n" + "  " + currTask);
                        }
                        else {
                            Task currTask = tasks[taskNumber];
                            currTask.setNotDone();
                            System.out.println("OK, I've marked this task as not done yet:: \n" + "  " + currTask);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("added : " + str);
                        tasks[size] = new Task(str);
                        size++;
                    }
                }
                else {
                    System.out.println("added : " + str);
                    tasks[size] = new Task(str);
                    size++;
                }
                str = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope not to see you again :)");
        sc.close();
    }
}

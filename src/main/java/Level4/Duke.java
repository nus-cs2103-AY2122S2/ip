import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! i'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        List<Task> taskList = new ArrayList<>();

        while(sc.hasNext()){
            String[] s = sc.nextLine().split(" ");

            if(s[0].equals("list")){
                int count = 1;
                for(Task sample:taskList) {
                    System.out.println(count + ". " + sample);
                    count++;
                }
            }
            else if(s[0].equals("mark")) {
                taskList.get(Integer.parseInt(s[1]) - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(Integer.parseInt(s[1]) - 1));
            }

            else if(s[0].equals("unmark")) {
                taskList.get(Integer.parseInt(s[1]) - 1).setUndone();
                System.out.println("OK,I've marked this task as not done yet:");
                System.out.println(taskList.get(Integer.parseInt(s[1]) - 1));
            }

            else if(s[0].equals("event")){
                String time = "";
                String str = "";
                int index = 0;

                for(int i=1; i<s.length;i++) {
                    if(s[i].equals("at/")) {
                        index = i+1;
                        break;
                    } else {
                        str = str + s[i] + " ";
                    }
                }
                for(int k = index; k<s.length;k++){
                    time = time + s[k] + " ";
                }
                Event event = new Event(str,time);
                taskList.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + taskList.size() + " tasks in the list");

            }

            else if(s[0].equals("deadline")) {
                String time = "";
                String str = "";
                int index = 0;

                for(int i = 1; i<s.length;i++){
                    if(s[i].equals("/by")){
                        index = i+1;
                        break;
                    } else {
                        str = str + s[i] + " ";
                    }
                }

                for(int k=index;k<s.length;k++) {
                    time = time + s[k] + " ";
                }
                Deadline deadline = new Deadline(str, time);
                taskList.add(deadline);
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline);
                System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
            }

            else if(s[0].equals("todo")) {
                String str = "";
                for(int i=1;i<s.length;i++) {
                    str = str + s[i] + "";
                }
                ToDos todoTask = new ToDos(str);
                taskList.add(todoTask);
                System.out.println("Got it. I've addded this task");
                System.out.println(todoTask);
                System.out.println("Now you have " + taskList.size() + " tasks in the lists.");
            }

            else if(s[0].equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                String str = Arrays.toString(s);
                str = str.substring(1, str.length()-1).replaceAll(",","");
                System.out.println("added: " + String.join(",",str));
                taskList.add(new Task(str));
            }
        }

    }
}

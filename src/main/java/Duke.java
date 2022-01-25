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

        while (sc.hasNext()) {
            try {
                String[] s = sc.nextLine().split(" ");
                if (s[0].equals("bye")) {
                    if (s.length > 1) {
                        throw new Exception_handler("I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (s[0].equals("list")) {
                    int count = 1;
                    for (Task sample : taskList) {
                        System.out.println(count + ". " + sample);
                        count++;
                    }
                } else if (s[0].equals("mark")) {
                    if (s.length < 2) {
                        throw new Exception_handler("☹ OOPS!!! That task doesn't exist or you failed to include a number.");
                    }
                    int index = Integer.parseInt(s[1]) - 1;
                    if (index < 0 || index > taskList.size() - 1) {
                        throw new Exception_handler("☹ OOPS!!! That task doesn't exist or you failed to include a number.");
                    }
                    taskList.get(index).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print(taskList.get(index));
                } else if (s[0].equals("todo")) {
                    if (s.length < 2) {
                        throw new Exception_handler("☹ OOPS!!! You're missing some descriptions for your event.");
                    }
                    String str = "";
                    for (int i = 1; i < s.length; i++) {
                        str = str + s[i] + " ";
                    }
                    ToDos task = new ToDos(str);
                    taskList.add(task);
                    System.out.println("Got it, I've added this task: ");
                    System.out.println(task);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else if (s[0].equals("event")) {
                    if (s.length < 2) {
                        throw new Exception_handler("☹ OOPS!!! The description cof a todo cannot be empty.");
                    }
                    String str = "";
                    String time = "";
                    int index = 0;
                    for (int i = 1; i < s.length; i++) {
                        if (s[i].equals("/at")) {
                            index = i + 1;
                            break;
                        } else {
                            str = str + s[i] + " ";
                        }
                    }
                    for (int k = index; k < s.length; k++) {
                        time = time + s[k] + " ";
                    }
                    Event event = new Event(str, time);
                    taskList.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list");
                } else if (s[0].equals("deadline")) {
                    if (s.length < 2) {
                        throw new Exception_handler("☹ OOPS!!! You're missing some descriptions for your deadline.");
                    }
                    String time = "";
                    String str = "";
                    int index = 0;

                    for (int i = 1; i < s.length; i++) {
                        if (s[i].equals("/by")) {
                            index = i + 1;
                            break;
                        } else {
                            str = str + s[i] + " ";
                        }
                    }

                    for (int k = index; k < s.length; k++) {
                        time = time + s[k] + " ";
                    }
                    Deadline deadline = new Deadline(str,time);
                    taskList.add(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
                } else if(s[0].equals("delete")) {
                    if(s.length<2) {
                        throw new Exception_handler("Please enter an index");
                    }
                    int index = Integer.parseInt(s[1]) - 1;
                    if(index<0 || index>=taskList.size()) {
                        throw new Exception_handler("Index out of range or invalid");
                    }
                    Task removed = taskList.remove(index);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(removed);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list. ");
                } else {
                    throw new Exception_handler("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch(Exception_handler e){
                System.out.println(e);
            }
        }
    }
}
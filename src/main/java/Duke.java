import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws MissingDescriptionException {
        String logo = "       __  \n"
                + "(____()'`; \n"
                + "/,    /` \n"
                + "\\\\\"--\\\\\n";

        System.out.println("Woof, I am (supposed to look like) a dog bot. \n" + logo + "\n" +  "What do you want from me?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Task> tasks = new ArrayList<>();
        while (!str.equals("bye")){
            try {
                errorHandling(str);
                if (str.equals("list")){
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + 1 + "." + task);
                    }
                    str = sc.nextLine();
                }
                else {
                    String[] temp = str.split(" ");
                    if (temp[0].equals("unmark") || temp[0].equals("mark")) {
                            int taskNumber = Integer.parseInt(temp[1]);
                            if (tasks.size() < taskNumber){
                                System.out.println("Invalid task number!");
                            }
                            else {
                                if (temp[0].equals("mark")) {
                                    Task currTask = tasks.get(taskNumber - 1);
                                    currTask.setDone();
                                    System.out.println("Nice! I've marked this task as done: \n" + "  " + currTask);
                                }
                                else {
                                    Task currTask = tasks.get(taskNumber - 1);
                                    currTask.setNotDone();
                                    System.out.println("OK, I've marked this task as not done yet:: \n" + "  " + currTask);
                                }
                            }
                    }
                    else {
                        if (temp[0].equals("todo")){
                            Todo todo = new Todo(str.substring(5));
                            tasks.add(todo);System.out.println("Got it. I've added this task: \n  " + todo +
                                    "\nNow you have " + tasks.size() + " task(s) on the list.");
                        }
                        else if (temp[0].equals("event")){
                            Event event = new Event(str.substring(6));
                            tasks.add(event);
                            System.out.println("Got it. I've added this task: \n  " + event +
                                    "\nNow you have " + tasks.size() + " task(s) on the list.");
                        }
                        else if (temp[0].equals("deadline")){
                            Deadline deadline = new Deadline(str.substring(9));
                            tasks.add(deadline);
                            System.out.println("Got it. I've added this task: \n  " + deadline +
                                    "\nNow you have " + tasks.size() + " task(s) on the list.");
                        }
                        else {
                            System.out.println("added : " + str);
                            tasks.add(new Task(str));
                        }
                    }
                }
           }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                str = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope not to see you again :)");
        sc.close();
    }

    private static void errorHandling(String str) throws MissingDescriptionException, DukeException {
        String[] strArr = str.split(" ");
        String firstWord = strArr[0];
        if (firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo")
                || firstWord.equals("list") || firstWord.equals("delete") || firstWord.equals("unmark")
                || firstWord.equals("mark")){
            if (firstWord.equals("deadline") || firstWord.equals("event") || firstWord.equals("todo") && strArr.length == 1) {
                throw new MissingDescriptionException();
            }
            if (firstWord.equals("unmark") || firstWord.equals("mark")){
                if (strArr.length == 1) {
                    throw new DukeException();
                }
                else {
                    try {
                        int index = Integer.parseInt(strArr[1]);
                    }
                    catch (NumberFormatException e){
                        throw new DukeException();
                    }
                }
            }
        }
        else{
            throw new DukeException();
        }
    }
}

package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;


public class Parser {
    public String[] s;

    public Parser(String command, TaskList tasks, Storage storage) throws IOException {
        s = command.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        FileWriter fw = new  FileWriter("data/duke.txt",true);
        PrintWriter wf = new  PrintWriter(fw);

            // "bye" to end the program
        switch (s[0]) {
            case "bye" -> System.out.println("    Bye!! See you again soon!!");


            // if user requests to list their tasks
            case "list" -> {
                System.out.println("    Here are the tasks in your lists:");
                int n = 1;
                for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                    Task t = tasks.get(i);
                    String by = "";
                    if (t.getType().equals("D") || t.getType().equals("E"))
                        by = t.getBy().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                    System.out.println("    " + n + ". [" + t.getType() + "][" + t.getDone() + "] " + t.desc + by);
                    n++;
                }
            }
            case "mark" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(s[1]) - 1;
                tasks.set(n, tasks.get(n).mark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc + " | " + date);

                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc);

                System.out.println("    Alright! I've marked this as done:\n      [" + temp.getDone() + "] " + temp.desc);
            }
            case "unmark" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(s[1]) - 1;
                tasks.set(n, tasks.get(n).unmark());
                Task temp = tasks.get(n);
                if (!(temp instanceof ToDo)) {
                    String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc + " | " + date);
                } else
                    tasks.updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc);

                System.out.println("    Alright! I've marked this as not done:\n      [" + temp.getDone() + "] " + temp.desc);
            }
            case "delete" -> {
                wf.flush(); // need to flush first cause updates were stored in buffer
                int n = Integer.parseInt(s[1]) - 1;
                Task t = tasks.get(n);
                tasks.remove(n);
                tasks.deleteTask(n + 1);
                System.out.println("    Alright! This task has been deleted:\n      [" + t.getType() + "][" + t.getDone() + "] " + t.desc + t.getBy());

            }

            // user add a todo task
            case "todo" -> {
                Arrays.toString(command.split(" ", 2));
                s = command.split(" ", 2);
                if (s.length < 2) {
                    System.out.println("    Oops!! Description of ToDo can't be empty!!\n ");
                } else {
                    System.out.println("    Okay! I've added this task:  ");
                    tasks.add(new ToDo(s[1]));
                    System.out.println("      [T][ ] " + s[1]);
                    wf.println("T 0 " + s[1]);
                    System.out.println("\n    Now you have " + tasks.getNumberOfTasks() + " tasks on your list");
                }
            }
            // user add a event task
            case "event" -> {
                Arrays.toString(command.split(" ", 2));
                s = findDate(command.split(" "));
                System.out.println("    Okay! I've added this task into the list:\n  ");
                tasks.add(new Event(s[0], LocalDateTime.parse(s[1], formatter)));
                System.out.println("      [E][ ] " + s[0]);
                wf.println("E 0 " + s[0] + " | " + s[1]);
                System.out.println("\n    Now you have " + tasks.getNumberOfTasks() + " tasks on your list");
            }
            // user add a deadline task
            case "deadline" -> {
                Arrays.toString(command.split(" ", 2));
                s = findDate(command.split(" "));
                System.out.println("    Okay! I've added this task:\n  ");
                tasks.add(new Deadline(s[0], LocalDateTime.parse(s[1], formatter)));
                System.out.println("      [D][ ] " + s[0]);
                wf.println("D 0 " + s[0] + " | " + s[1]);
                System.out.println("\n    Now you have " + tasks.getNumberOfTasks() + " tasks on your list");
            }
            // user add items to list
            default -> System.out.println("    Sorry! I don't know what that means :'(");
        }
       wf.close();

    }

    public String[] findDate(String[] full) {
        StringBuilder sb = new StringBuilder();
        String[] end = new String[100];
        int i = 0;
        for (String s : full) {
            if(s.charAt(0) == '/')
                break;
            i++;
        }
        // get the desc
        for(int j = 1; j < i; j++) {
            sb.append(full[j]).append(" ");
        }

        // get the time
        end[0] = sb.toString();
        sb = new StringBuilder();
//        }
        sb.append(full[i+1]).append(" ");
        sb.append(full[i+2]);

        end[1] = sb.toString();
        return end;
    }
}
